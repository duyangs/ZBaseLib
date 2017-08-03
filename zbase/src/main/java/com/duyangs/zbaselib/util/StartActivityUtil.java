package com.duyangs.zbaselib.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.duyangs.zbaselib.R;


/**
 * <p>Project:BaseLibDemo</p>
 * <p>Package:com.duyangs.baselib.util</p>
 * <p>Description:Activity 跳转工具类</p>
 * <p>Company:</p>
 *
 * @author duyangs
 * @date 2017/05/08 0008
 */
public class StartActivityUtil {
    /**
     * 页面跳转
     *
     * @param activity 当前activity
     * @param clz 要跳转的类
     */
    public static void startActivity(Context context , Class<?> clz) {
        context.startActivity(new Intent(context,clz));
        if (context instanceof Activity) {
            ((Activity)context).overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        }
    }

    /**
     * 携带数据的页面跳转
     *
     * @param activity 当前activity
     * @param clz 要跳转的类
     * @param bundle 数据
     */
    public static void startActivity(Context context, Class<?> clz, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(context, clz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
        if (context instanceof Activity) {
            ((Activity)context).overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        }
    }

    /**
     * 含有Bundle通过Class打开编辑界面
     *
     * @param context 当前activity 当前调用的是startActivityForResult 必须传递activity参数
     * @param cls 要跳转的类
     * @param bundle 携带的数据
     * @param requestCode 请求码
     */
    public static void startActivityForResult(Activity context, Class<?> cls, Bundle bundle,
                                              int requestCode) {
        Intent intent = new Intent();
        intent.setClass(context, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivityForResult(intent, requestCode);
//        if (context instanceof Activity) {
        context.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
//        }
    }
}
