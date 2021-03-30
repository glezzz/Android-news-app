package com.myproject.newsapp.api

import com.myproject.newsapp.util.Constants.Companion.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {
    companion object {

        private val retrofit by lazy {
            // to log retrofit responses for easier debugging
            val logging = HttpLoggingInterceptor()
            // to see the body of the response
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                    // how should the response be interpreted and converted to Kotlin objects
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
        }

        // api instance from retrofit builder
        val api by lazy {
            retrofit.create(NewsAPI::class.java)
        }
    }
}