package com.workshop.github.ui.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.workshop.github.api.RetrofitClient
import com.workshop.github.data.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowersViewModel: ViewModel() {

    private val listFollowers = MutableLiveData<ArrayList<User>>()

    fun setListFolowers(username: String){
        RetrofitClient.apiService
                .getFollowers(username)
                .enqueue(object : Callback<ArrayList<User>>{
                    override fun onResponse(call: Call<ArrayList<User>>, response: Response<ArrayList<User>>) {
                        if (response.isSuccessful){
                            listFollowers.postValue(response.body())
                        }
                    }

                    override fun onFailure(call: Call<ArrayList<User>>, t: Throwable) {
                        Log.d("Failure", t.message!!)
                    }

                })
    }

    fun getListFollowers(): LiveData<ArrayList<User>> = listFollowers

}