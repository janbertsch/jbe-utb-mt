package com.example.jbe_app.data
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query


@Dao
interface PostModelDao {
    @Query("SELECT * FROM PostModel")
    fun getAll(): List<PostModel>

    @Insert
    fun insertAll(vararg postModels: PostModel)

    @Delete
    fun delete(postModel: PostModel)
}