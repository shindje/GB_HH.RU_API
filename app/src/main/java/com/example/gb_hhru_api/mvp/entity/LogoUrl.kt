package com.example.gb_hhru_api.mvp.entity

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LogoUrl (
    @Expose val original: String?,
    @Expose val size90: String?,
    @Expose val size240: String?,
) : Parcelable