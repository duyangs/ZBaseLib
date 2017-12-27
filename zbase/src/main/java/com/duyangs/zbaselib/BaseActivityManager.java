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
public enum  BaseActivityManager {

    INSTANCE;

    private static Stack<Activity> activityStack;

    /**
     * 获取当前Activity
     */
    public Activity currentActivity() {
        Activity activity = activityStack.lastElement();
        return activity;
    }

    /**
     * 添加一个Activity
     */
    public void addActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new Stack<Activity>();
        }
        activityStack.add(activity);
    }

    /**
     * 移除一个Activity
     */
    public void removeActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new Stack<Activity>();
        }
        activityStack.remove(activity);
    }

    /**
     * 结束当前Activity
     */
    public void finishActivity() {
        Activity activity = activityStack.lastElement();
        finishActivity(activity);
    }

    /**
     * 结束一个Activity
     */
    public void finishActivity(Activity activity) {
        if (activity != null) {
            removeActivity(activity);
            activity.finish();
            activity = null;
        }
    }

    /**
     * 结束一个Activity,根据class
     */
    public void finishActivity(Class<?> cls) {
        for (Activity activity : activityStack) {
            if (activity.getClass().equals(cls)) {
                finishActivity(activity);
            }
        }
    }

    /**
     * 结束所有Activity
     */
    public void finishAllActivity() {
        for (Activity activity : activityStack) {
            if (null != activity) {
                activity.finish();
            }
        }
        activityStack.clear();
    }

    /**
     * 退出应用程序 -> 杀进程
     */
    public void exit() {
        exit2();
        android.os.Process.killProcess(android.os.Process.myPid());
    }

    /**
     * 退出应用程序 -> 不杀进程,只是关掉所有Activity
     */
    public void exit2() {
        finishAllActivity();
    }
}
