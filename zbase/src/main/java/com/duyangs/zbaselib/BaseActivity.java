package com.duyangs.zbaselib;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.duyangs.zbaselib.toolbar.BaseToolbar;
import com.duyangs.zbaselib.toolbar.DefaultBarOnClickListener;
import com.duyangs.zbaselib.util.LogUtil;
import com.duyangs.zbaselib.util.ToastUtil;
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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtil.d(TAG, "BaseActivity-->onCreate()");

        if (bindLayout() != 0) {
            setContentView(bindLayout());
        }
//        AppManager.getInstance().addActivity(this);
        Bundle bundle = getIntent().getExtras();
        ImmersionBar.with(this)
                .statusBarColor("#ffffff")
                .fitsSystemWindows(true)  //使用该属性必须指定状态栏的颜色，不然状态栏透明，很难看
                .statusBarAlpha(0.3f)  //状态栏透明度，不写默认0.0f
                .init();
        initToolBarBuilder();
        initParams(bundle);
        initView();
        initData();
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
     * @param params
     */
    protected abstract void initParams(Bundle params);

    /**
     * 初始化控件
     */
    protected abstract void initView();

    /**
     * 初始化数据
     */
    protected abstract void initData();

    private void initToolBarBuilder() {
        toolBarBuilder = BaseToolbar.newInstance(this);
    }

    /**
     * 初始化BaseToolbar  表明使用baseTitle
     */
    protected void initDefaultBaseToolbar(String title) {
        toolBarBuilder.setOnClickListener(DefaultBarOnClickListener.newInstance(this))
                .setTextTitle(title)
                .build();
    }

    protected void initDefaultBaseToolbar(int titleId) {
        toolBarBuilder.setOnClickListener(DefaultBarOnClickListener.newInstance(this))
                .setRsIdTitle(titleId)
                .build();
    }

    /**
     * 应用场景在于动态修改页面Title
     * @param title
     */
    protected void setBarTitle(String title){
        toolBarBuilder.getBaseToolbar().setTitle(title);
    }

    protected void setBarTitle(int titleId){
        toolBarBuilder.getBaseToolbar().setTitle(getString(titleId));
    }

    protected void setBarTitleIcon(Drawable icon){
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

    /**
     * toast 该出为居中短显示
     *
     * @param msg 显示内容
     */
    protected void toast(Object msg) {
        ToastUtil.showShortCenter(this,msg);
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
//        AppManager.getInstance().addActivity(this);
        ImmersionBar.with(this).destroy();  //不调用该方法，如果界面bar发生改变，在不关闭app的情况下，退出此界面再进入将记忆最后一次bar改变的状态
    }


}
