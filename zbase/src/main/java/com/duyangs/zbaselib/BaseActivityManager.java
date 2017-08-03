package com.duyangs.zbaselib;

import android.app.Activity;
import android.content.Context;


import com.duyangs.zbaselib.util.LogUtil;

import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Stack;

/**
 * <p>Project:BaseLibDemo</p>
 * <p>Package:com.duyangs.baselib</p>
 * <p>Description: Activity 栈管理 </p>
 * <p>Company:</p>
 *
 * @author duyangs
 * @date 2017/04/21 0021
 */
public class BaseActivityManager {
    public static final String TAG = "BaseActivityManager";
    private static Stack<WeakReference<Activity>> activityStack;

    private BaseActivityManager() {
    }

    //懒汉单例模式 low
//    public static BaseActivityManager getInstance() {
//        if (instance == null){
//            instance = new BaseActivityManager();
//        }
//        return instance;
//    }

    //Double Check Lock(DCL)实现单例  某些情况下会失效
//    public static BaseActivityManager getInstance(){
//        if (instance == null){
//            synchronized (BaseActivityManager.class){
//                if (instance == null){
//                    instance = new BaseActivityManager();
//                }
//            }
//        }
//        return instance;
//    }

    public static BaseActivityManager getInstance() {
        return BaseActivityManagerHolder.instance;
    }

    private static class BaseActivityManagerHolder {
        private static final BaseActivityManager instance = new BaseActivityManager();
    }

    /**
     * @return 栈长度
     */
    public int StackSize(){
        return activityStack.size();
    }

    /***
     * 获得Activity栈
     *
     * @return Activity栈
     */
    public Stack<WeakReference<Activity>> getStack() {
        return activityStack;
    }

    /**
     * 添加指定Activity到堆栈
     */
    public void addActivity(WeakReference<Activity> activity) {
        if (activityStack == null) {
            activityStack = new Stack<>();
        }
        activityStack.add(activity);
    }

    /**
     * 获取当前Activity 栈顶
     */
    public Activity currentActivity() {
        try {
            WeakReference<Activity> activity = activityStack.lastElement();
            return activity.get();
        } catch (Exception e) {
            LogUtil.e(TAG, e.getMessage());
        }

        return null;
    }

    public Context applicationContext() {
        return currentActivity().getApplicationContext();
    }

    /**
     * 结束当前Activity
     */
    public void deleteActivity() {
        try {
            if (activityStack != null) {
                WeakReference<Activity> activity = activityStack.lastElement();
                deleteActivity(activity);
            }
        } catch (Exception e) {
            LogUtil.e(TAG, e.getMessage());
        }

    }

    /**
     * 结束指定的Activity
     */
    public void deleteActivity(WeakReference<Activity> activity) {

        try {
            Iterator<WeakReference<Activity>> iterator = activityStack.iterator();
            while (iterator.hasNext()) {
                WeakReference<Activity> stackActivity = iterator.next();
                if (stackActivity.get() == null) {
                    iterator.remove();
                    continue;
                }
                if (stackActivity.get().getClass().getName().equals(activity.get().getClass().getName())) {
                    iterator.remove();
                    stackActivity.get().finish();
                    break;
                }
            }
        } catch (Exception e) {
            LogUtil.e(TAG, e.getMessage());
        }

    }

    /**
     * 结束指定Class的Activity
     */
    public void deleteActivity(Class<?> cls) {
        try {

            ListIterator<WeakReference<Activity>> listIterator = activityStack.listIterator();
            while (listIterator.hasNext()) {
                Activity activity = listIterator.next().get();
                if (activity == null) {
                    listIterator.remove();
                    continue;
                }
                if (activity.getClass() == cls) {
                    listIterator.remove();
                    if (activity != null) {
                        activity.finish();
                    }
                    break;
                }
            }
        } catch (Exception e) {
            LogUtil.e(TAG, e.getMessage());
        }

    }

    /**
     * 结束全部的Activity
     */
    public void deleteAllActivity() {
        try {
            ListIterator<WeakReference<Activity>> listIterator = activityStack.listIterator();
            while (listIterator.hasNext()) {
                Activity activity = listIterator.next().get();
                if (activity != null) {
                    activity.finish();
                }
                listIterator.remove();
            }
        } catch (Exception e) {
            LogUtil.e(TAG, e.getMessage());
        }

    }

    /**
     * 移除除了某个activity的其他所有activity
     *
     * @param cls 界面
     */
    public void deleteOthersActivity(Class cls) {
        try {
            for (int i = 0; i < activityStack.size(); i++) {
                Activity activity = activityStack.get(i).get();
                Class thisClass = activity.getClass();
                if (thisClass.equals(cls)) {
                    break;
                }
                if (activityStack.get(i) != null) {
                    deleteActivity(activityStack.get(i));
                }
            }
        } catch (Exception e) {
            LogUtil.e(TAG, e.getMessage());
        }
    }

    /**
     * @return 获取指定类名的Activity
     */
    public Activity getActivity(Class<?> cls) {
        try {
            if (activityStack != null) {
                for (WeakReference<Activity> activity : activityStack) {
                    if (activity.getClass().equals(cls)) {
                        return activity.get();
                    }
                }
            }
        } catch (Exception e) {
            LogUtil.e(TAG, e.getMessage());
        }

        return null;
    }

    /**
     * 退出应用程序
     */
    public void AppExit() {
        try {
            deleteAllActivity();
            android.app.ActivityManager activityMgr = (android.app.ActivityManager) applicationContext().getSystemService(Context.ACTIVITY_SERVICE);
            activityMgr.restartPackage(applicationContext().getPackageName());
            System.exit(0);
        } catch (Exception e) {
            LogUtil.e(TAG, e.getMessage());
        }
    }
}
