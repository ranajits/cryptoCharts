package com.ranajit.tredzerv.ui.portfolio

import androidx.lifecycle.MutableLiveData
import com.ranajit.tredzerv.base.BaseViewModel
import com.ranajit.tredzerv.model.CurrencyResponse
import com.ranajit.tredzerv.network.NetworkHelper
import com.ranajit.tredzerv.utils.LogUtils

/**
 * Created by Ranajit on 15,May,2021
 */
class PFViewModel  : BaseViewModel() {

    var currenciesData = MutableLiveData<CurrencyResponse>()

    fun getCurrencies() {

        dialogMessage.value = "Please Wait...!"
        dialogVisibility.value = true
        NetworkHelper(mDisposable).requestApi<CurrencyResponse>(mService.getFinancierDetails(),
            object : NetworkHelper.RequestDelegate<CurrencyResponse> {
                override fun onSuccessResponse(data: CurrencyResponse?) {
                    currenciesData.value = data
                    dialogVisibility.value = false
                }

                override fun onErrorResponse(error: CurrencyResponse?) {
                    currenciesData.value = error
                    dialogVisibility.value = false
                }

                override fun onRequestFailed(`object`: Any?) {
                    LogUtils.logD(`object`.toString())
                    dialogVisibility.value = false
                }
            })
    }
}