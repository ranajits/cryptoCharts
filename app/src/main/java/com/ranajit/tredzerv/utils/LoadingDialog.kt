package com.ranajit.tredzerv.utils

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatDialog
import com.ranajit.tredzerv.R
import kotlinx.android.synthetic.main.dialog_progress.*

class LoadingDialog(context: Context) : AppCompatDialog(context) {

        val view = LayoutInflater.from(context).inflate(R.layout.dialog_progress, null)

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(view)
            setCancelable(false)
        }

        fun setMessage(msg: String) {
            if (txtMessage != null)
                txtMessage!!.text = msg
        }

    }