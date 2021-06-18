package com.decagonhq.clads.data.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DeliveryAddressModel(
    val deliveryAddress: String,
    val city: String,
    val state: String
) : Parcelable