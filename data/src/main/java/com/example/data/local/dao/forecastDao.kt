package com.example.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.data.local.entities.CityEntity
import com.example.data.local.entities.ForecastEntity
import com.example.domain.Forecast

@Dao
interface ForecastDao : BaseDao<ForecastEntity> {

    @Query("SELECT * FROM ForecastEntity WHERE cityId = :cityId")
    suspend fun getForecastListByCityId(cityId: Int): List<ForecastEntity>

    @Query("DELETE FROM ForecastEntity WHERE cityId = :cityId")
    fun deleteForecastsByCityId(cityId: Int): Int
}