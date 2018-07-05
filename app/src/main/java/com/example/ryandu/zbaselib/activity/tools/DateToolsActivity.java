package com.example.ryandu.zbaselib.activity.tools;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.duyangs.zbase.BaseActivity;
import com.duyangs.zbase.util.StartActivityUtil;
import com.duyangs.ztools.DateTool;
import com.example.ryandu.zbaselib.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * <p>Project:BaseLibDemo</p>
 * <p>Package:com.duyangs.baselibdemo</p>
 * <p>Description:</p>
 * <p>Company:</p>
 *
 * @author duyangs
 * @date 2017/04/21 0021
 */
public class DateToolsActivity extends BaseActivity {
    private LinearLayout linearLayout;

    public static void actionStart(Context context){
        StartActivityUtil.startActivity(context,DateToolsActivity.class);
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

    @SuppressLint({"SetTextI18n", "SimpleDateFormat"})
    @Override
    protected void initData(Bundle savedInstanceState) {

        TextView textView1 = new TextView(this);
        textView1.setText("getGMTime1() ->" + DateTool.INSTANCE.getGMTime1()+"\n"
                        +"getGMTime2() ->" + DateTool.INSTANCE.getGMTime2()+"\n"
                        +"getDateByYMDHMS(\"2018-07-05 14:49:00\") \n-> "+DateTool.INSTANCE.getDateByYMDHMS("2018-07-05 14:49:00")+"\n"
                        +"getDateByYMD(\"2018-07-05\") \n-> "+DateTool.INSTANCE.getDateByYMD("2018-07-05")+"\n"
                        +"getDateStringYMDHMS(new Date) \n-> "+DateTool.INSTANCE.getDateStringYMDHMS(new Date())+"\n"
                        +"getDateStringYMD(new Date) \n-> "+DateTool.INSTANCE.getDateStringYMD(new Date())+"\n"
                        +"getDateStringHMS(new Date) \n-> "+DateTool.INSTANCE.getDateStringHMS(new Date())+"\n"
                        +"getDateString(new Date,\"ss\") \n-> "+DateTool.INSTANCE.getDateString(new Date(),"ss")+"\n"
                        +"getDateString(new Date,new SimpleDateFormat(\"mm:ss\")) \n-> "+DateTool.INSTANCE.getDateString(new Date(),new SimpleDateFormat("mm:ss"))+"\n"
                        +"current() ->" + DateTool.INSTANCE.current()+"\n"
                        +"getYesterdayDate() ->" + DateTool.INSTANCE.getYesterdayDate()+"\n"
                        +"getTodayDate() ->" + DateTool.INSTANCE.getTodayDate()+"\n"
                        +"getTomorrowDate() ->" + DateTool.INSTANCE.getTomorrowDate()+"\n"
                        +"judgeCurrTime(\"2018-07-04 22:00:00\") ->" + DateTool.INSTANCE.judgeCurrTime("2018-07-04 22:00:00")+"\n"
                        +"judgeCurrTime(System.currentTimeMillis()) ->" + DateTool.INSTANCE.judgeCurrTime(System.currentTimeMillis())+"\n"
                        +"convertTimeToFormat(System.currentTimeMillis()) ->" + DateTool.INSTANCE.convertTimeToFormat(String.valueOf(System.currentTimeMillis()))+"\n"
                        +"dateToWeek(new Date()) ->" + DateTool.INSTANCE.dateToWeek(new Date())+"\n"
                        +"stringDateToStringData(\"yyyy-MM-dd HH:mm:ss\",\"yyyy-MM-dd\",\"2018-01-01 00:00:00\") \n->" + DateTool.INSTANCE.stringDateToStringData("yyyy-MM-dd HH:mm:ss","yyyy-MM-dd","2018-01-01 00:00:00")+"\n"
                        +"getMonthLastDay(2018,7) ->" + DateTool.INSTANCE.getMonthLastDay(2018,7)+"\n"
                        +"getCurrentYear() ->" + DateTool.INSTANCE.getCurrentYear()+"\n"
                        +"getCurrentMonth() ->" + DateTool.INSTANCE.getCurrentMonth()+"\n"
                        +"getCurrDay() ->" + DateTool.INSTANCE.getCurrDay()+"\n"
                        +"getDayByDate(Calendar.getInstance(),Calendar.YEAR,10) ->" + DateTool.INSTANCE.getDayByDate(Calendar.getInstance(),Calendar.YEAR,10)+"\n"
        );

        linearLayout.addView(textView1);

    }
}
