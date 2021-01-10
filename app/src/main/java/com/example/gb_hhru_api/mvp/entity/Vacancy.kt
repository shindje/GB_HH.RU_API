package com.example.gb_hhru_api.mvp.entity

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Vacancy(
    @Expose val id: String,
    @Expose val name: String,
    @Expose val url: String,
    @Expose val salary: Salary?,
    @Expose val employer: Employer?,
    @Expose val address: Address?,
    @Expose val snippet: Snippet?,
    @Expose val alternateUrl: String?,
) : Parcelable