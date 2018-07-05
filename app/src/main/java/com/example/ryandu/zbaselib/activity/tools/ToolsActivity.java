package com.example.ryandu.zbaselib.activity.tools;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.duyangs.zbase.BaseActivity;
import com.duyangs.zbase.util.StartActivityUtil;
import com.example.ryandu.zbaselib.MainAdapter;
import com.example.ryandu.zbaselib.R;


public class ToolsActivity extends BaseActivity implements MainAdapter.OnItemClickListener {

    private RecyclerView recyclerView;

    private String[] type = {"DateTools"};

    private MainAdapter adapter;

    public static void actionStart(Context context){
        StartActivityUtil.startActivity(context,ToolsActivity.class);
    }

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
            case "DateTools":
                DateToolsActivity.actionStart(this);
                break;
            }
    }
}
