package com.example.data.local.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

interface BaseDao<T> {

    /**
     * Inserts an object in the table and follows the Replace OnConflict Strategy
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(obj: T): Long

    // Inserts an object in the table and follows the Ignore OnConflict Strategy.
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertWithIgnore(obj: T): Long

    /**
     * Inserts a list of objects in the table and follows the Replace OnConflict Strategy
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMultiple(objects: List<T>): Array<Long>

    /**
     * Inserts a list of objects in the table and follows the Ignore OnConflict Strategy
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMultipleWithIgnore(objects: List<T>): Array<Long>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(obj: T): Int

    @Delete
    suspend fun delete(obj: T): Int
}