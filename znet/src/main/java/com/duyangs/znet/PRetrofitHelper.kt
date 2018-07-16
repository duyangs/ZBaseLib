package com.brightcns.paylib.http

import android.util.Log
import com.duyangs.znet.ZNet
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.parse.ParseException.TIMEOUT
import me.jessyan.retrofiturlmanager.RetrofitUrlManager
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


/**
 * <p>Project:ZHttpLib</p>
 * <p>Package:com.duyangs.zhttplib</p>
 * <p>Description:retrofit控制器</p>
 * <p>Company:</p>
 *
 * @author duyangs
 * @date 2017/07/17 0017
 */

class PRetrofitHelper private constructor() {


    private var pIntercept: Interceptor = Interceptor {
        return@Interceptor it.proceed(it.request().newBuilder().addHeader("port", "Android").build())
    }
    private var pHttpLoggingInterceptor: HttpLoggingInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger {
        Log.i("PRetrofitHelper", "MESSAGE == [ $it ] ==")
    }).setLevel(HttpLoggingInterceptor.Level.BODY)

    private var okHttpClientBuilder: OkHttpClient.Builder = RetrofitUrlManager.getInstance().with(OkHttpClient.Builder())
            .addInterceptor(pIntercept)
            .addInterceptor(pHttpLoggingInterceptor)
            .retryOnConnectionFailure(true)
            .connectTimeout(TIMEOUT.toLong(), TimeUnit.SECONDS)
            .readTimeout(TIMEOUT.toLong(), TimeUnit.SECONDS)

    private var okHttpClient: OkHttpClient? = null

    private var gson: Gson = GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").create()

    private var factory: GsonConverterFactory = GsonConverterFactory.create(gson)
    private var mRetrofit: Retrofit? = null

    companion object {
        fun getInstance() = RetrofitHelperHolder.INSTANCE
    }

    private object RetrofitHelperHolder {
        val INSTANCE = PRetrofitHelper()
    }


    private fun resetApp() {

        for (interceptor : Interceptor in ZNet.getInterceptor()){
            okHttpClientBuilder.addInterceptor(interceptor)
        }

        okHttpClient = okHttpClientBuilder.build()

        mRetrofit = Retrofit.Builder()
                .baseUrl(ZNet.getDefaultUrl())
                .client(okHttpClient)
                .addConverterFactory(factory)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }

    fun getRetrofit(): Retrofit {
        if (mRetrofit != null) {
            return mRetrofit as Retrofit
        } else {
            throw NullPointerException("u should init first")
        }
    }

    init {
        resetApp()
    }


}
