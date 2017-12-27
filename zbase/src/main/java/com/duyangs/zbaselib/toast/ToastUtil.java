package com.duyangs.zbaselib.toast;

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

    public static final int TYPE_ERROR = 1001;
    public static final int TYPE_INFO = 1002;
    public static final int TYPE_SUCCESS = 1003;
    public static final int TYPE_WARNING = 1004;

    private static Toast mToast;
    private static int nowType = 0;

    /**
     * 居上显示 短时间
     *
     * @param msg 显示类容
     */
    public static void showShortTop(Context context, Object msg, int type) {
        show(context, msg, Gravity.TOP, Toast.LENGTH_SHORT,type);
    }

    /**
     * 居中显示 短时间
     *
     * @param msg 显示类容
     */
    public static void showShortCenter(Context context, Object msg, int type) {
        show(context, msg, Gravity.CENTER, Toast.LENGTH_SHORT,type);
    }

    /**
     * 居下显示 短时间
     *
     * @param msg 显示类容
     */
    public static void showShortBottom(Context context, Object msg, int type) {
        show(context, msg, Gravity.BOTTOM, Toast.LENGTH_SHORT,type);
    }

    /**
     * 居上显示 长时间
     *
     * @param msg 显示类容
     */
    public static void showLongTop(Context context, Object msg, int type) {
        show(context, msg, Gravity.TOP, Toast.LENGTH_LONG,type);
    }

    /**
     * 居中显示 长时间
     *
     * @param msg 显示类容
     */
    public static void showLongCenter(Context context, Object msg, int type) {
        show(context, msg, Gravity.CENTER, Toast.LENGTH_LONG,type);
    }

    /**
     * 居下显示 长时间
     *
     * @param msg 显示类容
     */
    public static void showLongBottom(Context context, Object msg, int type) {
        show(context, msg, Gravity.BOTTOM, Toast.LENGTH_LONG,type);
    }

    /**
     * 显示 不对外开放
     *
     * @param msg     显示类容
     * @param gravity 显示位置
     */
    private static void show(Context context, Object msg, int gravity, int duration, int type) {

        String showMsg = "";
        if (msg instanceof Integer) {
            showMsg = context.getString((Integer) msg);
        } else if (msg instanceof String) {
            showMsg = (String) msg;
        }

        if (mToast == null) {
            mToast = initToast(context, showMsg, duration,type);
        } else {
            //如果当前Toast没有消失， 判断是否与上一个 toast 类型相同
            if (nowType != 0 && nowType == type) {
                //类型相同 直接显示
                Toasty.toastTextView.setText(showMsg);
//                mToast.setText(showMsg);
            }else {
                //类型不同 重新初始化toast
                mToast = initToast(context, showMsg, duration,type);
            }
        }

        nowType = type;
        mToast.setGravity(gravity, 0, 100);
        mToast.show();
    }

    private static Toast initToast(Context context, String showMsg, int duration, int type) {
        Toast toast = null;
        switch (type) {
            case TYPE_ERROR:
                toast = Toasty.error(context, showMsg, duration, true);
                break;
            case TYPE_INFO:
                toast = Toasty.info(context, showMsg, duration, true);
                break;
            case TYPE_SUCCESS:
                toast = Toasty.success(context, showMsg, duration, true);
                break;
            case TYPE_WARNING:
                toast = Toasty.warning(context, showMsg, duration, true);
                break;
            default:
                toast = Toasty.info(context, showMsg, duration, true);
                break;
        }

        return toast;
    }

    /**
     * 取消toast
     */
    public static void cancelToast() {
        if (mToast != null) {
            mToast.cancel();
            mToast = null;
        }
    }
}
