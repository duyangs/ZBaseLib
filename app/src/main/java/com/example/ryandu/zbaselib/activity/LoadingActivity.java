package com.example.ryandu.zbaselib.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.duyangs.zbaselib.BaseActivity;
import com.duyangs.zbaselib.loadingView.LoadingUtil;
import com.duyangs.zbaselib.util.StartActivityUtil;
import com.example.ryandu.zbaselib.R;

/**
 * "" ZBaseLib
 * create by DuYang
 * e-mail:duyangs1994@gmail.com
 * update time 2017/12/27.
 */

public class LoadingActivity extends BaseActivity {

    public static void actionStart(Context context){
        StartActivityUtil.startActivity(context,LoadingActivity.class);
    }


    @Override
    protected int bindLayout() {
        return R.layout.activity_loading;
    }

    @Override
    protected void initParams(Bundle params) {

    }

    @Override
    protected void initView() {
        findById(R.id.button_show).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoadingUtil.showCanCancel(LoadingActivity.this,"ceshi...");
            }
        });

        findById(R.id.button_dismiss).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoadingUtil.dismiss();
            }
        });
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }
}
