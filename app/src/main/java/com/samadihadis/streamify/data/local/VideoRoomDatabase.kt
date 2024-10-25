package com.samadihadis.streamify.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.samadihadis.streamify.data.local.model.VideoEntity

@Database(entities = [VideoEntity::class], version = 1, exportSchema = false)
abstract class VideoRoomDatabase : RoomDatabase() {

    abstract fun videoDao(): VideoDao

    companion object {
        @Volatile
        private var INSTANCE: VideoRoomDatabase? = null
        fun getDatabase(context: Context): VideoRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    VideoRoomDatabase::class.java,
                    "video_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}