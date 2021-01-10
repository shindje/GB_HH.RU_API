package com.example.gb_hhru_api.mvp.entity.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class RoomImageCached(
    @PrimaryKey var url: String,
    var path: String
)