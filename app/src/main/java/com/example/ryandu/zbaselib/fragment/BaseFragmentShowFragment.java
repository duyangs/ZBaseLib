package com.example.ryandu.zbaselib.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.duyangs.zbaselib.BaseFragment;
import com.example.ryandu.zbaselib.R;


/**
 * <p>Project:BaseLibDemo</p>
 * <p>Package:com.duyangs.baselibdemo.fragment</p>
 * <p>Description:</p>
 * <p>Company:</p>
 *
 * @author duyangs
 * @date 2017/04/26 0026
 */
public class BaseFragmentShowFragment extends BaseFragment {

    private static BaseFragmentShowFragment mFragment;//用于newInstance

    private TextView textView;

    public static BaseFragmentShowFragment newInstance(String s) {
        Bundle bundle = new Bundle();
        bundle.putString("hello",s);
        mFragment = new BaseFragmentShowFragment();
        if (bundle != null){
            mFragment.setArguments(bundle);
        }
        return mFragment;
    }

    @Override
    protected int bindLayout() {
        return R.layout.fragment_show;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        textView = findById(R.id.text_view);
    }

    @Override
    protected void initData() {
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toast(getArguments().getString("hello"));
            }
        });
    }
}
