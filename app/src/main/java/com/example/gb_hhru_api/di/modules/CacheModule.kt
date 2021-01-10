package com.example.gb_hhru_api.di.modules

import androidx.room.Room
import com.example.gb_hhru_api.mvp.entity.room.db.Database
import com.example.gb_hhru_api.mvp.model.cache.IImageCache
import com.example.gb_hhru_api.mvp.model.cache.IVacanciesCache
import com.example.gb_hhru_api.mvp.model.cache.RoomImageCache
import com.example.gb_hhru_api.mvp.model.cache.RoomVacanciesCache
import com.example.gb_hhru_api.mvp.model.repo.IVacanciesRepo
import com.example.gb_hhru_api.ui.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CacheModule {
    @Singleton
    @Provides
    fun database(app: App): Database = Room.databaseBuilder(app, Database::class.java, Database.DB_NAME)
        .build()

    @Singleton
    @Provides
    fun vacanciesCache(database: Database): IVacanciesCache = RoomVacanciesCache(database)

    @Singleton
    @Provides
    fun imageCache(app: App, database: Database): IImageCache = RoomImageCache(app, database)
}