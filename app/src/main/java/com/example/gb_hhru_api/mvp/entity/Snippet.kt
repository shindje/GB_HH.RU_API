package com.example.gb_hhru_api.mvp.entity

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

fun cut(s: String?): String {
    val MAX_LENGTH = 100
    if (s == null)
        return ""
    else
        return if (s.length > MAX_LENGTH) s.substring(0, MAX_LENGTH) + "..." else s
}

@Parcelize
data class Snippet(
    @Expose val requirement: String?,
    @Expose val responsibility: String?,
) : Parcelable {

    fun requirementShort(): String {
        return cut(requirement)
    }

    fun responsibilityShort(): String {
        return cut(responsibility)
    }

    fun requirementLong(): String? {
        return requirement
    }

    fun responsibilityLong(): String? {
        return responsibility
    }

    fun shortsAsArray(): Array<String> {
        return arrayOf(requirementShort(), responsibilityShort())
    }
}