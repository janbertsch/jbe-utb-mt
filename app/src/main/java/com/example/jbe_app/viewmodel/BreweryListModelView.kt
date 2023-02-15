package com.example.jbe_app.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.jbe_app.data.Brewery
import com.example.jbe_app.data.PostModel


class BreweryListModelView(application: Application): AndroidViewModel(application){

    private var brewery:Brewery?=null
    var postModelListLiveData : LiveData<List<PostModel>>?=null
    var deletePostLiveData:LiveData<Boolean>?=null

    init {
        brewery = Brewery()
        postModelListLiveData = MutableLiveData()
        deletePostLiveData = MutableLiveData()
    }

    fun fetchAllPosts(){
        postModelListLiveData = brewery?.fetchAllPosts()
    }
}