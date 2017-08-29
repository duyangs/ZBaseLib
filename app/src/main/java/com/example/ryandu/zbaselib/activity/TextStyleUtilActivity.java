package com.example.ryandu.zbaselib.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Layout;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.text.style.DynamicDrawableSpan;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.duyangs.zbaselib.BaseActivity;
import com.duyangs.zbaselib.util.LogUtil;
import com.duyangs.zbaselib.util.TextSpannableUtil;
import com.example.ryandu.zbaselib.R;


/**
 * <p>Project:BaseLibDemo</p>
 * <p>Package:com.duyangs.baselibdemo</p>
 * <p>Description:</p>
 * <p>Company:</p>
 *
 * @author duyangs
 * @date 2017/04/21 0021
 */
public class TextStyleUtilActivity extends BaseActivity {
    private LinearLayout linearLayout;

    @Override
    protected int bindLayout() {
        return R.layout.activity_text_style;
    }

    @Override
    public void initParams(Bundle params) {

    }

    @Override
    protected void initView() {
        linearLayout = findById(R.id.ac_text_style_layout);

    }

    @Override
    protected void initData() {
        //不带样式的文字
        TextView textView = new TextView(this);


        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                toast("事件触发了");
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                ds.setColor(Color.BLUE);
                ds.setUnderlineText(false);
            }
        };


        TextView textView1 = new TextView(this);
        long time = System.nanoTime();
        for (int i = 0; i < 200; i++) {
            textView1.setText(new TextSpannableUtil()
                    .addText(new TextSpannableUtil.TextStyle.Builder().setText("正常\n")
                            .build())
                    .addText(new TextSpannableUtil.TextStyle.Builder().setText("字号大\n")
                            .setAbsoluteSizeSpSpan(this, 25)
                            .build())
                    .addText(new TextSpannableUtil.TextStyle.Builder().setText("前景色\n")
                            .setTextColorSpan(Color.RED)
                            .build())
                    .addText(new TextSpannableUtil.TextStyle.Builder().setText("背景色\n")
                            .setBackgroundColorSpan(Color.BLUE)
                            .build())
                    .addText(new TextSpannableUtil.TextStyle.Builder().setText("正常\n")
                            .setStyleSpan(TextSpannableUtil.TextStyle.Style.NORMAL)
                            .build())
                    .addText(new TextSpannableUtil.TextStyle.Builder().setText("加粗\n")
                            .setStyleSpan(TextSpannableUtil.TextStyle.Style.BOLD)
                            .build())
                    .addText(new TextSpannableUtil.TextStyle.Builder().setText("斜体\n")
                            .setStyleSpan(TextSpannableUtil.TextStyle.Style.ITALIC)
                            .build())
                    .addText(new TextSpannableUtil.TextStyle.Builder().setText("粗斜体\n")
                            .setStyleSpan(TextSpannableUtil.TextStyle.Style.BOLD_ITALIC)
                            .build())
                    .addText(new TextSpannableUtil.TextStyle.Builder()
                            .setText("下划线\n")
                            .setUnderlineSpan(true)
                            .build())
                    .addText(new TextSpannableUtil.TextStyle.Builder()
                            .setText("删除线\n")
                            .setStrikethroughSpan(true)
                            .build())
                    .addText(new TextSpannableUtil.TextStyle.Builder()
                            .setText("上标\n")
                            .setTextColorSpan(Color.RED)
                            .setSuperscriptsAndSubscripts(TextSpannableUtil.TextStyle.SuperscriptsAndSubscripts.SUPERSCRIPT)
                            .build())
                    .addText(new TextSpannableUtil.TextStyle.Builder()
                            .setText("下标\n")
                            .setSuperscriptsAndSubscripts(TextSpannableUtil.TextStyle.SuperscriptsAndSubscripts.SUBSCRIPT)
                            .build())
                    .addText("文字\n", new TextSpannableUtil.TextStyle.Builder()
                            .setStyleSpan(TextSpannableUtil.TextStyle.Style.BOLD)
                            .build())
                    .addText(new TextSpannableUtil.TextStyle.Builder()
                            .setText("缩进\n")
                            .setLeadingMargin(100, 50)
                            .build())
                    .addText(new TextSpannableUtil.TextStyle.Builder()
                            .setText("引用\n")
                            .setQuoteColor(Color.RED)
                            .build())
                    .addText(new TextSpannableUtil.TextStyle.Builder()
                            .setText("列表项\n")
                            .setBullet(100, Color.BLACK)
                            .setAbsoluteSizeDipSpan(30)
                            .build())
                    .addText(new TextSpannableUtil.TextStyle.Builder()
                            .setText("2倍字体\n")
                            .setProportion(2)
                            .build())
                    .addText(new TextSpannableUtil.TextStyle.Builder()
                            .setText("横向2倍字体\n")
                            .setXProportion(2)
                            .build())
                    .addText(new TextSpannableUtil.TextStyle.Builder()
                            .setText("monospace font\n")
                            .setFontFamily("monospace")
                            .build())
                    .addText(new TextSpannableUtil.TextStyle.Builder()
                            .setText("serif font\n")
                            .setFontFamily("serif")
                            .build())
                    .addText(new TextSpannableUtil.TextStyle.Builder()
                            .setText("sans-serif font\n")
                            .setFontFamily("sans-serif")
                            .build())
                    .addText(new TextSpannableUtil.TextStyle.Builder()
                            .setText("测试正常对齐\n")
                            .setAlign(Layout.Alignment.ALIGN_NORMAL)
                            .build())
                    .addText(new TextSpannableUtil.TextStyle.Builder()
                            .setText("测试居中对齐\n")
                            .setAlign(Layout.Alignment.ALIGN_CENTER)
                            .build())
                    .addText(new TextSpannableUtil.TextStyle.Builder()
                            .setText("测试相反对齐\n")
                            .setAlign(Layout.Alignment.ALIGN_OPPOSITE)
                            .build())
                    .addText(new TextSpannableUtil.TextStyle.Builder()
                            .setText("ZBaseLib\n")
                            .setURL(textView1, "https://github.com/duyangs/ZBaseLib")
                            .build())
                    .addText(new TextSpannableUtil.TextStyle.Builder()
                            .setText("点击事件\n")
                            .setOnClickListener(textView1, new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    toast("点击事件");
                                }
                            }).build())
                    .addText(new TextSpannableUtil.TextStyle.Builder()
                            .setText("图片")
                            .setImage(this, R.mipmap.ic_launcher, DynamicDrawableSpan.ALIGN_BASELINE)
                            .build())
                    .toSpannableString());
//
        }
        time = System.nanoTime() - time;
        LogUtil.v(TAG, "耗时" + time + "ns");
        linearLayout.addView(textView1);

    }
}
