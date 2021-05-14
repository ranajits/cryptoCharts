package com.ranajit.tredzerv.utils

import android.annotation.SuppressLint
import android.content.Context
import java.io.IOException
import java.nio.charset.StandardCharsets

/**
 * Created by Ranajit on 14,May,2021
 */
class Util {
    companion object {
        @SuppressLint("NewApi")
        fun loadJSONFromAsserts(context: Context, fileName: String?): String? {
            var json: String? = null
            json = try {
                val `is` = context.assets.open(fileName!!)
                val size = `is`.available()
                val buffer = ByteArray(size)
                `is`.read(buffer)
                `is`.close()
                String(buffer, StandardCharsets.UTF_8)
            } catch (ex: IOException) {
                ex.printStackTrace()
                return null
            }
            return json
        }
    }
}