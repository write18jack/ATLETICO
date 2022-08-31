package com.example.atletico.ui.lineup

import android.app.Application

class LineupApplication: Application() {
    val database: ItemRoomDatabase by lazy { ItemRoomDatabase.getDatabase(this) }
}