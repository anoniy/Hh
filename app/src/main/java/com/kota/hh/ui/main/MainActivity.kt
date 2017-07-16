package com.kota.hh.ui.main

import android.os.Bundle
import android.view.View
import com.kota.hh.R
import com.kota.hh.ui.base.BaseActivity
import com.kota.hh.ui.main.auth.AuthActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity: BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        showAuthButton.setOnClickListener(onShowAuthButtonClick)
    }

    private val onShowAuthButtonClick = View.OnClickListener {
        startActivity(AuthActivity.createIntent(applicationContext))
    }

}