package com.tejuspicsum_app.ApiService

import android.content.Context
import com.intuit.sdp.BuildConfig
import com.tejuspicsum_app.apis.WebApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiService {

    companion object {

        var BASE_URL = "https://picsum.photos/"
        var Image_URL="https://picsum.photos/300/300?image="

        private var retrofitObj: Retrofit? = null

        fun getRetrofitObj(act: Context): WebApi? {
            if (null == retrofitObj) {
                val client = OkHttpClient().newBuilder()
                    .callTimeout(2, TimeUnit.MINUTES)
                    .connectTimeout(1, TimeUnit.SECONDS)
                    .readTimeout(1, TimeUnit.MINUTES)
                    .writeTimeout(1, TimeUnit.MINUTES)
                    .addInterceptor(HttpLoggingInterceptor().apply {
                        level =
                            if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
                    })
                    .addInterceptor { chain ->
                        val original = chain.request()
                        val request = original.newBuilder()
                            .header("Content-Type", "application/json")
                            .method(original.method, original.body)
                            .build()
                        chain.proceed(request)

                    }
                    .build()
                retrofitObj = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofitObj?.create(WebApi::class.java)
        }


    }
}