package com.duyangs.zbase.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.AlignmentSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.BulletSpan;
import android.text.style.ClickableSpan;
import android.text.style.DynamicDrawableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.LeadingMarginSpan;
import android.text.style.QuoteSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.ScaleXSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.SubscriptSpan;
import android.text.style.SuperscriptSpan;
import android.text.style.TypefaceSpan;
import android.text.style.URLSpan;
import android.text.style.UnderlineSpan;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>Project:BaseLibDemo</p>
 * <p>Package:com.duyangs.baselib.util</p>
 * <p>Description:TextSpannable 工具类</p>
 * <p>Company:</p>
 *
 * @author duyangs
 * @date 2017/04/24 0024
 */
public class TextSpannableUtil {

    public static final String TAG = "--" + TextSpannableUtil.class.getSimpleName();
    /**
     * 全部文字
     */
    private StringBuilder stringBuilder;
    /**
     * 样式
     */
    private List<Object> style;
    /**
     * 样式使用位置
     */
    private List<Integer[]> position;

    public TextSpannableUtil() {
        stringBuilder = new StringBuilder();
        style = new LinkedList<>();
        position = new LinkedList<>();
    }

    /**
     * 添加
     *
     * @param text            文字
     * @param parcelableSpans 样式
     * @return 文字样式拼接
     */
    private TextSpannableUtil addText(String text, Object... parcelableSpans) {

        if (TextUtils.isEmpty(text)) {
            return this;
        }
        int start = stringBuilder.length();
        stringBuilder.append(text);
        int end = stringBuilder.length();
        if (parcelableSpans != null)
            for (Object parcelableSpan : parcelableSpans) {
                position.add(new Integer[]{start, end});
                style.add(parcelableSpan);
            }
        return this;
    }

    /**
     * 添加
     *
     * @param text      文字
     * @param textStyle 样式
     * @return 文字样式拼接
     */
    public TextSpannableUtil addText(String text, TextStyle textStyle) {
        addText(text, textStyle.toParcelableSpanList());
        return this;
    }

    /**
     * 添加
     *
     * @param text 文字
     */
    public TextSpannableUtil addText(String text) {
        addText(text, (Object[]) null);
        return this;
    }

    /**
     * 添加
     *
     * @param textStyle 样式
     * @return 文字样式拼接
     */
    public TextSpannableUtil addText(TextStyle textStyle) {
        addText(textStyle.getText(), textStyle.toParcelableSpanList());
        return this;
    }

    /**
     * 清空里面数据
     *
     * @return 文字样式拼接
     */
    public TextSpannableUtil clear() {
        stringBuilder.delete(0, stringBuilder.length());
        style.clear();
        position.clear();
        return this;
    }

    /**
     * 生成多彩文字
     * 输出设置到textView上的
     *
     * @return 多彩文字
     */
    public SpannableString toSpannableString() {
        SpannableString spannableString = new SpannableString(stringBuilder);
        Iterator<Object> styleIterator = style.iterator();
        Iterator<Integer[]> positionIterator = position.iterator();
        Object style;
        Integer[] position;
        while (styleIterator.hasNext()) {
            style = styleIterator.next();
            position = positionIterator.next();
            spannableString.setSpan(style, position[0], position[1], Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return spannableString;
    }

    /**
     * 文字样式
     */
    public static class TextStyle {

        private Parameter parameter;//涉及的参数

        public String getText() {
            if (parameter != null) {
                return parameter.text;
            }
            return "";
        }

        private TextStyle(){}
        private TextStyle(Builder builder) {
            this.parameter = builder.parameter;
        }

        private Object[] toParcelableSpanList() {
            List<Object> parcelableSpanList = new LinkedList<>();
            if (parameter.style != null) {
                parcelableSpanList.add(new StyleSpan(parameter.style.getStyle()));
            }
            if (parameter.textColor != 0) {
                parcelableSpanList.add(new ForegroundColorSpan(parameter.textColor));
                parameter.textColor = 0;
            }
            if (parameter.backgroundColor != 0) {
                parcelableSpanList.add(new BackgroundColorSpan(parameter.backgroundColor));
                parameter.backgroundColor = 0;
            }
            if (parameter.pxSize != -1) {
                parcelableSpanList.add(new AbsoluteSizeSpan(parameter.pxSize));
                parameter.pxSize = -1;
            } else if (parameter.dipSize != -1) {
                parcelableSpanList.add(new AbsoluteSizeSpan(parameter.dipSize, true));
                parameter.dipSize = -1;
            }
            if (parameter.underline) {
                parcelableSpanList.add(new UnderlineSpan());
            }
            if (parameter.strikethrough) {
                parcelableSpanList.add(new StrikethroughSpan());
            }
            if (!TextUtils.isEmpty(parameter.url)) {
                parcelableSpanList.add(new URLSpan(parameter.url));
            }
            if (parameter.onClickListener != null) {
                parcelableSpanList.add(new SimpleClickableSpan(parameter.onClickListener));
            }
            if (parameter.superscriptsAndSubscripts != null) {
                switch (parameter.superscriptsAndSubscripts) {
                    case SUPERSCRIPT:
                        parcelableSpanList.add(new SuperscriptSpan());
                        break;
                    case SUBSCRIPT:
                        parcelableSpanList.add(new SubscriptSpan());
                        break;
                }
            }

            if (parameter.isLeadingMargin) {//设置缩进
                parcelableSpanList.add(new LeadingMarginSpan.Standard(parameter.first, parameter.rest));
                parameter.isLeadingMargin = false;
            }

            if (parameter.quoteColor != parameter.defaultValue) {//设置引用
                parcelableSpanList.add(new QuoteSpan(parameter.quoteColor));
                parameter.quoteColor = parameter.defaultValue;
            }

            if (parameter.isBullet) {//设置列表项
                parcelableSpanList.add(new BulletSpan(parameter.gapWidth, parameter.bulletColor));
                parameter.isBullet = false;
            }

            if (parameter.proportion != -1) {//设置几倍字体
                parcelableSpanList.add(new RelativeSizeSpan(parameter.proportion));
                parameter.proportion = -1;
            }

            if (parameter.xProportion != -1) {//设置横向几倍字体
                parcelableSpanList.add(new ScaleXSpan(parameter.xProportion));
                parameter.xProportion = -1;
            }

            if (parameter.fontFamily != null) {//设置字体
                parcelableSpanList.add(new TypefaceSpan(parameter.fontFamily));
                parameter.fontFamily = null;
            }

            if (parameter.align != null) {//设置对齐方式
                parcelableSpanList.add(new AlignmentSpan.Standard(parameter.align));
                parameter.align = null;
            }

            if (parameter.imageSpan != null){//设置图片
                parcelableSpanList.add(parameter.imageSpan);
                parameter.imageSpan = null;
            }


            return parcelableSpanList.toArray();
        }

        private Builder newBuilder() {
            return new Builder(this);
        }

        public static class Builder {

            private Parameter parameter;

            public Builder() {
                parameter = new Parameter();
                parameter.textColor = 0;
                parameter.backgroundColor = 0;
                parameter.pxSize = -1;
                parameter.dipSize = -1;
                parameter.proportion = -1;
                parameter.xProportion = -1;
            }

            Builder(TextStyle textStyle) {
                this.parameter = textStyle.parameter;
            }

            /**
             * 设置字体大小
             *
             * @param spSize sp
             * @return Builder
             */
            public Builder setAbsoluteSizeSpSpan(Context context, int spSize) {
                DisplayMetrics metrics = context.getResources().getDisplayMetrics();
                parameter.pxSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, spSize, metrics);
                return this;
            }

            /**
             * 设置字体大小
             *
             * @param dipSize dip
             * @return Builder
             */
            public Builder setAbsoluteSizeDipSpan(int dipSize) {
                parameter.dipSize = dipSize;
                return this;
            }

            /**
             * 设置字体大小
             *
             * @param pxSize px
             * @return Builder
             */
            public Builder setAbsoluteSizePxSpan(int pxSize) {
                parameter.pxSize = pxSize;
                return this;
            }

            /**
             * 添加文字颜色
             *
             * @param textColor 文字颜色
             * @return Builder
             */
            public Builder setTextColorSpan(int textColor) {
                parameter.textColor = textColor;
                return this;
            }

            /**
             * 添加背景颜色
             *
             * @param backgroundColor 背景颜色
             * @return Builder
             */
            public Builder setBackgroundColorSpan(int backgroundColor) {
                parameter.backgroundColor = backgroundColor;
                return this;
            }

            /**
             * 添加文字样式
             *
             * @param style 文字样式
             * @return Builder
             */
            public Builder setStyleSpan(Style style) {
                parameter.style = style;
                return this;
            }


            /**
             * 添加下划线
             *
             * @param underline 是否下划线
             * @return Builder
             */
            public Builder setUnderlineSpan(boolean underline) {
                parameter.underline = underline;
                return this;
            }

            /**
             * 添加删除线
             *
             * @param strikethrough 是否要删除线
             * @return Builder
             */
            public Builder setStrikethroughSpan(boolean strikethrough) {
                parameter.strikethrough = strikethrough;
                return this;
            }

            /**
             * 上标和下标
             *
             * @param superscriptsAndSubscripts superscriptsAndSubscripts
             */
            public Builder setSuperscriptsAndSubscripts(SuperscriptsAndSubscripts superscriptsAndSubscripts) {
                parameter.superscriptsAndSubscripts = superscriptsAndSubscripts;
                return this;
            }

            /**
             * 超级链接（需要添加setMovementMethod方法附加响应）
             * textView.setMovementMethod(LinkMovementMethod.getInstance());
             *
             * @param textView 要显示的控件
             * @param url      地址
             */
            public Builder setURL(TextView textView, String url) {
                textView.setMovementMethod(LinkMovementMethod.getInstance());
                parameter.url = url;
                return this;
            }

            /**
             * 点击事件（需要添加setMovementMethod方法附加响应）
             * textView.setMovementMethod(LinkMovementMethod.getInstance());
             *
             * @param textView        要显示的控件
             * @param onClickListener 点击事件
             */
            public Builder setOnClickListener(TextView textView, View.OnClickListener onClickListener) {
                textView.setMovementMethod(LinkMovementMethod.getInstance());
                parameter.onClickListener = onClickListener;
                return this;
            }

            /**
             * @param context           上下文
             * @param bitmap            图片
             * @param verticalAlignment 可以使用 {@link DynamicDrawableSpan#ALIGN_BOTTOM}(底部对齐) 或
             *                          {@link DynamicDrawableSpan#ALIGN_BASELINE}(对齐基线).
             */
            public Builder setImage(Context context, Bitmap bitmap, int verticalAlignment) {
                parameter.imageSpan = new ImageSpan(context, bitmap, verticalAlignment);
                return this;
            }

            /**
             * @param context           上下文
             * @param drawable          图片
             * @param verticalAlignment 可以使用 {@link DynamicDrawableSpan#ALIGN_BOTTOM}(底部对齐) 或
             *                          {@link DynamicDrawableSpan#ALIGN_BASELINE}(对齐基线).
             */
            public Builder setImage(Context context, Drawable drawable, int verticalAlignment) {
                parameter.imageSpan = new ImageSpan(drawable, verticalAlignment);
                return this;
            }

            /**
             * @param context           上下文
             * @param resourceId        图片
             * @param verticalAlignment 可以使用 {@link DynamicDrawableSpan#ALIGN_BOTTOM}(底部对齐) 或
             *                          {@link DynamicDrawableSpan#ALIGN_BASELINE}(对齐基线).
             */
            public Builder setImage(Context context, int resourceId, int verticalAlignment) {
                parameter.imageSpan = new ImageSpan(context, resourceId, verticalAlignment);
                return this;
            }

            /**
             * 设置缩进
             *
             * @param first 首行缩进
             * @param rest  剩余行缩进
             * @return Builder
             */
            public Builder setLeadingMargin(int first, int rest) {
                parameter.first = first;
                parameter.rest = rest;
                parameter.isLeadingMargin = true;
                return this;
            }

            /**
             * 设置引用线的颜色
             *
             * @param color 引用线的颜色
             * @return Builder
             */
            public Builder setQuoteColor(@ColorInt int color) {
                parameter.quoteColor = color;
                return this;
            }

            /**
             * 设置列表标记
             *
             * @param gapWidth 列表标记和文字间距离
             * @param color    列表标记的颜色
             * @return Builder
             */
            public Builder setBullet(int gapWidth, int color) {
                parameter.gapWidth = gapWidth;
                parameter.bulletColor = color;
                parameter.isBullet = true;
                return this;
            }

            /**
             * 设置字体比例
             *
             * @param proportion 比例
             * @return Builder
             */
            public Builder setProportion(float proportion) {
                parameter.proportion = proportion;
                return this;
            }

            /**
             * 设置字体横向比例
             *
             * @param proportion 比例
             * @return Builder
             */
            public Builder setXProportion(float proportion) {
                parameter.xProportion = proportion;
                return this;
            }

            /**
             * 设置字体
             *
             * @param fontFamily 字体
             *                   <ul>
             *                   <li>monospace</li>
             *                   <li>serif</li>
             *                   <li>sans-serif</li>
             *                   </ul>
             * @return Builder
             */
            public Builder setFontFamily(@Nullable String fontFamily) {
                parameter.fontFamily = fontFamily;
                return this;
            }

            /**
             * 设置对齐
             *
             * @param align 对其方式
             *              <ul>
             *              <li>{@link Layout.Alignment#ALIGN_NORMAL}正常</li>
             *              <li>{@link Layout.Alignment#ALIGN_OPPOSITE}相反</li>
             *              <li>{@link Layout.Alignment#ALIGN_CENTER}居中</li>
             *              </ul>
             * @return Builder
             */
            public Builder setAlign(@Nullable Layout.Alignment align) {
                parameter.align = align;
                return this;
            }


            /**
             * 设置文字
             *
             * @param text 文字
             */
            public Builder setText(String text) {
                parameter.text = text;
                return this;
            }

            public TextStyle build() {
                return new TextStyle(this);
            }

        }


        /**
         * 文字样式
         * Android不推荐用枚举 由于枚举比int型更占用内存
         * 个人觉着枚举更好用，就不做这个优化
         */
        public enum Style {
            /**
             * 正常
             */
            NORMAL(android.graphics.Typeface.NORMAL),
            /**
             * 粗体
             */
            BOLD(android.graphics.Typeface.BOLD),
            /**
             * 斜体
             */
            ITALIC(android.graphics.Typeface.ITALIC),
            /**
             * 粗斜体
             */
            BOLD_ITALIC(android.graphics.Typeface.BOLD_ITALIC);

            private int style;

            Style(int style) {
                this.style = style;
            }

            public int getStyle() {
                return style;
            }
        }

        /**
         * 上标和下标
         */
        public enum SuperscriptsAndSubscripts {
            /**
             * 上标
             */
            SUPERSCRIPT,
            /**
             * 正常
             */
            NORMAL,
            /**
             * 下标
             */
            SUBSCRIPT
        }

        /**
         * 所有参数
         */
        static class Parameter {

            private int defaultValue = 0x12000000;

            /**
             * 文字内容
             */
            private String text;
            /**
             * 文字样式
             */
            private Style style;
            /**
             * 文字颜色
             */
            private int textColor;
            /**
             * 背景颜色
             */
            private int backgroundColor;
            /**
             * 文字大小
             */
            private int pxSize;
            /**
             * 文字大小
             */
            private int dipSize;
            /**
             * 下划线
             */
            private boolean underline;
            /**
             * 删除线
             */
            private boolean strikethrough;
            /**
             * 连接地址
             */
            private String url;
            /**
             * 上标和下标
             */
            private SuperscriptsAndSubscripts superscriptsAndSubscripts;
            /**
             * 点击事件
             */
            private View.OnClickListener onClickListener;
            /**
             * 图像跨度
             */
            private ImageSpan imageSpan;

            private boolean isLeadingMargin;//是否缩进
            private int first;//首行缩进
            private int rest;//剩余行缩进

            private int quoteColor;//引用线的颜色

            private boolean isBullet;//是否列表标记
            private int gapWidth;//列表标记和文字间距离
            private int bulletColor;//列表标记的颜色

            private float proportion;//比例(设置几倍字体)

            private float xProportion;//比例（设置横向几倍字体）

            private String fontFamily;//字体

            private Layout.Alignment align;//对齐方式
        }
    }

    static class SimpleClickableSpan extends ClickableSpan {


        private View.OnClickListener onClickListener;

        SimpleClickableSpan(View.OnClickListener onClickListener) {
            super();
            this.onClickListener = onClickListener;
        }

        @Override
        public void onClick(View widget) {
            onClickListener.onClick(widget);
        }
    }
}
