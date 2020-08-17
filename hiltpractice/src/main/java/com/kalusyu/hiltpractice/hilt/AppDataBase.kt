package com.kalusyu.hiltpractice.hilt

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * desc:
 *
 * @author biaowen.yu
 * @date 2020/8/15 12:56
 *
 **/
@Database(
    entities = arrayOf(PersonEntity::class),
    version = 1,
    exportSchema = false
)
abstract class AppDataBase : RoomDatabase() {
    abstract fun personDao(): PersonDao
}