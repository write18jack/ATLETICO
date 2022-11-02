package com.example.atletico.ui.lineup

import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

@Dao
interface ItemDao {

    @Query("SELECT * from EntityX ORDER BY position ASC")
    fun getItems(): Flow<List<EntityX>>

    // 全てのアイテム取得 LiveData
    @Query("SELECT * FROM EntityX")
    fun selectAllWithLiveData(): LiveData<List<EntityX>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: EntityX)

    @Update
    suspend fun update(item: EntityX)

    @Delete
    suspend fun delete(item: EntityX)
}