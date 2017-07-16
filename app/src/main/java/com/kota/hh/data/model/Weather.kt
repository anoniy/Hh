package com.kota.hh.data.model

import com.google.gson.annotations.SerializedName

class Weather{

    @SerializedName("last_updated_epoch")
    val lastUpdatedEpoch: Int = 0

    @SerializedName("last_updated")
    val lastUpdated: String = ""

    @SerializedName("temp_c")
    val tempC: Double = 0.0

    @SerializedName("temp_f")
    val tempF: Double = 0.0

    @SerializedName("is_day")
    val isDay: Int = 0

    @SerializedName("condition")
    val condition: Condition? = null

    @SerializedName("wind_mph")
    val windMph: Double? = null

    @SerializedName("wind_kph")
    val windKph: Double? = null

    @SerializedName("wind_degree")
    val windDegree: Int? = null

    @SerializedName("wind_dir")
    val windDir: String? = null

    @SerializedName("pressure_mb")
    val pressureMb: Double? = null

    @SerializedName("pressure_in")
    val pressureIn: Double? = null

    @SerializedName("precip_mm")
    val precipMm: Double? = null

    @SerializedName("precip_in")
    val precipIn: Double? = null

    @SerializedName("humidity")
    val humidity: Int? = null

    @SerializedName("cloud")
    val cloud: Int? = null

    @SerializedName("feelslike_c")
    val feelslikeC: Double? = null

    @SerializedName("feelslike_f")
    val feelslikeF: Double? = null

    @SerializedName("vis_km")
    val visKm: Double? = null

    @SerializedName("vis_miles")
    val visMiles: Double? = null
}