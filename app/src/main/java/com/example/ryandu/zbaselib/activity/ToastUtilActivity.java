package com.example.ryandu.zbaselib.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.duyangs.zbaselib.BaseActivity;
import com.duyangs.zbaselib.util.StartActivityUtil;
import com.duyangs.zbaselib.toast.ToastZ;
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

    public static void actionStart(Context context) {
        StartActivityUtil.startActivity(context, ToastUtilActivity.class);
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
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.normal:
                ToastZ.normal("normal");
                break;
            case R.id.success:
                ToastZ.success("success");
                break;
            case R.id.warning:
                ToastZ.warning("warning");
                break;
            case R.id.error:
                ToastZ.error("error");
                break;

            case R.id.ac_toast_cancel:
                ToastZ.cancelToast();
                break;
        }
    }
}

