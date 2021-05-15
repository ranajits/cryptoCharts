package com.ranajit.tredzerv.utils

import android.util.Log
import com.ranajit.tredzerv.BuildConfig

class LogUtils {

    companion object {
        private val isDebug: Boolean = BuildConfig.DEBUG
        private const val TAG = "TREDZERV"
        fun logD(msg: String?) {
            if (isDebug) {
                if (msg != null) {
                    Log.e(TAG, msg)
                }
            }
        }

        fun logD(tag: String, msg: String?) {
            if (isDebug) {
                if (msg != null) {
                    Log.e("$TAG : $tag", msg)
                }
            }
        }

        fun logD(tag: String, msg: String?, e: Exception?) {
            if (isDebug) {
                Log.e("$TAG : $tag", msg, e)
            }
        }

        fun logE(msg: String?) {
            if (isDebug) {
                if (msg != null) {
                    Log.e(TAG, msg)
                }
            }
        }

        fun logE(tag: String, msg: String?) {
            if (isDebug) {
                if (msg != null) {
                    Log.e("$TAG : $tag", msg)
                }
            }
        }

        fun logE(tag: String, msg: String?, e: Exception?) {
            if (isDebug) {
                Log.e("$TAG : $tag", msg, e)
            }
        }

        fun logE(tag: String, msg: String?, e: Throwable?) {
            if (isDebug) {
                Log.e("$TAG : $tag", msg, e)
            }
        }

        fun logI(msg: String?) {
            if (isDebug) {
                if (msg != null) {
                    Log.i(TAG, msg)
                }
            }
        }

        fun logI(tag: String, msg: String?) {
            if (isDebug) {
                if (msg != null) {
                    Log.i("$TAG : $tag", msg)
                }
            }
        }

        fun logV(msg: String?) {
            if (isDebug) {
                if (msg != null) {
                    Log.v(TAG, msg)
                }
            }
        }

        fun logV(tag: String, msg: String?) {
            if (isDebug) {
                if (msg != null) {
                    Log.v("$TAG : $tag", msg)
                }
            }
        }

        fun logW(msg: String?) {
            if (isDebug) {
                if (msg != null) {
                    Log.w(TAG, msg)
                }
            }
        }

        fun logW(tag: String, msg: String?) {
            if (isDebug) {
                if (msg != null) {
                    Log.w("$TAG : $tag", msg)
                }
            }
        }

        fun logWTF(msg: String?) {
            if (isDebug) {
                Log.wtf(TAG, msg)
            }
        }

        fun logWTF(tag: String, msg: String?) {
            if (isDebug) {
                Log.wtf("$TAG : $tag", msg)
            }
        }

        fun logE(msg: String?, e: Exception?) {
            if (isDebug) {
                Log.e(TAG, msg, e)
            }
        }
    }
}