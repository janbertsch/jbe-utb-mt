package com.example.jbe_app.data

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.jbe_app.network.BreweryApiClient
import com.example.jbe_app.network.BreweryApiInterface
import retrofit2.Callback
import retrofit2.Call
import retrofit2.Response


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