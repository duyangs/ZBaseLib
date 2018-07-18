package com.duyangs.zbase.mvp;

import android.app.Application;
import android.support.annotation.NonNull;
import android.util.Log;

import com.duyangs.zbase.mvp.annotation.Autowire;
import com.duyangs.zbase.mvp.model.face.BaseModel;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>Project:ZBase</p>
 * <p>Package:com.duyangs.zbase</p>
 * <p>Description:MvpAutowired</p>
 * <p>Company:</p>
 *
 * @author duyangs
 * @date 2018/07/18
 */
public class MvpAutowire {
    /**
     * 缓存需要注入得类的
     * 缓存类需要的注入
     */
    private final static Map<String, AutowireBean[]> AUTOWIRE_MAP = new ConcurrentHashMap<>(20);

    /**
     * 缓存注入的类
     */
    private final static ReferenceQueue<Object> MODEL_OBJECT_REFERENCE_QUEUE = new ReferenceQueue<>();

    /**
     * 缓存注入的类
     */
    private final static Map<String, Entry<String, Object>> MODEL_MAP = new ConcurrentHashMap<>(20);

    /**
     * app上下文缓存
     */
    private static WeakReference<Application> application;

    /**
     * 注释自动装配
     */
    private static AnnotationAutowire annotationAutowire;

    /**
     * 初始化
     *
     * @param application         应用
     * @param annotationAutowire 注释自动装配
     */
    public static void init(@NonNull Application application, @NonNull AnnotationAutowire annotationAutowire) {
        MvpAutowire.application = new WeakReference<>(application);
        MvpAutowire.annotationAutowire = annotationAutowire;
    }

    /**
     * 初始化
     *
     * @param application 应用
     */
    public static void init(@NonNull Application application) {
        MvpAutowire.application = new WeakReference<>(application);
        MvpAutowire.annotationAutowire = new AnnotationAutowire() {
            @NonNull
            @Override
            public Object newAnnotation(Class value, Autowire autowire, Field field) {
                throw new RuntimeException("不支持 " + value.getName() + " 注解");
            }
        };
    }

    private MvpAutowire() {

    }

    /**
     * 自动装配变量
     * 查找类里面需要装配的变量(变量用{@link Autowire}标识后)，自动装配
     *
     * @param o 需要装配得类
     */
    public static void autowire(Object o) {
        Class aClass = o.getClass();
        //获取要注入的变量
        AutowireBean[] autowireBeen = AUTOWIRE_MAP.get(aClass.getName());
        if (autowireBeen == null) {
            synchronized (AUTOWIRE_MAP) {
                autowireBeen = AUTOWIRE_MAP.get(aClass.getName());
                if (autowireBeen == null) {
                    ArrayList<AutowireBean> autowireBeenList = new ArrayList<>();
                    Field[] fields = aClass.getDeclaredFields();

                    AutowireBean autowireBean;
                    Autowire autowire;
                    for (Field field : fields) {
                        //获取需要注入的类
                        autowire = field.getAnnotation(Autowire.class);
                        if (autowire != null) {
                            if (autowire.value() == Object.class) {
                                autowire = field.getType().getAnnotation(Autowire.class);
                            }
                            field.setAccessible(true);
                            autowireBean = new AutowireBean(field, autowire);
                            autowireBeenList.add(autowireBean);
                        }
                    }
                    autowireBeen = autowireBeenList.toArray(new AutowireBean[autowireBeenList.size()]);
                    AUTOWIRE_MAP.put(aClass.getName(), autowireBeen);
                }
            }
        }
        //开始注入
        for (AutowireBean autowireBean : autowireBeen) {
            Object model = loadServiceModel(autowireBean);
            try {
                autowireBean.field.set(o, model);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 加载对应的类
     *
     * @param autowireBean 变量和对应的注解
     * @return 这个对象
     */
    @NonNull
    private static Object loadServiceModel(AutowireBean autowireBean) {

        expungeStaleEntries();

        Class value = autowireBean.autowire.value();
        String key = value.getName();
        Entry<String, Object> stringObjectEntry = MODEL_MAP.get(key);
        Object returnObject = null;
        if (stringObjectEntry != null) {
            returnObject = stringObjectEntry.get();
        }
        if (returnObject == null) {
            synchronized (MODEL_MAP) {
                stringObjectEntry = MODEL_MAP.get(key);
                if (stringObjectEntry != null) {
                    returnObject = stringObjectEntry.get();
                }
                if (returnObject == null) {
                    //判断是否是注解的Class
                    if (value.isAnnotation()) {
                        returnObject = newServiceAnnotation(value, autowireBean.autowire, autowireBean.field);
                    } else {
                        returnObject = newServiceModel(value);
                    }
                    stringObjectEntry = new Entry<>(key, returnObject, MODEL_OBJECT_REFERENCE_QUEUE);
                    MODEL_MAP.put(key, stringObjectEntry);
                }
            }
        }
        return returnObject;
    }

    /**
     * 加载Model实现类
     *
     * @param tClass model的接口
     * @param <T>    接口的类
     * @return 接口的对象
     */
    @SuppressWarnings("unchecked")
    public static <T> T loadServiceModel(Class<T> tClass) {
        Autowire autowire = tClass.getAnnotation(Autowire.class);
        if (autowire == null) {
            throw new NullPointerException("在" + tClass.getName() + "类中没有Autowired注解");
        }
        AutowireBean autowireBean = new AutowireBean(null, autowire);
        return (T) loadServiceModel(autowireBean);
    }

    /**
     * 从表中删除已经被回收的条目。
     */
    private static void expungeStaleEntries() {
        for (Object o; (o = MODEL_OBJECT_REFERENCE_QUEUE.poll()) != null; ) {
            synchronized (MODEL_OBJECT_REFERENCE_QUEUE) {
                synchronized (MODEL_MAP) {
                    @SuppressWarnings("unchecked")
                    Entry<String, Object> entry = (Entry<String, Object>) o;
                    MODEL_MAP.remove(entry.getKey());
                }
            }
        }
    }

    /**
     * 创建一个新的Model
     *
     * @param value 要创建的class
     * @return 新的Model
     */
    @NonNull
    private static Object newServiceModel(Class<?> value) {
        try {
            Object model = value.newInstance();
            //model初始化过程
            if (model instanceof BaseModel) {
                ((BaseModel) model).init(MvpAutowire.application.get());
            }
            Log.e("---------", "新创建");
            return model;
        } catch (Exception e) {
            throw new RuntimeException(" 创建" + value.getName() + " 失败", e);
        }
    }

    @NonNull
    private static Object newServiceAnnotation(Class<?> value, Autowire autowire, Field field) {
        return annotationAutowire.newAnnotation(value, autowire, field);
    }

    /**
     * 实际保存的对象
     *
     * @param <K>
     * @param <V>
     */
    private static class Entry<K, V> extends SoftReference<Object> {
        /**
         * 这个变量的key
         */
        private final K key;

        Entry(K key, V referent, ReferenceQueue<? super Object> q) {
            super(referent, q);
            this.key = key;
        }

        K getKey() {
            return key;
        }
    }

    /**
     * 变量和对应的注解
     */
    private static class AutowireBean {
        /**
         * 需要注入的变量
         */
        final Field field;
        /**
         * 自动装配
         */
        final Autowire autowire;

        AutowireBean(Field field, Autowire autowire) {
            this.field = field;
            this.autowire = autowire;
        }
    }

    /**
     * 注释自动装配
     */
    public interface AnnotationAutowire {
        /**
         * 当 {@link Autowire#value()} 的变量是一个 注解的class 的时候，将调用这个方法
         *
         * @param value     注解的class
         * @param autowire 装载注解
         * @param field     变量
         * @return 功能的对象，这个对象只会有一个，当多个地方装载这个时候，将复用一个对象
         */
        @NonNull
        Object newAnnotation(Class value, Autowire autowire, Field field);
    }
}
