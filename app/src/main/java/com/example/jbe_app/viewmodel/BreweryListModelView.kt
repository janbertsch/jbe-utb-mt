package com.example.jbe_app.viewmodel
import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.jbe_app.data.AppDatabase
import com.example.jbe_app.data.Brewery
import com.example.jbe_app.data.PostModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class BreweryListModelView(application: Application): AndroidViewModel(application){

    private var brewery: Brewery? = Brewery()
    private var db: AppDatabase = AppDatabase.getInstance(getApplication<Application>().applicationContext)
    private val _postModelListLiveData = MutableLiveData<List<PostModel>?>()
    var postModelListLiveData: LiveData<List<PostModel>?> = _postModelListLiveData
    private val _deletePostLiveData = MutableLiveData<Boolean>()
    var deletePostLiveData: LiveData<Boolean> = _deletePostLiveData
    val savedBreweriesLiveData: LiveData<List<PostModel>> = db.postModelDao().getAll()


    init {
        brewery = Brewery()
        deletePostLiveData = MutableLiveData()
    }

    fun fetchAllPosts() {
        viewModelScope.launch(Dispatchers.IO) {
            val posts = brewery?.fetchAllPosts()
            Log.i("ViewModel", "Fetched Posts: $posts")
            _postModelListLiveData.postValue(posts)
        }
    }

    fun toggleSaveBrewery(brewery: PostModel) {
        viewModelScope.launch(Dispatchers.IO) {
            val savedBrewery = db.postModelDao().getById(brewery.name)
            if (savedBrewery == null) {
                // Brewery not saved, so save it
                db.postModelDao().insertAll(brewery)
            } else {
                // Brewery already saved, so remove it
                db.postModelDao().delete(brewery)
            }
        }
    }
}
