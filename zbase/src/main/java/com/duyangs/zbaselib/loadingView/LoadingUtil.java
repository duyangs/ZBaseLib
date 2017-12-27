package com.duyangs.zbaselib.loadingView;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.view.WindowManager;

import com.duyangs.zbaselib.util.LogUtil;

/**
 * "loading" ZBaseLib
 * create by DuYang
 * e-mail:duyangs1994@gmail.com
 * update time 2017/12/27.
 */

public class LoadingUtil {

    private static ShapeLoadingDialog shapeLoadingDialog;

    public static void show(Context context, String msg) {
        try {
            showNow(context, msg, false);
        } catch (WindowManager.BadTokenException e) {
            LogUtil.e("LoadingUtil", e.getMessage());
            dismiss();
            showNow(context, msg, false);
        }
    }

    public static void showCanCancel(Context context, String msg) {
        try {
            showNow(context, msg, true);
        } catch (WindowManager.BadTokenException e) {
            LogUtil.e("LoadingUtil", e.getMessage());
            dismiss();
            showNow(context, msg, true);
        }

    }

    public static void dismiss() {
        if (shapeLoadingDialog != null) {
            shapeLoadingDialog.dismiss();
            shapeLoadingDialog = null;
        }

    }

    private static void showNow(Context context, String msg, boolean isCancel) {

        if (shapeLoadingDialog == null) {
            shapeLoadingDialog = new ShapeLoadingDialog.Builder(context)
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
