package com.ranajit.tredzerv.network

import com.ranajit.tredzerv.model.CurrencyResponse
import io.reactivex.Single
import retrofit2.http.GET

interface APIInterface {

//    @GET("/api/v1/transactions/get-transaction-financier-details")
//    @GET("https://jsonkeeper.com/b/BFAF")
    @GET("/b/BFAF")
    fun getFinancierDetails(): Single<CurrencyResponse>
}