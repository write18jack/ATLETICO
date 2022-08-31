package com.example.atletico.ui.lineup

import android.app.Application

class SaveLineUpApplication : Application() {
    val database: ItemRoomDatabase by lazy { ItemRoomDatabase.getDatabase(this) }
}