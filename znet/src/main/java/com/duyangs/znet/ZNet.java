package com.duyangs.znet;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;

import java.util.List;
import java.util.Map;

import me.jessyan.retrofiturlmanager.RetrofitUrlManager;
import okhttp3.Interceptor;

/**
 * create by DuYang
 * e-mail:duyangs1994@gmail.com
 * update time 2018/3/12.
 */

public class ZNet {
    @SuppressLint({"StaticFieldLeak"})
    private static Map<String, String> mBaseUrl;

    private static List<Interceptor> mInterceptor;

    private static String defaultUrlKey;

    private ZNet() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }


    public static void init(@NonNull Map<String, String> baseUrl) {
        mBaseUrl = baseUrl;
        // You can change BaseUrl at any time while App is running (The interface that declared the Domain-Name header)
        for (String key : mBaseUrl.keySet()) {
            if (null == defaultUrlKey || defaultUrlKey.isEmpty()){
                defaultUrlKey = key;
            }
            String value = mBaseUrl.get(key);
            RetrofitUrlManager.getInstance().putDomain(key, value);
        }
    }

    public static void init(@NonNull List<Interceptor> interceptor) {
        mInterceptor = interceptor;
    }

    public static void init(@NonNull Map<String, String> baseUrl,@NonNull List<Interceptor> interceptor){
        mBaseUrl = baseUrl;
        mInterceptor = interceptor;
        // You can change BaseUrl at any time while App is running (The interface that declared the Domain-Name header)
        for (String key : mBaseUrl.keySet()) {
            String value = mBaseUrl.get(key);
            RetrofitUrlManager.getInstance().putDomain(key, value);
        }
    }

    public static Map<String, String> getBaseUrl() {
        if (null == mBaseUrl || mBaseUrl.isEmpty()) {
            throw new NullPointerException("u should init first");
        } else {
            return mBaseUrl;
        }
    }

    public static List<Interceptor> getInterceptor() {
        if (null == mInterceptor || mInterceptor.isEmpty()) {
            throw new NullPointerException("u should init first");
        } else {
            return mInterceptor;
        }
    }

    public static String getDefaultUrl(){
        if (null == defaultUrlKey || defaultUrlKey.isEmpty()) {
            throw new NullPointerException("u should init first");
        } else {
            return mBaseUrl.get(defaultUrlKey);
        }
    }
}
