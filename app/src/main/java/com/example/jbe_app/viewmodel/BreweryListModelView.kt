package com.example.jbe_app.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.jbe_app.data.Brewery
import com.example.jbe_app.data.PostModel

/**
 * The [ViewModel] that is attached to the [OverviewFragment].
 */
class BreweryListModelView(application: Application): AndroidViewModel(application){

    private var brewery:Brewery?=null
    var postModelListLiveData : LiveData<List<PostModel>>?=null

    init {
        brewery = Brewery()
        postModelListLiveData = MutableLiveData()
    }

    fun fetchAllPosts(){
        postModelListLiveData = brewery?.fetchAllPosts()
    }

}