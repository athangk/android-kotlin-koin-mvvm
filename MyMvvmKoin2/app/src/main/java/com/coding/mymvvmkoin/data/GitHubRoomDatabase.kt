package com.coding.mymvvmkoin.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.coding.mymvvmkoin.data.openHab.GitHubDao
import com.coding.mymvvmkoin.data.openHab.GitHubEntity
import com.coding.mymvvmkoin.utilities.GIT_HUB_DATABASE



@Database(entities = [GitHubEntity::class], version = 1, exportSchema = false)
abstract class GitHubRoomDatabase : RoomDatabase() {


    abstract fun gitHubDao(): GitHubDao


    companion object {

        @Volatile
        private var INSTANCE: GitHubRoomDatabase? = null

        fun getInstance(context: Context): GitHubRoomDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        GitHubRoomDatabase::class.java,
                        GIT_HUB_DATABASE
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }

}
