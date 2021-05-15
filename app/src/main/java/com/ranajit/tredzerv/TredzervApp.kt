package com.ranajit.tredzerv

import android.widget.Toast
import androidx.multidex.MultiDexApplication

/**
 * Created by DELL on 15,May,2021
 */
class TredzervApp : MultiDexApplication() {

    private val TAG: String = "TredzervApp"

    companion object {
        lateinit var instance: TredzervApp
            private set
    }

    fun showToast(data: String?) {
        Toast.makeText(
            applicationContext, data,
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}