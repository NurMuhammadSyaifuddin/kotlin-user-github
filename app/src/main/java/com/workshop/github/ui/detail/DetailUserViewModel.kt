package com.workshop.github.ui.detail

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.workshop.github.api.RetrofitClient
import com.workshop.github.data.local.FavoriteUser
import com.workshop.github.data.local.FavoriteUserDao
import com.workshop.github.data.local.UserDatabase
import com.workshop.github.data.model.DetailResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.Executors

class DetailUserViewModel(application: Application) : AndroidViewModel(application) {

    private var userDb: UserDatabase? = UserDatabase.getDatabase(application)
    private var userDao: FavoriteUserDao? = userDb?.favoriteUserDataDao()

    private val user = MutableLiveData<DetailResponse>()

    fun setUserDetail(username: String) {
        RetrofitClient.apiService
            .getUserDetail(username)
            .enqueue(object : Callback<DetailResponse> {
                override fun onResponse(
                    call: Call<DetailResponse>,
                    response: Response<DetailResponse>
                ) {
                    if (response.isSuccessful) {
                        user.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<DetailResponse>, t: Throwable) {
                    Log.d("Failure", t.message)
                }

            })
    }

    fun getUserDetail(): LiveData<DetailResponse> = user

    fun addToFavorite(username: String, id: Int, avatarUrl: String){
        val dispatchers = Executors.newCachedThreadPool().asCoroutineDispatcher()
        CoroutineScope(dispatchers).launch {
            val user = FavoriteUser(id, username, avatarUrl)
            userDao?.addToFavoriteUser(user)
        }
    }

    suspend fun checkUser(id: Int) = userDao?.checkUser(id)

    fun removeFromFavorite(id: Int){
        val dispatchers = Executors.newCachedThreadPool().asCoroutineDispatcher()
        CoroutineScope(dispatchers).launch {
            userDao?.removeFavoriteUser(id)
        }
    }

}