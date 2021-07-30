package com.example.statisticsproject

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit


class MainActivity : AppCompatActivity() {
    private var weatherData: TextView? = null
    private var retrofit: Retrofit? = null
    private  var service:WeatherService?= RetrofitFactory.createRetrofit()!!.create(WeatherService::class.java)
    private lateinit var sessionEditText: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        weatherData = findViewById(R.id.textView)
        sessionEditText=findViewById(R.id.sessionEdit)
        getCurrentDataAsFirst(false,0,0)
        val button= findViewById<Button>(R.id.button)
        button.setOnClickListener {
            getCurrentDataAsFirst(true,1,1)
        }
    }
//
//    private fun getRetrofit(): Retrofit? {
////        val interceptor = HttpLoggingInterceptor()
////        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
////        val cookieHandler: CookieHandler = CookieManager()
////
////        val client = OkHttpClient.Builder().addNetworkInterceptor(interceptor)
////            .cookieJar(JavaNetCookieJar(cookieHandler))
////            .connectTimeout(1, TimeUnit.MINUTES)
////            .writeTimeout(1, TimeUnit.MINUTES)
////            .readTimeout(3, TimeUnit.MINUTES)
////            .build()
//
//           val retrofit = Retrofit.Builder()
//                .baseUrl(BaseUrl)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build()
//        return retrofit
//    }

    private fun getCurrentDataAsFirst(isFirst:Boolean,correct:Int,total:Int) {
        val call = service!!.saveStatistics(
            1,
            "AL_102-Lesson_1",
            27,
            "lesson",
            isFirst,
            0,
            correct,
            0,
            total,
            "3fd959a5da7df4b704aadf7f18eee105",
            "21997"
        )

        call.enqueue(object : Callback<WeatherResponse> {
            override fun onResponse(
                call: Call<WeatherResponse>,
                response: Response<WeatherResponse>
            ) {
                if (response.code() == 200) {
                    HeaderInterceptor.cookie=response.headers().get("set-cookie");
                    Log.d("N_TAG", HeaderInterceptor.cookie!!);

                    val weatherResponse = response.body()!!
                    weatherData!!.text = weatherResponse.msg
                    Log.d("N_TAG",weatherResponse.msg.toString())
                }
            }

            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                weatherData!!.text = t.message
            }
        })
    }

    companion object {
        var BaseUrl = "https://www.avc-agbu.org/edu/"
        var AppId = "2e65127e909e178d0af311a81f39948c"
        var lat = "35"
        var lon = "139"
    }

}