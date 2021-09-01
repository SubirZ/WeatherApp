package com.example.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.data.local.entities.CityEntity

@Dao
interface CityDao : BaseDao<CityEntity> {

    @Query("SELECT * FROM CityEntity")
    suspend fun getCityList(): List<CityEntity>

    @Query("SELECT * FROM CityEntity WHERE id = :cityId")
    suspend fun getCityById(cityId: Int): CityEntity

    @Query("DELETE FROM CityEntity WHERE id = :cityId")
    fun deleteCityById(cityId: Int): Int
}