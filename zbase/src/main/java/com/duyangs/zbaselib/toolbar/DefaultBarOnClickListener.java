package com.duyangs.zbaselib.toolbar;

import android.view.MenuItem;
import android.view.View;

import com.duyangs.zbaselib.BaseActivity;

/**
 * <p>Project:YYAAI_sales</p>
 * <p>Package:com.yyaai.sales.listener</p>
 * <p>Description: BaseBar默认监听  只监听返回键 做finish操作 </p>
 * <p>Company:</p>
 *
 * @author duyangs
 * @date 2017/06/14 0014
 */
public class DefaultBarOnClickListener implements BaseToolbar.OnClickListener {
    private BaseActivity baseActivity;

    private DefaultBarOnClickListener(BaseActivity baseActivity) {
        this.baseActivity = baseActivity;
    }

    public static DefaultBarOnClickListener newInstance(BaseActivity baseActivity) {
        return new DefaultBarOnClickListener(baseActivity);
    }

    @Override
    public void navigationOnClick(View view) {
        baseActivity.finish();
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        return false;
    }
}
