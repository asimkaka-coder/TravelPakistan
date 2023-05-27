package com.tp.travelpakistan.ui.payment.data.models

import com.google.gson.annotations.SerializedName
import com.tp.travelpakistan.ui.home.data.toppicks.model.Tour

data class CancelTourRequestBody(
    @SerializedName("purchasedBy") val purchasedBy: String,
    @SerializedName("tour")val tour: Tour,
    @SerializedName("pickup")val pickup:String,
    @SerializedName("amount")val amount:Int,
)
