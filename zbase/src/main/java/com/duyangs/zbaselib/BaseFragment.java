package com.duyangs.zbaselib;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.duyangs.zbaselib.util.LogUtil;
import com.duyangs.zbaselib.util.ToastUtil;


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
     * 页面跳转
     *
     * @param clz 要跳转的类
     */
    public void startActivity(Class<?> clz) {
        startActivity(new Intent(mActivity,clz));
    }

    /**
     * 携带数据的页面跳转
     *
     * @param clz 要跳转的类
     * @param bundle 数据
     */
    public void startActivity(Class<?> clz, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(mActivity, clz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    /**
     * 含有Bundle通过Class打开编辑界面
     *
     * @param cls 要跳转的类
     * @param bundle 携带的数据
     * @param requestCode 请求码
     */
    public void startActivityForResult(Class<?> cls, Bundle bundle,
                                       int requestCode) {
        Intent intent = new Intent();
        intent.setClass(mActivity, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }

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
     * toast 该出为居中短显示
     * @param msg 显示内容
     */
    protected void toast(Object msg){
        ToastUtil.showShortCenter(mActivity,msg);
    }

}
