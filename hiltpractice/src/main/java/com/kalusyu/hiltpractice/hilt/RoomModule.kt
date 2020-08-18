package com.kalusyu.hiltpractice.hilt

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

/**
 * desc:
 *
 * @author biaowen.yu
 * @date 2020/8/15 12:58
 *
 **/
@Module
@InstallIn(ApplicationComponent::class)
object RoomModule {

    @Provides
    @Singleton
    fun provideAppDatabase(application: Application): AppDataBase {
        return Room.databaseBuilder(application, AppDataBase::class.java, "hilt.db")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    @Provides
    @Singleton
    fun providePersonDao(appDataBase: AppDataBase): PersonDao {
        return appDataBase.personDao()
    }
}