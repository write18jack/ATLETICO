package com.example.atletico.ui.lineup

import android.app.Application
import com.example.atletico.ui.lineup.ItemRoomDatabase

class SaveLineUpApplication : Application() {
    val database: ItemRoomDatabase by lazy { ItemRoomDatabase.getDatabase(this) }
}