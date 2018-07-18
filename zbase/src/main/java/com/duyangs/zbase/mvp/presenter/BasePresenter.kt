package com.duyangs.zbase.mvp.presenter

import android.os.Handler
import android.os.Looper
import android.util.Log

import com.duyangs.zbase.mvp.MvpAutowire
import com.duyangs.zbase.mvp.view.BaseView

import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method
import java.lang.reflect.Proxy

/**
 * 创建者：梁建军
 * 创建日期： 2017/5/22
 * 创建时间： 16:21
 * BasePresenter
 * 版本：1.0
 * 说明：动态代理，判断调用view中是否在主线程
 * 没有在主线程时候，抛到主线程中运行，并且当前线程等待结果，直到返回时候，继续执行
 */
class BasePresenter<View : BaseView>
/**
 * 基础处理器，用于切换线程
 *
 * @param viewClass 代理的接口
 * @param view      接口的实现
 */
(viewClass: Class<View>, view: View) {

    protected var view: View

    init {
        val l = System.nanoTime()
        //添加动态代理，把方法在主线程中运行
        this.view = Proxy.newProxyInstance(javaClass.classLoader, arrayOf<Class<*>>(viewClass), ProxyHandler(view)) as View
        //查找需要注入的类，自动注入
        MvpAutowire.autowire(this)
        Log.d("--------", "装载时间：" + (System.nanoTime() - l) + "ns")
    }

    /**
     * 代理实际处理类
     *
     * @param <View> 视图实现接口
    </View> */
    private class ProxyHandler<View : BaseView> internal constructor(
            /**
             * view处理接口
             */
            private val view: View) : InvocationHandler {

        @Throws(Throwable::class)
        override fun invoke(proxy: Any, method: Method, args: Array<Any>): Any? {
            //判断有没有在UI线程，在UI线程 直接处理
            if (Thread.currentThread() === Looper.getMainLooper().thread) {
                return method.invoke(view, *args)
            }
            //不在UI线程，把内容抛到主线程中
            val returnObject = ReturnObject()
            handler.post {
                synchronized(returnObject) {
                    try {
                        returnObject.`object` = method.invoke(view, *args)
                    } catch (e: Exception) {
                        returnObject.throwable = e.cause
                    } finally {
                        returnObject.handle = false
                    }
                }
            }
            //等等待主线程中执行完成
            while (returnObject.handle) {
                synchronized(returnObject) {}
            }
            //判断是否异常结束的
            if (returnObject.throwable != null) {
                var throwable = returnObject.throwable
                while (throwable!!.cause != null) {
                    throwable = throwable.cause
                }
                throwable.initCause(RuntimeException("在View中发生异常"))
                throw returnObject.throwable!!
            }
            return returnObject.`object`
        }

        /**
         * 结果传递对象
         */
        private class ReturnObject {
            /**
             * 结果是否运行完成
             */
            @Volatile
            internal var handle/*处理*/ = true
            /**
             * 返回值
             */
            @Volatile
            internal var `object`: Any? = null
            /**
             * 抛出异常时候的异常
             */
            @Volatile
            internal var throwable: Throwable? = null
        }

        companion object {
            /**
             * 主线的Handler
             */
            private val handler = Handler(Looper.getMainLooper())
        }
    }

}
