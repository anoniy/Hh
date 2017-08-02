package com.kota.hh.ui.main.auth

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.View
import com.kota.hh.R
import com.kota.hh.data.model.Weather
import com.kota.hh.ui.base.BaseActivity
import com.kota.hh.util.ToastMessage
import kotlinx.android.synthetic.main.activity_auth.*
import kotlinx.android.synthetic.main.toolbar.*
import javax.inject.Inject


class AuthActivity : BaseActivity(), AuthMvpView {

    companion object {
        fun createIntent(context: Context): Intent = Intent(context, AuthActivity::class.java)
    }

    @Inject lateinit var authPresenter: AuthPresenter
    @Inject lateinit var authState: AuthState

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityComponent()!!.inject(this)
        authPresenter.attachView(this)
        setContentView(R.layout.activity_auth)
        initToolbar()
        initWatchers()
        authButton.setOnClickListener {
            hideKeyboard()
            authPresenter.makeAuth(authLoginEdit.text.toString(), authPasswordEdit.text.toString())
        }
        applyState()
    }

    override fun onDestroy() {
        super.onDestroy()
        authPresenter.detachView()
    }

    private fun initToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        setTitle(R.string.authorization)
    }

    private fun initWatchers() {
        authLoginEdit.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {}

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(loginSequence: CharSequence, p1: Int, p2: Int, p3: Int) {
                authState.login = loginSequence.toString()
            }
        })
        authPasswordEdit.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {}

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(passwordSequence: CharSequence, p1: Int, p2: Int, p3: Int) {
                authState.password = passwordSequence.toString()
            }
        })
    }

    private fun applyState() {
        authLoginEdit.setText(authState.login)
        authPasswordEdit.setText(authState.password)

        authLoginInputLayout.error = authState.loginError
        authPasswordInputLayout.error = authState.passwordError

        enableLoad(authState.isLoading)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.auth_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: android.view.MenuItem?): kotlin.Boolean {
        return super.onOptionsItemSelected(item)
    }

    /***** MVP View methods implementation *****/

    override fun showLoginError(error: String) {
        authLoginInputLayout.error = error
        authState.loginError = error
    }

    override fun showPasswordError(error: String) {
        authPasswordInputLayout.error = error
        authState.passwordError = error
    }

    override fun showError(error: String) {
        ToastMessage.show(applicationContext, error)
    }

    override fun showError(errorId: Int) {
        ToastMessage.show(applicationContext, errorId)
    }

    override fun enableLoad(isLoading: Boolean) {
        authState.isLoading = isLoading
        authButton.visibility = if (isLoading) View.INVISIBLE else View.VISIBLE
        loadIndicator.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    override fun showWeather(weather: Weather) {
        val snackbar = Snackbar.make(
                authContentLayout,
                String.format(
                        getString(R.string.temperature_template),
                        weather.tempC),
                Snackbar.LENGTH_LONG)
        snackbar.duration = 8000
        snackbar.show()
    }

}