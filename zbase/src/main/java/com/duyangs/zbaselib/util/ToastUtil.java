package com.duyangs.zbaselib.util;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;


/**
 * <p>Project:BaseLibDemo</p>
 * <p>Package:com.duyangs.baselib.util</p>
 * <p>Description: Toast 分装工具类</p>
 * <p>Company:</p>
 *
 * @author duyangs
 * @date 2017/04/21 0021
 */
public class ToastUtil {

    private static Toast mToast;

    /**
     * 居上显示 短时间
     * @param msg 显示类容
     */
    public static void showShortTop(Context context, Object msg){
        show(context,msg, Gravity.TOP, Toast.LENGTH_SHORT);
    }

    /**
     * 居中显示 短时间
     * @param msg 显示类容
     */
    public static void showShortCenter(Context context, Object msg){
        show(context,msg, Gravity.CENTER, Toast.LENGTH_SHORT);
    }

    /**
     * 居下显示 短时间
     * @param msg 显示类容
     */
    public static void showShortBottom(Context context, Object msg){
        show(context,msg, Gravity.BOTTOM, Toast.LENGTH_SHORT);
    }

    /**
     * 居上显示 长时间
     * @param msg 显示类容
     */
    public static void showLongTop(Context context, Object msg){
        show(context,msg, Gravity.TOP, Toast.LENGTH_LONG);
    }

    /**
     * 居中显示 长时间
     * @param msg 显示类容
     */
    public static void showLongCenter(Context context, Object msg){
        show(context,msg, Gravity.CENTER, Toast.LENGTH_LONG);
    }

    /**
     * 居下显示 长时间
     * @param msg 显示类容
     */
    public static void showLongBottom(Context context, Object msg){
        show(context,msg, Gravity.BOTTOM, Toast.LENGTH_LONG);
    }

    /**
     * 显示 不对外开放
     * @param msg 显示类容
     * @param gravity 显示位置
     */
    private static void show(Context context, Object msg, int gravity, int duration){

        String showmsg = "" ;
        if (msg instanceof Integer){
            showmsg = context.getString((Integer) msg);
        }else if (msg instanceof String){
            showmsg = (String) msg;
        }

        if (mToast == null){
            mToast = Toast.makeText(context, showmsg , duration);
        }else {
            //如果当前Toast没有消失， 直接显示内容，不需要重新设置
            mToast.setText(showmsg);
        }
        mToast.setGravity(gravity,0,100);
        mToast.show();
    }

    /**
     * 取消toast
     */
    public static void cancelToast(){
        if (mToast != null) {
            mToast.cancel();
            mToast = null;
        }
    }
}
