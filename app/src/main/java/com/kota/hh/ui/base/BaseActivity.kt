package com.kota.hh.ui.base

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.inputmethod.InputMethodManager
import com.kota.hh.App
import com.kota.hh.injection.component.ActivityComponent
import com.kota.hh.injection.component.ConfigPersistentComponent
import com.kota.hh.injection.component.DaggerConfigPersistentComponent
import com.kota.hh.injection.module.ActivityModule
import java.util.concurrent.atomic.AtomicLong

open class BaseActivity : AppCompatActivity() {

    private val KEY_ACTIVITY_ID = "KEY_ACTIVITY_ID"
    private val NEXT_ID: AtomicLong = AtomicLong(0)
    private val componentMap = HashMap<Long, ConfigPersistentComponent>()

    private var activityComponent: ActivityComponent? = null
    private var activityId = 0L

    private lateinit var configPersistentComponent: ConfigPersistentComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState != null) {
            activityId = savedInstanceState.getLong(KEY_ACTIVITY_ID)
        } else {
            activityId = NEXT_ID.getAndIncrement()
        }
        if (!componentMap.containsKey(activityId)) {
            configPersistentComponent = DaggerConfigPersistentComponent.builder()
                    .applicationComponent(App.get(this).getComponent())
                    .build()
            componentMap.put(activityId, configPersistentComponent)
        } else {
            configPersistentComponent = componentMap[activityId]!!
        }
        activityComponent = configPersistentComponent.activityComponent(ActivityModule(this))
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putLong(KEY_ACTIVITY_ID, activityId)
    }

    override fun onDestroy() {
        if (!isChangingConfigurations) {
            componentMap.remove(activityId)
        }
        super.onDestroy()
    }

    fun activityComponent() = activityComponent

    fun hideKeyboard() {
        val view = this.currentFocus
        if (view != null) {
            (getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
                    .hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

}