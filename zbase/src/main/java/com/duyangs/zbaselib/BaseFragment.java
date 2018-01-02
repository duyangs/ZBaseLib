package com.duyangs.zbaselib;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.duyangs.zbaselib.util.LogUtil;
import com.duyangs.zbaselib.toast.ToastZ;
import com.gyf.barlibrary.ImmersionBar;


/**
 * <p>Project:BaseLibDemo</p>
 * <p>Package:com.duyangs.baselib</p>
 * <p>Description:fragment 基类</p>
 * <p>Company:</p>
 *
 * @author duyangs
 * @date 2017/04/26 0026
 */
public abstract class BaseFragment extends Fragment {


    /** 日志输出标志 **/
    protected final String TAG = this.getClass().getSimpleName();

    protected Activity mActivity;

    protected View mView;

    protected ImmersionBar mImmersionBar;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        LogUtil.d(TAG,"onAttach()");
        mActivity = (Activity)context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtil.d(TAG,"onCreate()");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LogUtil.d(TAG,"onCreateView()");
        if (mView == null) {
            mView = inflater.inflate(bindLayout(), null);
        }
        initView(mView, savedInstanceState);
        return mView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        LogUtil.d(TAG,"onActivityCreate()");
        initData();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initImmersionBar();
    }

    @Override
    public void onStart() {
        super.onStart();
        LogUtil.d(TAG,"onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        LogUtil.d(TAG,"onResume()");
    }

    @Override
    public void onPause() {
        super.onPause();
        LogUtil.d(TAG,"onPause()");
    }

    @Override
    public void onStop() {
        super.onStop();
        LogUtil.d(TAG,"onStop()");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        LogUtil.d(TAG,"onDestroyView()");
        if (mView != null){//从父容器中删除
            ((ViewGroup)mView.getParent()).removeView(mView);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtil.d(TAG,"onDestroy()");
        if (mImmersionBar != null) {
            mImmersionBar.destroy();
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        LogUtil.d(TAG,"onDetach()");
    }

    /**
     * 绑定布局
     * @return 布局id
     */
    protected abstract int bindLayout();

    /**
     * 该抽象方法就是 初始化view
     * @param view
     * @param savedInstanceState
     */
    protected abstract void initView(View view, Bundle savedInstanceState);

    /**
     * 数据处理
     */
    protected abstract void initData();

    /**
     * 绑定控件
     *
     * @param resId 资源id
     *
     * @return view
     */
    protected    <T extends View> T findById(int resId) {
        return (T) mView.findViewById(resId);
    }

    /**
     * 初始化沉浸式
     */
    protected void initImmersionBar() {
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.statusBarColor("00000000")
                .fitsSystemWindows(true)  //使用该属性必须指定状态栏的颜色，不然状态栏透明，很难看
                .statusBarAlpha(0.0f)  //状态栏透明度，不写默认0.0f
                .statusBarDarkFont(true)   //状态栏字体是深色，不写默认为亮色
                .init();
    }

}
