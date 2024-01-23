package com.example.jbe_app.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PostModel (
    @PrimaryKey val name:String="",
    var city:String?=""
)