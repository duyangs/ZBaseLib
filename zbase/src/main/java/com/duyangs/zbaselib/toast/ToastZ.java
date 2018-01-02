package com.duyangs.zbaselib.toast;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.annotation.CheckResult;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.duyangs.zbaselib.R;
import com.duyangs.zbaselib.ZBase;


/**
 * <p>Project:BaseLibDemo</p>
 * <p>Package:com.duyangs.baselib.util</p>
 * <p>Description: Toast 分装工具类</p>
 * <p>Company:</p>
 *
 * @author duyangs
 * @date 2017/04/21 0021
 */
@SuppressLint("InflateParams")
public class ToastZ {

    private static int GRAVITY = Gravity.CENTER;

    @ColorInt
    private static int DEFAULT_TEXT_COLOR = Color.parseColor("#FFFFFF");
    @ColorInt
    private static int ERROR_COLOR = Color.parseColor("#D50000");
    @ColorInt
    private static int INFO_COLOR = Color.parseColor("#3F51B5");
    @ColorInt
    private static int SUCCESS_COLOR = Color.parseColor("#388E3C");
    @ColorInt
    private static int WARNING_COLOR = Color.parseColor("#FFA900");
    @ColorInt
    private static int NORMAL_COLOR = Color.parseColor("#353A3E");

    private static final Typeface LOADED_TOAST_TYPEFACE = Typeface.create("sans-serif-condensed", Typeface.NORMAL);
    private static Typeface currentTypeface = LOADED_TOAST_TYPEFACE;
    private static int textSize = 16; // in SP

    private static boolean tintIcon = true;

    public static final int TYPE_ERROR = 1001;
    public static final int TYPE_INFO = 1002;
    public static final int TYPE_SUCCESS = 1003;
    public static final int TYPE_WARNING = 1004;

    private static Toast mToast;
    private static int nowType = 0;

    private ToastZ() {
        // avoiding instantiation
    }

    public static void normal(Object msg) {
        normal(msg,GRAVITY);
    }

    public static void normal(Object msg, int gravity) {
        normal(msg, gravity, Toast.LENGTH_SHORT);
    }

    public static void normal(Object msg, int gravity, int duration) {
        normal(msg, gravity, duration, null);
    }

    public static void normal(Object msg, int gravity, int duration, Drawable icon) {
        normal(msg, gravity, duration, icon, icon == null ? false : true);
    }

    public static void normal(Object msg, int gravity, int duration, Drawable icon, boolean withIcon) {
        custom(msg, gravity, icon, NORMAL_COLOR, duration, withIcon, true);
    }

    public static void success(Object msg) {
        success(msg,GRAVITY);
    }

    public static void success(Object msg, int gravity) {
        success(msg,gravity,Toast.LENGTH_SHORT);
    }

    public static void success(Object msg, int gravity, int duration) {
        success(msg,gravity,duration,true);
    }

    public static void success(Object msg, int gravity, int duration,boolean withIcon) {
        custom(msg, gravity, ToastZUtil.getDrawable(ZBase.getContext(), R.drawable.ic_check_white_48dp),
                SUCCESS_COLOR, duration, withIcon, true);
    }

    public static void info(Object msg) {
        info(msg,GRAVITY);
    }

    public static void info(Object msg, int gravity) {
        info(msg,gravity,Toast.LENGTH_SHORT);
    }

    public static void info(Object msg, int gravity, int duration) {
        info(msg,gravity,duration,true);
    }

    public static void info(Object msg, int gravity, int duration,boolean withIcon) {
        custom(msg, gravity, ToastZUtil.getDrawable(ZBase.getContext(), R.drawable.ic_info_outline_white_48dp),
                INFO_COLOR, duration, withIcon, true);
    }

    public static void warning(Object msg) {
        warning(msg,GRAVITY);
    }

    public static void warning(Object msg, int gravity) {
        warning(msg,gravity,Toast.LENGTH_SHORT);
    }

    public static void warning(Object msg, int gravity, int duration) {
        warning(msg,gravity,duration,true);
    }

    public static void warning(Object msg, int gravity, int duration,boolean withIcon) {
        custom(msg, gravity, ToastZUtil.getDrawable(ZBase.getContext(), R.drawable.ic_error_outline_white_48dp),
                WARNING_COLOR, duration, withIcon, true);
    }

    public static void error(Object msg) {
        error(msg,GRAVITY);
    }

    public static void error(Object msg, int gravity) {
        error(msg,gravity,Toast.LENGTH_SHORT);
    }

    public static void error(Object msg, int gravity, int duration) {
       error(msg,gravity,duration,true);
    }

    public static void error(Object msg, int gravity, int duration,boolean withIcon) {
        custom(msg, gravity, ToastZUtil.getDrawable(ZBase.getContext(), R.drawable.ic_clear_white_48dp),
                ERROR_COLOR, duration, withIcon, true);
    }


    public static void custom(@NonNull Object msg, Drawable icon, int tintColor, int duration) {
        custom(msg, GRAVITY, icon, tintColor, duration, true, false);
    }

    public static void custom(@NonNull Object msg, Drawable icon, int tintColor, int duration, boolean withIcon) {
        custom(msg, GRAVITY, icon, tintColor, duration, withIcon, false);
    }

    public static void custom(@NonNull Object msg, @DrawableRes int iconRes, @ColorInt int tintColor, int duration,
                              boolean withIcon, boolean shouldTint) {
        custom(msg, GRAVITY, ToastZUtil.getDrawable(ZBase.getContext(), iconRes), tintColor, duration, withIcon, shouldTint);
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

    public static TextView toastTextView;

    private static void custom(@NonNull Object msg, int gravity, Drawable icon,
                               @ColorInt int tintColor, int duration,
                               boolean withIcon, boolean shouldTint) {

        cancelToast();

        String showMsg = "";
        if (msg instanceof Integer) {
            showMsg = ZBase.getContext().getString((Integer) msg);
        } else if (msg instanceof String) {
            showMsg = (String) msg;
        }

        mToast = Toast.makeText(ZBase.getContext(), null, duration);
        final View toastLayout = ((LayoutInflater) ZBase.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE))
                .inflate(R.layout.toast_layout, null);
        final ImageView toastIcon = toastLayout.findViewById(R.id.toast_icon);
        toastTextView = toastLayout.findViewById(R.id.toast_text);
        Drawable drawableFrame;

        if (shouldTint)
            drawableFrame = ToastZUtil.tint9PatchDrawableFrame(ZBase.getContext(), tintColor);
        else
            drawableFrame = ToastZUtil.getDrawable(ZBase.getContext(), R.drawable.toast_frame);
        ToastZUtil.setBackground(toastLayout, drawableFrame);

        if (withIcon) {
            if (icon == null)
                throw new IllegalArgumentException("Avoid passing 'icon' as null if 'withIcon' is set to true");
            if (tintIcon)
                icon = ToastZUtil.tintIcon(icon, DEFAULT_TEXT_COLOR);
            ToastZUtil.setBackground(toastIcon, icon);
        } else {
            toastIcon.setVisibility(View.GONE);
        }

        toastTextView.setText(showMsg);
        toastTextView.setTextColor(DEFAULT_TEXT_COLOR);
        toastTextView.setTypeface(currentTypeface);
        toastTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);

        mToast.setView(toastLayout);
        mToast.setGravity(gravity == -1 ? GRAVITY : gravity, 0, (gravity == Gravity.CENTER) ? 0 : 100);

        mToast.show();
    }

    public static class Config {
        private static int GRAVITY = Gravity.CENTER;
        @ColorInt
        private int DEFAULT_TEXT_COLOR = ToastZ.DEFAULT_TEXT_COLOR;
        @ColorInt
        private int ERROR_COLOR = ToastZ.ERROR_COLOR;
        @ColorInt
        private int INFO_COLOR = ToastZ.INFO_COLOR;
        @ColorInt
        private int SUCCESS_COLOR = ToastZ.SUCCESS_COLOR;
        @ColorInt
        private int WARNING_COLOR = ToastZ.WARNING_COLOR;

        private Typeface typeface = ToastZ.currentTypeface;
        private int textSize = ToastZ.textSize;

        private boolean tintIcon = ToastZ.tintIcon;

        private Config() {
            // avoiding instantiation
        }

        @CheckResult
        public static ToastZ.Config getInstance() {
            return new ToastZ.Config();
        }

        public static void reset() {
            ToastZ.GRAVITY = Gravity.CENTER;
            ToastZ.DEFAULT_TEXT_COLOR = Color.parseColor("#FFFFFF");
            ToastZ.ERROR_COLOR = Color.parseColor("#D50000");
            ToastZ.INFO_COLOR = Color.parseColor("#3F51B5");
            ToastZ.SUCCESS_COLOR = Color.parseColor("#388E3C");
            ToastZ.WARNING_COLOR = Color.parseColor("#FFA900");
            ToastZ.currentTypeface = LOADED_TOAST_TYPEFACE;
            ToastZ.textSize = 16;
            ToastZ.tintIcon = true;
        }

        public ToastZ.Config setGravity(int gravity) {
            GRAVITY = gravity;
            return this;
        }

        @CheckResult
        public ToastZ.Config setTextColor(@ColorInt int textColor) {
            DEFAULT_TEXT_COLOR = textColor;
            return this;
        }

        @CheckResult
        public ToastZ.Config setErrorColor(@ColorInt int errorColor) {
            ERROR_COLOR = errorColor;
            return this;
        }

        @CheckResult
        public ToastZ.Config setInfoColor(@ColorInt int infoColor) {
            INFO_COLOR = infoColor;
            return this;
        }

        @CheckResult
        public ToastZ.Config setSuccessColor(@ColorInt int successColor) {
            SUCCESS_COLOR = successColor;
            return this;
        }

        @CheckResult
        public ToastZ.Config setWarningColor(@ColorInt int warningColor) {
            WARNING_COLOR = warningColor;
            return this;
        }

        @CheckResult
        public ToastZ.Config setToastTypeface(@NonNull Typeface typeface) {
            this.typeface = typeface;
            return this;
        }

        @CheckResult
        public ToastZ.Config setTextSize(int sizeInSp) {
            this.textSize = sizeInSp;
            return this;
        }

        @CheckResult
        public ToastZ.Config tintIcon(boolean tintIcon) {
            this.tintIcon = tintIcon;
            return this;
        }

        public void apply() {
            ToastZ.GRAVITY = GRAVITY;
            ToastZ.DEFAULT_TEXT_COLOR = DEFAULT_TEXT_COLOR;
            ToastZ.ERROR_COLOR = ERROR_COLOR;
            ToastZ.INFO_COLOR = INFO_COLOR;
            ToastZ.SUCCESS_COLOR = SUCCESS_COLOR;
            ToastZ.WARNING_COLOR = WARNING_COLOR;
            ToastZ.currentTypeface = typeface;
            ToastZ.textSize = textSize;
            ToastZ.tintIcon = tintIcon;
        }
    }
}
