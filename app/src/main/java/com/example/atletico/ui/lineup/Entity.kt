package com.example.atletico.ui.lineup

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Entity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name= "position")
    val itemPosition: Int,
    @ColumnInfo(name = "player")
    val itemPlayer: Int
)
