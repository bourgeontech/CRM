package com.study.firebasecrash.Retrofit


import com.git.crm.Networks.ApiService
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiClient {


    var BASE_URL: String = "http://crm.everi.in/api/"
    public val getClient: ApiService
        get() {
            val gson = GsonBuilder()
                .disableHtmlEscaping()
                .create()
            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.MINUTES)
                .readTimeout(15, TimeUnit.MINUTES)
                .addInterceptor(interceptor).build()

            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(ApiService::class.java)
        }


}