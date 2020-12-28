package com.example.gb_hhru_api.mvp.entity

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Search(
    @Expose val items: Array<Vacancy>,
    @Expose val found: String?,
) : Parcelable