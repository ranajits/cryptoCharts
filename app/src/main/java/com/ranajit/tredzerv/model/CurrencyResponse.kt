package com.ranajit.tredzerv.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


/**
 * Created by Ranajit on 14,May,2021
 */
@Parcelize
data class CurrencyResponse(
    @SerializedName("data")
    val `data`: Data?,
    @SerializedName("request_id")
    var requestId: String?,
    @SerializedName("success")
    var success: Boolean,
    @SerializedName("response_code")
    var responseCode: Int,
    @SerializedName("message")
    var message: String?
) : Parcelable {
    @Parcelize
    data class Data(
        @SerializedName("portfolios")
        val portfolios: ArrayList<Portfolio?>?
    ) : Parcelable {
        @Parcelize
        data class Portfolio(
            @SerializedName("currency_name")
            val currencyName: String?,
            @SerializedName("currency_to_dollar")
            val currencyToDoller: String?,
            @SerializedName("currency_value")
            val currencyValue: String?,
            @SerializedName("logo")
            val logo: String?,
            @SerializedName("share_percentage")
            val sharePercentage: String?,
            @SerializedName("chart_shadow_color")
            val chartShadowColor: String?,
            @SerializedName("chart_line_color")
            val chartLineColor: String?,
            @SerializedName("chart_data")
            var chartData: List<Pair<String, Float>>
        ) : Parcelable
    }
}