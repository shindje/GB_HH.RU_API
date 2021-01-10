package com.example.gb_hhru_api.mvp.entity

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Employer (
    @Expose val id: String?,
    @Expose val name: String?,
    @Expose val logoUrls: LogoUrl?
) : Parcelable