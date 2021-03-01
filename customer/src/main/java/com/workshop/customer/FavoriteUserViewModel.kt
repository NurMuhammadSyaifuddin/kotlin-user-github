package com.workshop.customer

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class FavoriteUserViewModel(application: Application): AndroidViewModel(application) {

    private var list = MutableLiveData<ArrayList<User>>()

    fun setFavoriteUser(context: Context){
        val cursor = context.contentResolver.query(
            DatabaseContract.FavoriteUserColumns.CONTENT_URI,
            null,
            null,
            null,
            null
        )

        val listConverted = MappingHelper.mapCurosorToArrayList(cursor)
        list.postValue(listConverted)

    }

    fun getFavoriteUser(): LiveData<ArrayList<User>> = list

}