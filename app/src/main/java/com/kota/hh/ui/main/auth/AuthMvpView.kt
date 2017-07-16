package com.kota.hh.ui.main.auth

import com.kota.hh.data.model.Weather
import com.kota.hh.ui.base.MvpView

interface AuthMvpView : MvpView{

    fun showLoginError(error: String)
    fun showPasswordError(error: String)
    fun showError(error: String)
    fun showError(errorId: Int)
    fun enableLoad(isLoading: Boolean)
    fun showWeather(weather: Weather)

}