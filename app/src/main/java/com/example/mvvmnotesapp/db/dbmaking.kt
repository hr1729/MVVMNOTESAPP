package com.example.mvvmnotesapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
@Database(entities = [User::class],version = 1,exportSchema = false)
abstract class dbmaking:RoomDatabase() {
    abstract fun dao():Dao
    companion object{
        @Volatile
        var instance:dbmaking?=null
        private const val DATABASE_NAME="userdata"

        fun getInstance(context: Context):dbmaking?
        {
            if(instance == null)
            {
                synchronized(dbmaking::class.java)
                {
                    if(instance == null)
                    {
                        instance= Room.databaseBuilder(context,dbmaking::class.java,
                            DATABASE_NAME)
                            .fallbackToDestructiveMigration()
                            .build()
                    }
                }
            }

            return instance
        }

    }
}