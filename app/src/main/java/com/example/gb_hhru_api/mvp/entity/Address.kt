package com.example.gb_hhru_api.mvp.entity

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Address (
    @Expose val raw: String?,
): Parcelable