package com.android.maplemate.Data


import com.android.myapplication.Service.ApiServiceMc
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import de.hdodenhof.circleimageview.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitModule {
    private fun buildOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(
                // 로깅 인터셉터
                HttpLoggingInterceptor().apply {
                    level = if (BuildConfig.DEBUG) {
                        HttpLoggingInterceptor.Level.BODY
                    } else {
                        HttpLoggingInterceptor.Level.NONE
                    }
                }
            )
            .readTimeout(5, TimeUnit.MINUTES)
            .connectTimeout(5, TimeUnit.MINUTES)
            .build()

    private var gson: Gson = GsonBuilder()
        .setLenient()
        .create()

    fun createSonnyApiService(): ApiServiceMc {
        return Retrofit.Builder()
            .baseUrl("https://mc-material.o-r.kr/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(buildOkHttpClient())
            .build()
            .create(ApiServiceMc::class.java)
    }


}