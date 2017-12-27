package com.example.ryandu.zbaselib.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.duyangs.zbaselib.BaseActivity;
import com.duyangs.zbaselib.util.StartActivityUtil;
import com.duyangs.zbaselib.toast.ToastUtil;
import com.example.ryandu.zbaselib.R;

/**
 * <p>Project:BaseLibDemo</p>
 * <p>Package:com.duyangs.baselibdemo</p>
 * <p>Description:</p>
 * <p>Company:</p>
 *
 * @author duyangs
 * @date 2017/04/21 0021
 */
public class ToastUtilActivity extends BaseActivity {
    private Button topBtn;

    public static void actionStart(Context context){
        StartActivityUtil.startActivity(context,ToastUtilActivity.class);
    }
    @Override
    protected int bindLayout() {
        return R.layout.activity_toast;
    }


    @Override
    public void initParams(Bundle params) {

    }

    @Override
    protected void initView() {
        //使用 baseActivity 中的 $()方法声明变量
        topBtn = findById(R.id.ac_toast_top_short);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.ac_toast_top_short:
                ToastUtil.showShortTop(this,"top toast short",ToastUtil.TYPE_ERROR);
                break;
            case R.id.ac_toast_center_short:
                ToastUtil.showShortCenter(this,"center toast short",ToastUtil.TYPE_INFO);
                break;
            case R.id.ac_toast_bottom_short:
                ToastUtil.showShortBottom(this,R.string.toast_bottom,ToastUtil.TYPE_SUCCESS);
                break;
            case R.id.ac_toast_top_long:
                ToastUtil.showLongTop(this,"top toast long",ToastUtil.TYPE_WARNING);
                break;
            case R.id.ac_toast_center_long:
                ToastUtil.showLongCenter(this,"center toast long",ToastUtil.TYPE_SUCCESS);
                break;
            case R.id.ac_toast_bottom_long:
                ToastUtil.showLongBottom(this,"bottom toast long",ToastUtil.TYPE_INFO);
                break;
            case R.id.ac_toast_cancel:
                ToastUtil.cancelToast();
                break;
        }
    }
}

