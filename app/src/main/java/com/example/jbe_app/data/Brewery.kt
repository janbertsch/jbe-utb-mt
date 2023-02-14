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

    fun fetchAllPosts(): LiveData<List<PostModel>> {
        val data = MutableLiveData<List<PostModel>>()

        apiInterface?.fetchAllPosts()?.enqueue(object : Callback<List<PostModel>>{

            @SuppressLint("NullSafeMutableLiveData")
            override fun onFailure(call: Call<List<PostModel>>, t: Throwable) {
                data.value = null // buildable with error
            }

            @SuppressLint("NullSafeMutableLiveData")
            override fun onResponse(
                call: Call<List<PostModel>>,
                response: Response<List<PostModel>>
            ) {

                val res = response.body()
                if (response.code() == 200 &&  res!=null){
                    Log.i("tag", "DID a FETCHALL with 200 " + res.toString())
                    data.value = res // buildable with error
                }else{
                    data.value = null // buildable with error
                }
            }
        })


        return data
    }
}