package com.example.jbe_app.data

import android.util.Log
import com.example.jbe_app.network.BreweryApiClient
import com.example.jbe_app.network.BreweryApiInterface



class Brewery {
    private var apiInterface:BreweryApiInterface?=null

    init {
        apiInterface = BreweryApiClient.getApiClient().create(BreweryApiInterface::class.java)
    }

    fun fetchAllPosts(): List<PostModel>? {
        var posts: List<PostModel>? = null

        // Synchronously execute the call and return the result
        val response = apiInterface?.fetchAllPosts()?.execute()
        if (response?.isSuccessful == true) {
            posts = response.body()
        } else {
            Log.i("api call", "Error: ${response?.code()} ${response?.message()}")
        }
        return posts
    }
}