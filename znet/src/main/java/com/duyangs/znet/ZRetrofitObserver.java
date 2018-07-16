package com.duyangs.znet;

import android.content.Context;
import android.os.Parcelable;
import android.util.Log;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * <p>Project:ZHttpLib</p>
 * <p>Package:com.duyangs.zhttplib</p>
 * <p>Description:</p>
 * <p>Company:</p>
 *
 * @author duyangs
 * @date 2017/07/18 0018
 */
public abstract class ZRetrofitObserver<T extends Parcelable> implements Observer<ZBaseBean<T>> {
    public static final String TAG = "ZRetrofitObserver";

    protected CompositeDisposable compositeDisposable;
    protected Disposable disposable;

    protected Context context;

    public ZRetrofitObserver(Context context) {
        this.context = context;
    }

    public ZRetrofitObserver() {

    }

    @Override
    public void onSubscribe(@NonNull Disposable d) {
        if (d != null) {
            disposable = d;
        }

        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }
        compositeDisposable.add(d);
    }

    @Override
    public void onNext(ZBaseBean<T> value) {
        Log.e(TAG, "code:" + value.getCode() + " msg:" + value.getMsg());
        if (value.getCode() == 0) {
            T t = value.getData();
            onHandleSuccess(t);
        } else {
//            CheckErrorCode.checkErrorCode(value.getCode(),value.getMsg());
            onHandleError(value.getCode(), value.getMsg());
        }
    }


    @Override
    public void onError(@NonNull Throwable e) {
        Log.e(TAG, e.getMessage());
        onHandleError(400500, e.getMessage());
        if (!disposable.isDisposed()) {
            disposable.dispose();
        }
    }

    @Override
    public void onComplete() {
        if (!compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
        }
    }

    protected abstract void onHandleSuccess(T t);

    protected abstract void onHandleError(int code, String msg);

}
