package com.kota.hh.util

import android.content.Context
import android.support.v4.util.PatternsCompat.EMAIL_ADDRESS
import android.text.TextUtils
import com.kota.hh.R
import com.kota.hh.injection.ApplicationContext
import rx.Observable
import java.util.regex.Pattern
import javax.inject.Inject

class Validator @Inject constructor(@ApplicationContext val context: Context) {

    private val SUCCESSFUL_VALIDATION = ""
    private val PASSWORD_LENGTH_PATTERN =
            Pattern.compile("^(?=.{6,}).+$")
    private val PASSWORD_CONTAINS_PATTERN_LATIN =
            Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9]).+$")
    private val PASSWORD_CONTAINS_PATTERN_CUR =
            Pattern.compile("^(?=.*[а-я])(?=.*[А-Я])(?=.*[0-9]).+$")

    fun validateEmail(email: String): Observable<String> {
        if (TextUtils.isEmpty(email) || !EMAIL_ADDRESS.matcher(email).matches()) {
            return Observable.just(context.getString(R.string.wrong_email))
        }
        return Observable.just(SUCCESSFUL_VALIDATION)
    }

    fun validatePassword(password: String): Observable<String> {
        if (TextUtils.isEmpty(password) || !PASSWORD_LENGTH_PATTERN.matcher(password).matches()) {
            return Observable.just(context.getString(R.string.wrong_password_length))
        }
        if (!PASSWORD_CONTAINS_PATTERN_LATIN.matcher(password).matches()) {
            return Observable.just(context.getString(R.string.wrong_password_contains))
        }
        return Observable.just(SUCCESSFUL_VALIDATION)
    }
}