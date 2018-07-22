package com.example.ryandu.zbaselib.activity;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.duyangs.zbase.BaseActivity;
import com.duyangs.zbase.toolbar.BaseToolbar;
import com.duyangs.zbase.util.StartActivityUtil;
import com.example.ryandu.zbaselib.R;


/**
 * <p>Project:BaseLibDemo</p>
 * <p>Package:com.duyangs.baselibdemo</p>
 * <p>Description:ToolbarActivity 演示Base</p>
 * <p>Company:</p>
 *
 * @author duyangs
 * @date 2017/04/20 0020
 */
public class ToolbarActivity extends BaseActivity implements BaseToolbar.OnClickListener {


    public static void actionStart(Context context){
        StartActivityUtil.startActivity(context,ToolbarActivity.class);
    }

    @Override
    protected int bindLayout() {
        return R.layout.activity_toobar;
    }

    @Override
    public void initParams(Bundle params) {
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void initView() {
        toolBarBuilder.setOnClickListener(this)
                .setTextTitle("Toolbar演示")
                .setTitleDrawable(getResources().getDrawable(R.mipmap.ic_launcher_round))
                .setTitleOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Log.d(TAG,"title");
                    }
                }).build();

    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.bar_menu,menu);
        return true;
    }

    @Override
    public void navigationOnClick(View v) {
        Log.d(TAG,"back");
        finish();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        int msg = 0;


        switch (item.getItemId()){
            case R.id.action_edit:
                msg = R.string.bar_edit;
                break;
            case R.id.action_share:
                msg = R.string.bar_share;
                break;
            case R.id.action_settings:
                msg = R.string.bar_setting;
                break;
        }
        Log.d(TAG,msg+"");
        return true;
    }
}