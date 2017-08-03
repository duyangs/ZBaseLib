package com.example.ryandu.zbaselib.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.duyangs.zbaselib.BaseActivity;
import com.duyangs.zbaselib.toolbar.BaseToolbar;
import com.duyangs.zbaselib.util.ToastUtil;
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

    @Override
    protected int bindLayout() {
        return R.layout.activity_toobar;
    }

    @Override
    public void initParams(Bundle params) {
    }

    @Override
    protected void initView() {

        initDefaultBaseToolbar("Toolbar演示");

    }

    @Override
    protected void initData() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.bar_menu,menu);
        return true;
    }

    @Override
    public void navigationOnClick(View v) {
        toast("back");
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
        toast(msg);
        return true;
    }
}
