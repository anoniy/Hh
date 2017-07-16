package com.kota.hh.data.model

import com.google.gson.annotations.SerializedName

class WeatherAnswer{

    @SerializedName("location")
    val location: Location? = null

    @SerializedName("current")
    val weather: Weather? = null

}