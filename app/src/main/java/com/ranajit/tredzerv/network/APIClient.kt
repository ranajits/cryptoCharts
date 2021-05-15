package com.ranajit.tredzerv.network

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.ranajit.tredzerv.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object APIClient {

    val apiServiceClient: APIInterface by lazy {
        retrofitBuilderForClient
            .build()
            .create(APIInterface::class.java)
    }


    var gson: Gson = GsonBuilder()
        .setLenient()
        .create()

    private val retrofitBuilderForClient: Retrofit.Builder by lazy {
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(getOkHttpClient())
    }

    private fun getOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(headerInterceptorClient)
            .addNetworkInterceptor(interceptorOld)
            .callTimeout(1, TimeUnit.MINUTES)
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .build()
    }


    private val interceptorOld = run {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.apply {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.NONE
        }
    }


    private val headerInterceptorClient: Interceptor by lazy {
        Interceptor { chain: Interceptor.Chain ->
            val original = chain.request()
            val requestBuilder = original.newBuilder().addHeader("Connection", "close")
            val request = requestBuilder.build()
            val response = chain.proceed(request)
            return@Interceptor response
        }
    }


}

