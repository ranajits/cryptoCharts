package com.ranajit.tredzerv.model

import com.google.gson.annotations.SerializedName


/**
 * Created by Ranajit on 14,May,2021
 */
data class CurrencyResponse(
    @SerializedName("data")
    val `data`: Data?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("request_id")
    val requestId: String?,
    @SerializedName("response_code")
    val responseCode: Int?,
    @SerializedName("success")
    val success: Boolean?
) {
    data class Data(
        @SerializedName("portfolios")
        val portfolios: ArrayList<Portfolio?>?
    ) {
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
        )
    }
}