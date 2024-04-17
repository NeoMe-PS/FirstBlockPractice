package com.ps_pn.firstblockpractice.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ps_pn.firstblockpractice.data.db.entity.CategoryDbModel
import com.ps_pn.firstblockpractice.data.db.entity.EventDbModel


@Database(
    entities = [CategoryDbModel::class, EventDbModel::class],
    version = 2,
    exportSchema = false
)
@TypeConverters(DataConverter::class)
abstract class AppDataBase : RoomDatabase() {

    companion object {
        private var database: AppDataBase? = null
        private const val DB_NAME = "app.db"
        private val LOCK = Any()

        fun getInstance(context: Context): AppDataBase {
            synchronized(LOCK) {
                database?.let { return it }
                val instance =
                    Room.databaseBuilder(context, AppDataBase::class.java, DB_NAME)
                        .build()
                database = instance
                return instance
            }
        }
    }

    abstract fun appDao(): AppDao

}