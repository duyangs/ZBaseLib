package com.duyangs.zbaselib.loadingView;

import android.app.Activity;

/**
 * "loading" ZBaseLib
 * create by DuYang
 * e-mail:duyangs1994@gmail.com
 * update time 2017/12/27.
 */

public class LoadingUtil {

    private static ShapeLoadingDialog shapeLoadingDialog;

    public static void show(Activity activity, String msg) {
        showNow(activity,msg,false);
    }

    public static void showCanCancel(Activity activity, String msg) {
        showNow(activity,msg,true);
    }

    public static void dismiss() {
        if (shapeLoadingDialog != null) {
            shapeLoadingDialog.dismiss();
            shapeLoadingDialog = null;
        }

    }

    private static void showNow(Activity activity,String msg,boolean isCancel){

        if (shapeLoadingDialog == null) {
            shapeLoadingDialog = new ShapeLoadingDialog.Builder(activity)
                    .build();
        }
        if (msg != null) {
            shapeLoadingDialog.getBuilder().loadText(msg).build();
        }

        shapeLoadingDialog.getBuilder()
                .cancelable(isCancel)
                .canceledOnTouchOutside(isCancel)
                .build();

        shapeLoadingDialog.show();
    }

}
