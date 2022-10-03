package com.example.atletico.ui.lineup

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//@Entity(indices = [Index(value = ["position"], unique = true)])
//@Entity(primaryKeys = ["position"])
@Entity
data class EntityX(
//    @PrimaryKey(autoGenerate = true)
//    val id: Int = 0,
    @PrimaryKey
    @ColumnInfo(name = "position")
    val itemPosition: Int,
    @ColumnInfo(name = "player")
    val itemPlayer: Int
)