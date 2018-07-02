package com.duyangs.zbase;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.duyangs.zbase.toolbar.BaseToolbar;
import com.duyangs.zbase.toolbar.DefaultBarOnClickListener;
import com.duyangs.zbase.util.ATAUtil;
import com.duyangs.zbase.util.LogUtil;
import com.gyf.barlibrary.ImmersionBar;


/**
 * <p>Project:BaseLib</p>
 * <p>Package:com.duyangs.baselib</p>
 * <p>Description:Activity基类 支持继承</p>
 * <p>Company:</p>
 *
 * @author duyangs
 * @date 2017/04/19 0019
 */
public abstract class BaseActivity extends AppCompatActivity {

    /**
     * 日志输出标志
     **/
    protected final String TAG = this.getClass().getSimpleName();

    protected BaseToolbar.Builder toolBarBuilder;

    protected ImmersionBar mImmersionBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtil.d(TAG, "BaseActivity-->onCreate()");

        if (bindLayout() != 0) {
            setContentView(bindLayout());
        }
        Bundle bundle = getIntent().getExtras();
        initImmersionBar();
        initToolBarBuilder();
        initParams(bundle);
        initView();
        initData(savedInstanceState);
        BaseActivityManager.INSTANCE.addActivity(this);
    }

    /**
     * 初始化 ImmersionBar
     */
    protected void initImmersionBar(){
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.statusBarColor("#ffffff")
                .fitsSystemWindows(true)  //使用该属性必须指定状态栏的颜色，不然状态栏透明，很难看
                .statusBarAlpha(0.0f)  //状态栏透明度，不写默认0.0f
                .statusBarDarkFont(true)   //状态栏字体是深色，不写默认为亮色
                .init();
    }

    /**
     * 绑定布局
     *
     * @return 布局资源id
     */
    protected abstract int bindLayout();


    /**
     * [初始化参数]
     * a
     *
     * @param params 传入参数
     */
    protected abstract void initParams(Bundle params);

    /**
     * 初始化控件
     */
    protected abstract void initView();

    /**
     * 初始化数据
     */
    protected abstract void initData(Bundle savedInstanceState);

    private void initToolBarBuilder() {
        toolBarBuilder = BaseToolbar.newInstance(this);
    }

    /**
     * 初始化BaseToolbar  表明使用baseTitle
     */
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    protected void initDefaultBaseToolbar(String title) {
        toolBarBuilder.setOnClickListener(DefaultBarOnClickListener.newInstance(this))
                .setTextTitle(title)
                .build();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    protected void initDefaultBaseToolbar(int titleId) {
        toolBarBuilder.setOnClickListener(DefaultBarOnClickListener.newInstance(this))
                .setRsIdTitle(titleId)
                .build();
    }

    /**
     * 应用场景在于动态修改页面Title
     *
     * @param title 标题
     */
    protected void setBarTitle(String title) {
        toolBarBuilder.getBaseToolbar().setTitle(title);
    }

    protected void setBarTitle(int titleId) {
        toolBarBuilder.getBaseToolbar().setTitle(getString(titleId));
    }

    protected void setBarTitleIcon(Drawable icon) {
        toolBarBuilder.getBaseToolbar().setTitleDrawable(icon);
    }


    /**
     * 绑定控件
     *
     * @param resId 资源id
     * @return view
     */
    protected <T extends View> T findById(int resId) {
        return (T) super.findViewById(resId);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        LogUtil.d(TAG, "onRestart()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        LogUtil.d(TAG, "onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        LogUtil.d(TAG, "onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        LogUtil.d(TAG, "onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        LogUtil.d(TAG, "onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtil.d(TAG, "onDestroy()");
        BaseActivityManager.INSTANCE.finishActivity(this);
        if (mImmersionBar != null) {
            mImmersionBar.destroy();
        }//不调用该方法，如果界面bar发生改变，在不关闭app的情况下，退出此界面再进入将记忆最后一次bar改变的状态
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        ATAUtil.out(this);
    }
}
