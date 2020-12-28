package com.example.gb_hhru_api.mvp.entity

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Salary (
    @Expose val from: String?,
    @Expose val to: String?,
    @Expose val currency: String?,
) : Parcelable {
    override fun toString(): String {
        if (from != null && to != null) return "$from-$to $currency"
        else if (from != null) return "от $from $currency"
             else if (to != null) return "до $to $currency"
                  else return ""
    }
}