package com.example.statisticsproject
import android.util.Log
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response


object HeaderInterceptor:Interceptor {
    var cookie: String? = null

    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()
        val builder: Request.Builder = request.newBuilder()
        if (cookie != null) {
            builder.addHeader("Cookie", cookie!!)
//            builder.addHeader("Connection", "close")
        } else {
            Log.e("Cookie", "Cookie not found")
        }
        return chain.proceed(builder.build())
    }

}