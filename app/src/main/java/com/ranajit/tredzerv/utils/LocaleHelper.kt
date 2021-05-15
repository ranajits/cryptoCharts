package com.ranajit.tredzerv.utils

import android.content.Context
import android.content.ContextWrapper
import android.content.res.Configuration
import android.os.Build
import android.text.TextUtils
import java.util.*


class LocaleHelper(base: Context) : ContextWrapper(base) {
    companion object {
        fun wrap(context: Context, language: String): ContextWrapper {
            var context: Context = context
            if (TextUtils.isEmpty(language.trim { it <= ' ' })) {
                return LocaleHelper(context)
            }
            val config: Configuration = context.resources.configuration
            val locale = Locale(language)
            Locale.setDefault(locale)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                config.setLocale(locale)
            } else {
                config.locale = locale
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                config.setLayoutDirection(locale)
                context = context.createConfigurationContext(config)
            } else {
                context.resources
                    .updateConfiguration(config, context.resources.displayMetrics)
            }
            return LocaleHelper(context)
        }
    }
}