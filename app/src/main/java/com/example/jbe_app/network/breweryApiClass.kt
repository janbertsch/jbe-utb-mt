package com.example.jbe_app.network
import com.example.jbe_app.data.PostModel
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import retrofit2.http.GET
import retrofit2.Call
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://api.openbrewerydb.org/breweries/"

class BreweryApiClient {

    companion object{

        private var retrofit:Retrofit?=null

        fun getApiClient(): Retrofit {
            val gson = GsonBuilder()
                .setLenient()
                .create()
            val okHttpClient = OkHttpClient.Builder()
                .readTimeout(100, TimeUnit.SECONDS)
                .connectTimeout(100, TimeUnit.SECONDS)
                .build()
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build()
            }

            return retrofit!!
        }
    }

}

interface BreweryApiInterface{
    @GET("random?size=5")
    fun fetchAllPosts():
            Call<List<PostModel>>
}

