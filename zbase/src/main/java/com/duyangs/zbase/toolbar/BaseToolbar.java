package com.duyangs.zbase.toolbar;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.duyangs.zbase.R;

import java.util.Objects;

/**
 * <p>Project:BaseLibDemo</p>
 * <p>Package:com.duyangs.baselib</p>
 * <p>Description:可直接调用Toolbar的封装类，不支持继承</p>
 * <p>Company:</p>
 *
 * @author duyangs
 * @date 2017/04/19 0019
 */
public class BaseToolbar {
    private Toolbar mToolbar;

    private TextView titleTextView;

    private AppCompatActivity appCompatActivity;

    private OnClickListener onClickListener;

    private View.OnClickListener titleOnClickListener;

    private int rsIdTitle;

    private String textTitle;

    private Drawable titleDrawable;

    private boolean isHideNavigationIcon;

    private BaseToolbar(Builder builder) {
        this.appCompatActivity = builder.appCompatActivity;
        this.onClickListener = builder.onClickListener;
        this.titleOnClickListener = builder.titleOnClickListener;
        this.rsIdTitle = builder.rsIdTitle;
        this.textTitle = builder.textTitle;
        this.titleDrawable = builder.titleDrawable;
        this.isHideNavigationIcon = builder.isHideNavigationIcon;
    }



    /**
     * 初始化ToolBar
     */
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void initToolbar() {
        mToolbar = appCompatActivity.findViewById(R.id.base_title);
        if (mToolbar == null) {
           return;
        }
        appCompatActivity.setSupportActionBar(mToolbar);
        Objects.requireNonNull(appCompatActivity.getSupportActionBar()).setDisplayShowTitleEnabled(false);
//            getSupportActionBar().setDisplayShowTitleEnabled(true);
        titleTextView = appCompatActivity.findViewById(R.id.base_title_text);
        initListener();
        setTitleText();
        setTitleTextIcon();
        hideNavigationIcon();
    }



    /**
     * 初始化监听操作
     */
    private void initListener() {
        if (onClickListener == null){
            return;
        }
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListener.navigationOnClick(v);
            }
        });
        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                return onClickListener.onMenuItemClick(item);
            }
        });
    }

    /**
     * 设置标题
     */
    private void setTitleText() {
        if (titleTextView == null) {
            return;
        }

        if (titleOnClickListener != null){
            titleTextView.setOnClickListener(titleOnClickListener);
        }

        if (textTitle != null) {
            titleTextView.setText(textTitle);
            return;
        }
        if (rsIdTitle != 0){
            titleTextView.setText(rsIdTitle);
        }
    }


    /**
     * 设置title drawable
     */
    private void setTitleTextIcon(){
        if (titleTextView != null){
            if (titleDrawable != null) {
                titleDrawable.setBounds(0, 0, titleDrawable.getMinimumWidth(), titleDrawable.getMinimumHeight());
                titleTextView.setCompoundDrawablePadding(20);
                titleTextView.setCompoundDrawables(titleDrawable, null, null, null);
            }else {
                titleTextView.setCompoundDrawables(null, null, null, null);
            }
        }
    }


    /**
     * 隐藏返回按钮
     */
    private void hideNavigationIcon() {
        if (isHideNavigationIcon) {
            mToolbar.setNavigationIcon(null);
        }
    }


    /**
     * 将navigationOnClick 和 onMenuItemClick 封装成接口  方便在BaseActivity的子类中直接实现对ToolBar的点击监听操作
     */
    public interface OnClickListener{
        void navigationOnClick(View v);

        boolean onMenuItemClick(MenuItem item);
    }

    /**
     * 获取ToolBar 方便自己实现其他功能
     * @return mToolbar
     */
    public Toolbar getmToolbar() {
        return mToolbar;
    }

    public void setTitle(String title){
        if (titleTextView == null){
            return;
        }
        titleTextView.setText(title);
    }

    public void setTitleOnClickListener(View.OnClickListener onClickListener){
        this.titleOnClickListener = onClickListener;
        if (titleTextView != null){
            titleTextView.setOnClickListener(onClickListener);
        }
    }

    public void setTitleDrawable(Drawable titleDrawable){
        this.titleDrawable = titleDrawable;
        setTitleTextIcon();
    }


    public static Builder newInstance(AppCompatActivity appCompatActivity){
        return new Builder(appCompatActivity);
    }


    public static class Builder{
        private AppCompatActivity appCompatActivity;

        private OnClickListener onClickListener;

        private View.OnClickListener titleOnClickListener;

        private int rsIdTitle;

        private String textTitle;

        private Drawable titleDrawable;

        private boolean isHideNavigationIcon;

        private BaseToolbar baseToolbar;

        private Builder(AppCompatActivity appCompatActivity) {
            this.appCompatActivity = appCompatActivity;
        }

        public Builder setOnClickListener(OnClickListener onClickListener) {
            this.onClickListener = onClickListener;
            return this;
        }

        public Builder setTitleOnClickListener(View.OnClickListener onClickListener){
            this.titleOnClickListener = onClickListener;
            return this;
        }

        public Builder setRsIdTitle(int rsIdTitle) {
            this.rsIdTitle = rsIdTitle;
            return this;
        }

        public Builder setTextTitle(String textTitle) {
            this.textTitle = textTitle;
            return this;
        }

        public Builder isHideNavigationIcon(boolean isHideNavigationIcon) {
            this.isHideNavigationIcon = isHideNavigationIcon;
            return this;
        }

        public Builder setTitleDrawable(Drawable titleDrawable) {
            this.titleDrawable = titleDrawable;
            return this;
        }

        public BaseToolbar getBaseToolbar(){
            return  baseToolbar;
        }

        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        public void build(){
            baseToolbar = new BaseToolbar(this);
            baseToolbar.initToolbar();
        }
    }
}
