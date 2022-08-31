package com.example.atletico.ui.lineup

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDao {

    @Query("SELECT * from entity ORDER BY position ASC")
    fun getItems(): Flow<List<Entity>>

    @Query("SELECT * from entity WHERE id = :id")
    fun getItem(id:Int): Flow<Entity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: Entity)

    @Update
    suspend fun update(item: Entity)

    @Delete
    suspend fun delete(item:Entity)
}