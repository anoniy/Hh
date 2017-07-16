package com.kota.hh.ui.base

open class BasePresenter<T: MvpView>: Presenter<T>{

    var mvpView: T? = null

    override fun attachView(mvpView: T) {
        this.mvpView = mvpView
    }

    override fun detachView() {
        this.mvpView = null
    }

    fun isViewAttached() = mvpView != null

    fun checkViewAttached(){
        if (!isViewAttached()) throw MvpViewNotAttachedException()
    }

    class MvpViewNotAttachedException: RuntimeException(
            "Please call Presenter.attachView(MvpView) before requesting data to the Presenter")
}