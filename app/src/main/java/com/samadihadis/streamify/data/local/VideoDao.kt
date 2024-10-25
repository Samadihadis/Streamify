package com.samadihadis.streamify.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.samadihadis.streamify.data.local.model.VideoEntity

@Dao
interface VideoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(videos: VideoEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(videos: List<VideoEntity>)

    @Update
    suspend fun update(video: VideoEntity)

    @Query("UPDATE video_table SET is_favorite=:isFavorite WHERE v_id = :vlId")
    suspend fun update(vlId: Int, isFavorite: Boolean)

    @Query("DELETE FROM video_table WHERE v_id = :vlId")
    suspend fun delete(vlId : Int)

    @Query("SELECT * FROM video_table ORDER BY v_id ASC")
    fun getAll(): List<VideoEntity>

    @Query("DELETE FROM video_table")
    suspend fun deleteAll()

}