package com.ranajit.tredzerv.network

import android.widget.Toast
import com.ranajit.tredzerv.TredzervApp
import com.ranajit.tredzerv.utils.LogUtils
import com.ranajit.tredzerv.utils.isNetworkAvailable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

open class NetworkHelper(compositeDisposable: CompositeDisposable) {


    /**
     * this flag use for when you call api from service.
     */
    val mDisposable = compositeDisposable
    private var requestDelegate: RequestDelegate<*>? = null
    private var call: Single<*>? = null
    private var displayUnAuthorizedToast: Toast? = null

    open fun <T> requestApi(
        call: Single<*>?,
        requestDelegate: RequestDelegate<*>?
    ) {
        this.call = call
        this.requestDelegate = requestDelegate
        requestData<Any>()
    }


    open fun <T> requestData() {
        try {
            if (isNetworkAvailable()) {
                mDisposable.add(
                    call!!
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({ response ->
                            if (response != null) {
                                (requestDelegate!! as RequestDelegate<T>).onSuccessResponse(response as T)
                            } else {
                                (requestDelegate!! as RequestDelegate<T>).onErrorResponse(response as T)
                            }
                        }, { throwable ->
                                requestDelegate!!.onRequestFailed(throwable)
                        })
                )
            } else {
                TredzervApp.instance.showToast("Please check your internet connection")
                requestDelegate!!.onRequestFailed(null)
            }
        } catch (e: Exception) {
            LogUtils.logE("Exception", e)
        }
    }

    /**
     * Generic interface getting callback from API response
     *
     * @param <T> Generic class object
    </T> */
    interface RequestDelegate<T> {
        fun onSuccessResponse(data: T?)
        fun onErrorResponse(error: T?)
        fun onRequestFailed(`object`: Any?)
    }

}
