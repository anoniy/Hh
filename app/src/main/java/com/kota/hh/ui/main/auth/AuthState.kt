package com.kota.hh.ui.main.auth

import javax.inject.Inject

class AuthState @Inject constructor() {

    var login: String = "kota@gmail.com"
    var password: String = "QE2weqweq"
    var loginError: String = ""
    var passwordError: String = ""
    var isLoading: Boolean = false

}
