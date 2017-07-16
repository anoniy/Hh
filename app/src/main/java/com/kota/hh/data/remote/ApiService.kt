package com.kota.hh.data.remote

import com.kota.hh.BuildConfig
import com.kota.hh.data.model.WeatherAnswer
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import rx.Observable

interface ApiService {

    @GET("current.json?key=${BuildConfig.API_KEY}")
    fun getWeather(@Query("q") cityName: String): Observable<WeatherAnswer>

    companion object {
        private val ENDPOINT = "https://api.apixu.com/v1/"

        fun newApiService(): ApiService {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

            val client: OkHttpClient = OkHttpClient
                    .Builder()
                    .addInterceptor(httpLoggingInterceptor)
                    .build()

            val retrofit = Retrofit.Builder()
                    .baseUrl(ENDPOINT)
                    .client(client)
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            return retrofit.create(ApiService::class.java)
        }
    }

}