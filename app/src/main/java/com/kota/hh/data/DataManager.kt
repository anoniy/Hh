package com.kota.hh.data

import com.kota.hh.data.remote.ApiService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataManager @Inject constructor(private val apiService: ApiService){

    fun getWeather() = apiService.getWeather("Moscow")

}