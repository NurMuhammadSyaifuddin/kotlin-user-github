package com.workshop.github.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.workshop.github.api.RetrofitClient
import com.workshop.github.data.model.User
import com.workshop.github.data.model.UserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserViewModel: ViewModel() {

    val listUsers = MutableLiveData<ArrayList<User>>()

    fun setSearch(query: String){
        RetrofitClient.apiService
                .getSearchUsers(query)
                .enqueue(object : Callback<UserResponse>{
                    override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                        if (response.isSuccessful){
                            listUsers.postValue(response.body()?.items)
                        }
                    }

                    override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                        Log.d("Failue ", t.message)
                    }

                })
    }

    fun getSearchUser(): LiveData<ArrayList<User>> = listUsers

}