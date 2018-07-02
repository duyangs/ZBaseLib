package com.example.ryandu.zbaselib.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;

import com.duyangs.zbase.BaseActivity;
import com.duyangs.zbase.util.StartActivityUtil;
import com.example.ryandu.zbaselib.R;
import com.example.ryandu.zbaselib.fragment.BaseFragmentShowFragment;

import static com.example.ryandu.zbaselib.fragment.BaseFragmentShowFragment.*;


/**
 * <p>Project:BaseLibDemo</p>
 * <p>Package:com.duyangs.baselibdemo</p>
 * <p>Description: 展示baseFragment</p>
 * <p>Company:</p>
 *
 * @author duyangs
 * @date 2017/04/26 0026
 */
public class BaseFragmentShowActivity extends BaseActivity {

    private FrameLayout frameLayout;


    public static void actionStart(Context context){
        StartActivityUtil.startActivity(context,BaseFragmentShowActivity.class);
    }

    @Override
    protected int bindLayout() {
        return R.layout.activity_base_frag_show;
    }

    @Override
    protected void initParams(Bundle params) {

    }

    @Override
    protected void initView() {
        frameLayout = findById(R.id.frameLayout);
        Bundle bundle = new Bundle();
        bundle.putString("hello","hello");

        BaseFragmentShowFragment fragment;
        FragmentManager mFragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        fragment = newInstance("s");
        transaction.add(R.id.frameLayout, fragment);
        transaction.commit();
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }


}
