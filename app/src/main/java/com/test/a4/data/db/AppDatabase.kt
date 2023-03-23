package com.test.a4.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.test.a4.data.db.dao.TeamDao
import com.test.a4.data.db.entities.TeamEntity

@Database(entities = [TeamEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun teamDao(): TeamDao
}