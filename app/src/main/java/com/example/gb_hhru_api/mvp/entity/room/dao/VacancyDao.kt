package com.example.gb_hhru_api.mvp.entity.room.dao

import androidx.room.*
import com.example.gb_hhru_api.mvp.entity.room.RoomVacancy

@Dao
interface VacancyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vacancy: RoomVacancy)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg vacancys: RoomVacancy)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vacancys: List<RoomVacancy>)

    @Update
    fun update(vacancy: RoomVacancy)

    @Update
    fun update(vararg vacancys: RoomVacancy)

    @Update
    fun update(vacancys: List<RoomVacancy>)

    @Delete
    fun delete(vacancy: RoomVacancy)

    @Delete
    fun delete(vararg vacancys: RoomVacancy)

    @Delete
    fun delete(vacancys: List<RoomVacancy>)

    @Query("DELETE FROM RoomVacancy")
    fun deleteAll()

    @Query("SELECT * FROM RoomVacancy")
    fun getAll(): List<RoomVacancy>
}