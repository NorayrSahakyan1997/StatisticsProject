package com.example.statisticsproject

import com.example.statisticsproject.MainActivity.Companion.BaseUrl
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object  RetrofitFactory {
    private val URL = BaseUrl
    private var retrofit: Retrofit? = null
    fun createRetrofit(): Retrofit? {
        if (retrofit == null) {
            synchronized(RetrofitFactory::class.java) {
                if (retrofit == null) {
                    val okHttpClient: OkHttpClient = OkHttpClient.Builder().connectTimeout(18, TimeUnit.SECONDS).build()
                    retrofit = Retrofit.Builder()
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(okHttpClient)
                        .baseUrl(URL)
                        .build()
                }
            }
        }
        return retrofit
    }

}