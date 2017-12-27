package com.example.ryandu.zbaselib;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.duyangs.zbaselib.BaseActivity;
import com.example.ryandu.zbaselib.activity.BaseFragmentShowActivity;
import com.example.ryandu.zbaselib.activity.LoadingActivity;
import com.example.ryandu.zbaselib.activity.TextStyleUtilActivity;
import com.example.ryandu.zbaselib.activity.ToastUtilActivity;
import com.example.ryandu.zbaselib.activity.ToolbarActivity;


public class MainActivity extends BaseActivity implements MainAdapter.OnItemClickListener {

    private RecyclerView recyclerView;

    private String[] type = {
            "Toolbar"
            , "Toast"
            , "TextStyle"
            , "BaseFragment"
            , "Loading"};

    private MainAdapter adapter;

    @Override
    protected int bindLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initParams(Bundle params) {
    }

    @Override
    protected void initView() {
        recyclerView = findById(R.id.main_recycler);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        adapter = new MainAdapter(this,type);
        adapter.setOnItemClickListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //添加分割线
        recyclerView.addItemDecoration(new DividerItemDecoration(
                this, DividerItemDecoration.VERTICAL));
        //设置Item增加、移除动画
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void OnItemClick(View view, int position) {
        switch (type[position]){
            case "Toolbar":
                ToolbarActivity.actionStart(this);
                break;
            case "Toast":
                ToastUtilActivity.actionStart(this);
                break;
            case "TextStyle":
                TextStyleUtilActivity.actionStart(this);
                break;
            case "BaseFragment":
                BaseFragmentShowActivity.actionStart(this);
                break;
            case "Loading":
                LoadingActivity.actionStart(this);
                break;
            }
    }
}
