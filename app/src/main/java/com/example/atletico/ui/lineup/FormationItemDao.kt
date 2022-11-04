package com.example.atletico.ui.lineup

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface FormationItemDao {

    @Query("SELECT * from LastFormation")
    fun getFormation(): Flow<LastFormation>

    @Query("SELECT * from LastFormation WHERE id = :id")
    fun getFormationId(id: Int): Flow<LastFormation>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertFormation(item: LastFormation)

    @Update
    suspend fun updateFormation(item: LastFormation)
}