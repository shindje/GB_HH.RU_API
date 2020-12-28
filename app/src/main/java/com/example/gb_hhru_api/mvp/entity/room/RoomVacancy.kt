package com.example.gb_hhru_api.mvp.entity.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class RoomVacancy(
    @PrimaryKey var id: String,
    var name: String,
    var url: String?,
    var salaryFrom: String?,
    var salaryTo: String?,
    var salaryCurrency: String?,
    var addressRaw: String?,
    var employerId: String?,
    var employerName: String?,
    var employerLogoUrlOriginal: String?,
    var employerLogoUrlSize90: String?,
    var employerLogoUrlSize240: String?,
    var requirement: String?,
    var responsibility: String?,
)