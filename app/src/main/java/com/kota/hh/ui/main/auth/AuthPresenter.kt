package com.kota.hh.ui.main.auth

import android.text.TextUtils
import com.kota.hh.R
import com.kota.hh.data.DataManager
import com.kota.hh.ui.base.BasePresenter
import com.kota.hh.util.RxUtil
import com.kota.hh.util.Validator
import rx.Observable
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import javax.inject.Inject

class AuthPresenter @Inject constructor(val dataManager: DataManager, val validator: Validator)
    : BasePresenter<AuthMvpView>() {

    private val TAG = "@@@@AP"
    private var validationSubscription: Subscription? = null
    private var weatherSubSubscription: Subscription? = null

    override fun attachView(mvpView: AuthMvpView) {
        super.attachView(mvpView)
    }

    override fun detachView() {
        super.detachView()
        RxUtil.unsubscribe(validationSubscription)
        RxUtil.unsubscribe(weatherSubSubscription)
    }

    fun makeAuth(login: String, password: String) {
        RxUtil.unsubscribe(validationSubscription)

        validationSubscription = Observable.combineLatest(
                validator
                        .validateEmail(login)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .map { validLoginMessage ->
                            mvpView!!.showLoginError(validLoginMessage)
                            return@map TextUtils.isEmpty(validLoginMessage)
                        },
                validator
                        .validatePassword(password)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .map { validPasswordMessage ->
                            mvpView!!.showPasswordError(validPasswordMessage)
                            return@map TextUtils.isEmpty(validPasswordMessage)
                        },
                { isLoginValid, isPasswordValid -> isLoginValid && isPasswordValid })
                .filter { return@filter it }
                .subscribe(
                        { loadWeather() },
                        { mvpView!!.showError(R.string.error_when_data_load) }
                )
    }

    private fun loadWeather() {
        RxUtil.unsubscribe(weatherSubSubscription)

        mvpView!!.enableLoad(true)
        weatherSubSubscription = dataManager.getWeather()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnEach { mvpView!!.enableLoad(false) }
                .subscribe(
                        { weatherAnswer ->
                            if (weatherAnswer.weather != null) {
                                mvpView!!.showWeather(weatherAnswer.weather)
                            }
                        },
                        { mvpView!!.showError(R.string.error_when_data_load) }
                )
    }
}