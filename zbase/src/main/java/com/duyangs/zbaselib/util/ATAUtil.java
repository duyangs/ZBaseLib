package com.duyangs.zbaselib.util;

import android.app.Activity;
import android.os.Build;

import com.duyangs.zbaselib.R;

/**
 * "页面启动 动画util" ZBaseLib
 * ATA => Activity Transition Anim
 * create by DuYang
 * e-mail:duyangs1994@gmail.com
 * update time 2017/12/27.
 */

public class ATAUtil {

    public static void in(Activity activity){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            activity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        }
    }

    public static void out(Activity activity){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            activity.overridePendingTransition(R.anim.slide_in_from_left, R.anim.slide_out_to_right);
        }
    }

}
