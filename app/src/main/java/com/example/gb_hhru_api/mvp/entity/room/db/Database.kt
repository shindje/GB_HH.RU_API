package com.example.gb_hhru_api.mvp.entity.room.db

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.gb_hhru_api.mvp.entity.room.RoomImageCached
import com.example.gb_hhru_api.mvp.entity.room.RoomVacancy
import com.example.gb_hhru_api.mvp.entity.room.dao.ImageCachedDao
import com.example.gb_hhru_api.mvp.entity.room.dao.VacancyDao

@androidx.room.Database(entities = [RoomVacancy::class, RoomImageCached::class], version = 1)
abstract class Database : RoomDatabase() {
    abstract val vacancyDao: VacancyDao
    abstract val imageCachedDao: ImageCachedDao

    companion object {
        const val DB_NAME = "database.db"
        private var instance: Database? = null
        fun getInstance() = instance ?: throw RuntimeException("Database has not been created. Please call create(context)")

        fun create(context: Context?) {
            if (instance == null) {
                instance = Room.databaseBuilder(context!!, Database::class.java, DB_NAME)
                    .fallbackToDestructiveMigration()
                    .build()
            }
        }
    }
}