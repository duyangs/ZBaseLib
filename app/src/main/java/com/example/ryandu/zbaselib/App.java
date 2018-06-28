package com.example.ryandu.zbaselib;

import android.app.Application;

import com.duyangs.zbaselib.ZBase;
import com.duyangs.zbaselib.util.LogUtil;


/**
 * <p>Project:BaseLibDemo</p>
 * <p>Package:com.duyangs.baselibdemo</p>
 * <p>Description:</p>
 * <p>Company:</p>
 *
 * @author duyangs
 * @date 2017/05/02 0002
 */
public class App extends Application {

    public LogUtil.Builder logBuilder;

    private App appContext;

    public App getApp(){return appContext;}

    @Override
    public void onCreate() {
        super.onCreate();

        /*
          初始化 LogUtil 并设置是否显示日志
         */
        logBuilder = new LogUtil.Builder().setLogOn(true);

        ZBase.init(this);
    }
}
