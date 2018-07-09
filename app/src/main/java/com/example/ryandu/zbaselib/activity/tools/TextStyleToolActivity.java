package com.example.ryandu.zbaselib.activity.tools;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Layout;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.text.style.DynamicDrawableSpan;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.duyangs.zbase.BaseActivity;
import com.duyangs.zbase.util.LogUtil;
import com.duyangs.zbase.util.StartActivityUtil;
import com.duyangs.ztools.TextSpannableTool;
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
public class TextStyleToolActivity extends BaseActivity {
    private LinearLayout linearLayout;

    public static void actionStart(Context context){
        StartActivityUtil.startActivity(context,TextStyleToolActivity.class);
    }

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
    protected void initData(Bundle savedInstanceState) {
        //不带样式的文字
        TextView textView = new TextView(this);


        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                Log.d(TAG,"事件触发了");
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
            textView1.setText(new TextSpannableTool()
                    .addText(new TextSpannableTool.TextStyle.Builder().setText("正常\n")
                            .build())
                    .addText(new TextSpannableTool.TextStyle.Builder().setText("字号大\n")
                            .setAbsoluteSizeSpSpan(this, 25)
                            .build())
                    .addText(new TextSpannableTool.TextStyle.Builder().setText("前景色\n")
                            .setTextColorSpan(Color.RED)
                            .build())
                    .addText(new TextSpannableTool.TextStyle.Builder().setText("背景色\n")
                            .setBackgroundColorSpan(Color.BLUE)
                            .build())
                    .addText(new TextSpannableTool.TextStyle.Builder().setText("正常\n")
                            .setStyleSpan(TextSpannableTool.TextStyle.Style.NORMAL)
                            .build())
                    .addText(new TextSpannableTool.TextStyle.Builder().setText("加粗\n")
                            .setStyleSpan(TextSpannableTool.TextStyle.Style.BOLD)
                            .build())
                    .addText(new TextSpannableTool.TextStyle.Builder().setText("斜体\n")
                            .setStyleSpan(TextSpannableTool.TextStyle.Style.ITALIC)
                            .build())
                    .addText(new TextSpannableTool.TextStyle.Builder().setText("粗斜体\n")
                            .setStyleSpan(TextSpannableTool.TextStyle.Style.BOLD_ITALIC)
                            .build())
                    .addText(new TextSpannableTool.TextStyle.Builder()
                            .setText("下划线\n")
                            .setUnderlineSpan(true)
                            .build())
                    .addText(new TextSpannableTool.TextStyle.Builder()
                            .setText("删除线\n")
                            .setStrikethroughSpan(true)
                            .build())
                    .addText(new TextSpannableTool.TextStyle.Builder()
                            .setText("上标\n")
                            .setTextColorSpan(Color.RED)
                            .setSuperscriptsAndSubscripts(TextSpannableTool.TextStyle.SuperscriptsAndSubscripts.SUPERSCRIPT)
                            .build())
                    .addText(new TextSpannableTool.TextStyle.Builder()
                            .setText("下标\n")
                            .setSuperscriptsAndSubscripts(TextSpannableTool.TextStyle.SuperscriptsAndSubscripts.SUBSCRIPT)
                            .build())
                    .addText("文字\n", new TextSpannableTool.TextStyle.Builder()
                            .setStyleSpan(TextSpannableTool.TextStyle.Style.BOLD)
                            .build())
                    .addText(new TextSpannableTool.TextStyle.Builder()
                            .setText("缩进\n")
                            .setLeadingMargin(100, 50)
                            .build())
                    .addText(new TextSpannableTool.TextStyle.Builder()
                            .setText("引用\n")
                            .setQuoteColor(Color.RED)
                            .build())
                    .addText(new TextSpannableTool.TextStyle.Builder()
                            .setText("列表项\n")
                            .setBullet(100, Color.BLACK)
                            .setAbsoluteSizeDipSpan(30)
                            .build())
                    .addText(new TextSpannableTool.TextStyle.Builder()
                            .setText("2倍字体\n")
                            .setProportion(2)
                            .build())
                    .addText(new TextSpannableTool.TextStyle.Builder()
                            .setText("横向2倍字体\n")
                            .setXProportion(2)
                            .build())
                    .addText(new TextSpannableTool.TextStyle.Builder()
                            .setText("monospace font\n")
                            .setFontFamily("monospace")
                            .build())
                    .addText(new TextSpannableTool.TextStyle.Builder()
                            .setText("serif font\n")
                            .setFontFamily("serif")
                            .build())
                    .addText(new TextSpannableTool.TextStyle.Builder()
                            .setText("sans-serif font\n")
                            .setFontFamily("sans-serif")
                            .build())
                    .addText(new TextSpannableTool.TextStyle.Builder()
                            .setText("测试正常对齐\n")
                            .setAlign(Layout.Alignment.ALIGN_NORMAL)
                            .build())
                    .addText(new TextSpannableTool.TextStyle.Builder()
                            .setText("测试居中对齐\n")
                            .setAlign(Layout.Alignment.ALIGN_CENTER)
                            .build())
                    .addText(new TextSpannableTool.TextStyle.Builder()
                            .setText("测试相反对齐\n")
                            .setAlign(Layout.Alignment.ALIGN_OPPOSITE)
                            .build())
                    .addText(new TextSpannableTool.TextStyle.Builder()
                            .setText("ZBaseLib\n")
                            .setURL(textView1, "https://github.com/duyangs/ZBaseLib")
                            .build())
                    .addText(new TextSpannableTool.TextStyle.Builder()
                            .setText("点击事件\n")
                            .setOnClickListener(textView1, new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Log.d(TAG,"点击事件");
                                }
                            }).build())
                    .addText(new TextSpannableTool.TextStyle.Builder()
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
