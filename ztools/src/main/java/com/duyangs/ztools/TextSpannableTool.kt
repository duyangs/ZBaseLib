package com.duyangs.ztools

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.support.annotation.ColorInt
import android.text.Layout
import android.text.SpannableString
import android.text.Spanned
import android.text.TextUtils
import android.text.method.LinkMovementMethod
import android.text.style.*
import android.util.TypedValue
import android.view.View
import android.widget.TextView
import java.util.*

/**
 *
 * Project:BaseLibDemo
 *
 * Package:com.duyangs.baselib.util
 *
 * Description:TextSpannable 工具类
 *
 * Company:
 *
 * @author duyangs
 * @date 2017/04/24 0024
 */
class TextSpannableTool {
    /**
     * 全部文字
     */
    private val stringBuilder: StringBuilder = StringBuilder()
    /**
     * 样式
     */
    private val style: MutableList<Any>
    /**
     * 样式使用位置
     */
    private val position: MutableList<Array<Int>>

    init {
        style = LinkedList()
        position = LinkedList()
    }

    /**
     * 添加
     *
     * @param text            文字
     * @param parcelableSpans 样式
     * @return 文字样式拼接
     */
    private fun addText(text: String?, vararg parcelableSpans: Any): TextSpannableTool {

        if (TextUtils.isEmpty(text)) {
            return this
        }
        val start = stringBuilder.length
        stringBuilder.append(text)
        val end = stringBuilder.length
        if (parcelableSpans != null)
            for (parcelableSpan in parcelableSpans) {
                position.add(arrayOf(start, end))
                style.add(parcelableSpan)
            }
        return this
    }

    /**
     * 添加
     *
     * @param text      文字
     * @param textStyle 样式
     * @return 文字样式拼接
     */
    fun addText(text: String, textStyle: TextStyle): TextSpannableTool {
        addText(text, *textStyle.toParcelableSpanList())
        return this
    }

    /**
     * 添加
     *
     * @param text 文字
     */
    fun addText(text: String): TextSpannableTool {
        addText(text, *(null as Array<Any>?)!!)
        return this
    }

    /**
     * 添加
     *
     * @param textStyle 样式
     * @return 文字样式拼接
     */
    fun addText(textStyle: TextStyle): TextSpannableTool {
        addText(textStyle.text, *textStyle.toParcelableSpanList())
        return this
    }

    /**
     * 清空里面数据
     *
     * @return 文字样式拼接
     */
    fun clear(): TextSpannableTool {
        stringBuilder.delete(0, stringBuilder.length)
        style.clear()
        position.clear()
        return this
    }

    /**
     * 生成多彩文字
     * 输出设置到textView上的
     *
     * @return 多彩文字
     */
    fun toSpannableString(): SpannableString {
        val spannableString = SpannableString(stringBuilder)
        val styleIterator = style.iterator()
        val positionIterator = position.iterator()
        var style: Any
        var position: Array<Int>
        while (styleIterator.hasNext()) {
            style = styleIterator.next()
            position = positionIterator.next()
            spannableString.setSpan(style, position[0], position[1], Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
        return spannableString
    }

    /**
     * 文字样式
     */
    class TextStyle {

        private var parameter: Parameter? = null//涉及的参数

        val text: String?
            get() = if (parameter != null) {
                parameter!!.text
            } else ""

        private constructor() {}
        private constructor(builder: Builder) {
            this.parameter = builder.parameter
        }

        fun toParcelableSpanList(): Array<Any> {
            val parcelableSpanList = LinkedList<Any>()
                parcelableSpanList.add(StyleSpan(parameter!!.style!!.getStyle()))

            if (parameter!!.textColor != 0) {
                parcelableSpanList.add(ForegroundColorSpan(parameter!!.textColor))
                parameter!!.textColor = 0
            }

            if (parameter!!.backgroundColor != 0) {
                parcelableSpanList.add(BackgroundColorSpan(parameter!!.backgroundColor))
                parameter!!.backgroundColor = 0
            }
            if (parameter!!.pxSize != -1) {
                parcelableSpanList.add(AbsoluteSizeSpan(parameter!!.pxSize))
                parameter!!.pxSize = -1
            }
            if (parameter!!.dipSize != -1) {
                parcelableSpanList.add(AbsoluteSizeSpan(parameter!!.dipSize, true))
                parameter!!.dipSize = -1
            }
            if (parameter!!.underline) {
                parcelableSpanList.add(UnderlineSpan())
            }
            if (parameter!!.strikethrough) {
                parcelableSpanList.add(StrikethroughSpan())
            }
            if (!TextUtils.isEmpty(parameter!!.url)) {
                parcelableSpanList.add(URLSpan(parameter!!.url))
            }
            if (parameter!!.onClickListener != null) {
                parcelableSpanList.add(SimpleClickableSpan(parameter!!.onClickListener!!))
                parameter!!.onClickListener = null
            }

            if (parameter!!.superscriptsAndSubscripts != null) {
                when (parameter!!.superscriptsAndSubscripts) {
                    TextSpannableTool.TextStyle.SuperscriptsAndSubscripts.SUPERSCRIPT -> parcelableSpanList.add(SuperscriptSpan())
                    TextSpannableTool.TextStyle.SuperscriptsAndSubscripts.SUBSCRIPT -> parcelableSpanList.add(SubscriptSpan())
                }
            }

            if (parameter!!.isLeadingMargin) {//设置缩进
                parcelableSpanList.add(LeadingMarginSpan.Standard(parameter!!.first, parameter!!.rest))
                parameter!!.isLeadingMargin = false
            }

            if (parameter!!.quoteColor != parameter!!.defaultValue) {//设置引用
                parcelableSpanList.add(QuoteSpan(parameter!!.quoteColor))
                parameter!!.quoteColor = parameter!!.defaultValue
            }

            if (parameter!!.isBullet) {//设置列表项
                parcelableSpanList.add(BulletSpan(parameter!!.gapWidth, parameter!!.bulletColor))
                parameter!!.isBullet = false
            }

            if (parameter!!.proportion != -1f) {//设置几倍字体
                parcelableSpanList.add(RelativeSizeSpan(parameter!!.proportion))
                parameter!!.proportion = -1f
            }

            if (parameter!!.xProportion != -1f) {//设置横向几倍字体
                parcelableSpanList.add(ScaleXSpan(parameter!!.xProportion))
                parameter!!.xProportion = -1f
            }

            if (parameter!!.fontFamily != null) {//设置字体
                parcelableSpanList.add(TypefaceSpan(parameter!!.fontFamily))
                parameter!!.fontFamily = null
            }

            if (parameter!!.align != null) {//设置对齐方式
                parcelableSpanList.add(AlignmentSpan.Standard(parameter!!.align))
                parameter!!.align = null
            }

            //设置图片
            if (parameter!!.imageSpan != null) {
                parcelableSpanList.add(parameter!!.imageSpan!!)
                parameter!!.imageSpan = null
            }



            return parcelableSpanList.toTypedArray()
        }

        private fun newBuilder(): Builder {
            return Builder(this)
        }

        class Builder {

            internal var parameter: Parameter? = null

            constructor() {
                parameter = Parameter()
                parameter!!.textColor = 0
                parameter!!.backgroundColor = 0
                parameter!!.pxSize = -1
                parameter!!.dipSize = -1
                parameter!!.proportion = -1f
                parameter!!.xProportion = -1f
            }

            internal constructor(textStyle: TextStyle) {
                this.parameter = textStyle.parameter
            }

            /**
             * 设置字体大小
             *
             * @param spSize sp
             * @return Builder
             */
            fun setAbsoluteSizeSpSpan(context: Context, spSize: Int): Builder {
                val metrics = context.resources.displayMetrics
                parameter!!.pxSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, spSize.toFloat(), metrics).toInt()
                return this
            }

            /**
             * 设置字体大小
             *
             * @param dipSize dip
             * @return Builder
             */
            fun setAbsoluteSizeDipSpan(dipSize: Int): Builder {
                parameter!!.dipSize = dipSize
                return this
            }

            /**
             * 设置字体大小
             *
             * @param pxSize px
             * @return Builder
             */
            fun setAbsoluteSizePxSpan(pxSize: Int): Builder {
                parameter!!.pxSize = pxSize
                return this
            }

            /**
             * 添加文字颜色
             *
             * @param textColor 文字颜色
             * @return Builder
             */
            fun setTextColorSpan(textColor: Int): Builder {
                parameter!!.textColor = textColor
                return this
            }

            /**
             * 添加背景颜色
             *
             * @param backgroundColor 背景颜色
             * @return Builder
             */
            fun setBackgroundColorSpan(backgroundColor: Int): Builder {
                parameter!!.backgroundColor = backgroundColor
                return this
            }

            /**
             * 添加文字样式
             *
             * @param style 文字样式
             * @return Builder
             */
            fun setStyleSpan(style: Style): Builder {
                parameter!!.style = style
                return this
            }


            /**
             * 添加下划线
             *
             * @param underline 是否下划线
             * @return Builder
             */
            fun setUnderlineSpan(underline: Boolean): Builder {
                parameter!!.underline = underline
                return this
            }

            /**
             * 添加删除线
             *
             * @param strikethrough 是否要删除线
             * @return Builder
             */
            fun setStrikethroughSpan(strikethrough: Boolean): Builder {
                parameter!!.strikethrough = strikethrough
                return this
            }

            /**
             * 上标和下标
             *
             * @param superscriptsAndSubscripts superscriptsAndSubscripts
             */
            fun setSuperscriptsAndSubscripts(superscriptsAndSubscripts: SuperscriptsAndSubscripts): Builder {
                parameter!!.superscriptsAndSubscripts = superscriptsAndSubscripts
                return this
            }

            /**
             * 超级链接（需要添加setMovementMethod方法附加响应）
             * textView.setMovementMethod(LinkMovementMethod.getInstance());
             *
             * @param textView 要显示的控件
             * @param url      地址
             */
            fun setURL(textView: TextView, url: String): Builder {
                textView.movementMethod = LinkMovementMethod.getInstance()
                parameter!!.url = url
                return this
            }

            /**
             * 点击事件（需要添加setMovementMethod方法附加响应）
             * textView.setMovementMethod(LinkMovementMethod.getInstance());
             *
             * @param textView        要显示的控件
             * @param onClickListener 点击事件
             */
            fun setOnClickListener(textView: TextView, onClickListener: View.OnClickListener): Builder {
                textView.movementMethod = LinkMovementMethod.getInstance()
                parameter!!.onClickListener = onClickListener
                return this
            }

            /**
             * @param context           上下文
             * @param bitmap            图片
             * @param verticalAlignment 可以使用 [DynamicDrawableSpan.ALIGN_BOTTOM](底部对齐) 或
             * [DynamicDrawableSpan.ALIGN_BASELINE](对齐基线).
             */
            fun setImage(context: Context, bitmap: Bitmap, verticalAlignment: Int): Builder {
                parameter!!.imageSpan = ImageSpan(context, bitmap, verticalAlignment)
                return this
            }

            /**
             * @param context           上下文
             * @param drawable          图片
             * @param verticalAlignment 可以使用 [DynamicDrawableSpan.ALIGN_BOTTOM](底部对齐) 或
             * [DynamicDrawableSpan.ALIGN_BASELINE](对齐基线).
             */
            fun setImage(context: Context, drawable: Drawable, verticalAlignment: Int): Builder {
                parameter!!.imageSpan = ImageSpan(drawable, verticalAlignment)
                return this
            }

            /**
             * @param context           上下文
             * @param resourceId        图片
             * @param verticalAlignment 可以使用 [DynamicDrawableSpan.ALIGN_BOTTOM](底部对齐) 或
             * [DynamicDrawableSpan.ALIGN_BASELINE](对齐基线).
             */
            fun setImage(context: Context, resourceId: Int, verticalAlignment: Int): Builder {
                parameter!!.imageSpan = ImageSpan(context, resourceId, verticalAlignment)
                return this
            }

            /**
             * 设置缩进
             *
             * @param first 首行缩进
             * @param rest  剩余行缩进
             * @return Builder
             */
            fun setLeadingMargin(first: Int, rest: Int): Builder {
                parameter!!.first = first
                parameter!!.rest = rest
                parameter!!.isLeadingMargin = true
                return this
            }

            /**
             * 设置引用线的颜色
             *
             * @param color 引用线的颜色
             * @return Builder
             */
            fun setQuoteColor(@ColorInt color: Int): Builder {
                parameter!!.quoteColor = color
                return this
            }

            /**
             * 设置列表标记
             *
             * @param gapWidth 列表标记和文字间距离
             * @param color    列表标记的颜色
             * @return Builder
             */
            fun setBullet(gapWidth: Int, color: Int): Builder {
                parameter!!.gapWidth = gapWidth
                parameter!!.bulletColor = color
                parameter!!.isBullet = true
                return this
            }

            /**
             * 设置字体比例
             *
             * @param proportion 比例
             * @return Builder
             */
            fun setProportion(proportion: Float): Builder {
                parameter!!.proportion = proportion
                return this
            }

            /**
             * 设置字体横向比例
             *
             * @param proportion 比例
             * @return Builder
             */
            fun setXProportion(proportion: Float): Builder {
                parameter!!.xProportion = proportion
                return this
            }

            /**
             * 设置字体
             *
             * @param fontFamily 字体
             *
             *  * monospace
             *  * serif
             *  * sans-serif
             *
             * @return Builder
             */
            fun setFontFamily(fontFamily: String?): Builder {
                parameter!!.fontFamily = fontFamily
                return this
            }

            /**
             * 设置对齐
             *
             * @param align 对其方式
             *
             *  * [Layout.Alignment.ALIGN_NORMAL]正常
             *  * [Layout.Alignment.ALIGN_OPPOSITE]相反
             *  * [Layout.Alignment.ALIGN_CENTER]居中
             *
             * @return Builder
             */
            fun setAlign(align: Layout.Alignment?): Builder {
                parameter!!.align = align
                return this
            }


            /**
             * 设置文字
             *
             * @param text 文字
             */
            fun setText(text: String): Builder {
                parameter!!.text = text
                return this
            }

            fun build(): TextStyle {
                return TextStyle(this)
            }

        }


        /**
         * 文字样式
         * Android不推荐用枚举 由于枚举比int型更占用内存
         * 个人觉着枚举更好用，就不做这个优化
         */
        enum class Style constructor(private var style: Int) {
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

            fun getStyle(): Int {
                return style
            }
        }

        /**
         * 上标和下标
         */
        enum class SuperscriptsAndSubscripts {
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
        internal class Parameter {

            val defaultValue = 0x12000000

            /**
             * 文字内容
             */
            var text: String? = null
            /**
             * 文字样式
             */
            var style: Style? = Style.NORMAL
            /**
             * 文字颜色
             */
            var textColor: Int = 0
            /**
             * 背景颜色
             */
            var backgroundColor: Int = 0
            /**
             * 文字大小
             */
            var pxSize: Int = 0
            /**
             * 文字大小
             */
            var dipSize: Int = 0
            /**
             * 下划线
             */
            var underline: Boolean = false
            /**
             * 删除线
             */
            var strikethrough: Boolean = false
            /**
             * 连接地址
             */
            var url: String? = null
            /**
             * 上标和下标
             */
            var superscriptsAndSubscripts: SuperscriptsAndSubscripts? = null
            /**
             * 点击事件
             */
            var onClickListener: View.OnClickListener? = null
            /**
             * 图像跨度
             */
            var imageSpan: ImageSpan? = null

            var isLeadingMargin: Boolean = false//是否缩进
            var first: Int = 0//首行缩进
            var rest: Int = 0//剩余行缩进

            var quoteColor: Int = 0//引用线的颜色

            var isBullet: Boolean = false//是否列表标记
            var gapWidth: Int = 0//列表标记和文字间距离
            var bulletColor: Int = 0//列表标记的颜色

            var proportion: Float = 0.toFloat()//比例(设置几倍字体)

            var xProportion: Float = 0.toFloat()//比例（设置横向几倍字体）

            var fontFamily: String? = null//字体

            var align: Layout.Alignment? = null//对齐方式
        }
    }

    internal class SimpleClickableSpan(private val onClickListener: View.OnClickListener) : ClickableSpan() {

        override fun onClick(widget: View) {
            onClickListener.onClick(widget)
        }
    }

    companion object {

        val TAG = "--" + TextSpannableTool::class.java.simpleName
    }
}
