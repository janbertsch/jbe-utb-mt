package com.example.jbe_app.data
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface PostModelDao {
    @Query("SELECT * FROM PostModel")
    fun getAll(): LiveData<List<PostModel>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(vararg postModels: PostModel)

    @Query("SELECT * FROM PostModel WHERE name = :id")
    suspend fun getById(id: String): PostModel?

    @Delete
    fun delete(postModel: PostModel)
}