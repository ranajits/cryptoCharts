package com.ranajit.tredzerv.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
open class BaseModel :Parcelable {
    
    @SerializedName("request_id")
    var requestId: String? = null

    @SerializedName("success")
    var success = false

    @SerializedName("response_code")
    var responseCode = 0

    @SerializedName("message")
    var message: String? = null
}
