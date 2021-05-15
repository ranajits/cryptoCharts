package com.ranajit.tredzerv.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ranajit.tredzerv.network.APIClient
import io.reactivex.disposables.CompositeDisposable

open class BaseViewModel : ViewModel() {

    protected val dialogMessage: MutableLiveData<String> = MutableLiveData("")
    protected val dialogVisibility: MutableLiveData<Boolean> = MutableLiveData(false)
    protected val mDisposable = CompositeDisposable()
    protected val mService = APIClient.apiServiceClient


    fun getMessage() = dialogMessage
    fun getVisibility() = dialogVisibility


    override fun onCleared() {
        super.onCleared()

        if (!mDisposable.isDisposed) {
            mDisposable.dispose()
        }
    }

}