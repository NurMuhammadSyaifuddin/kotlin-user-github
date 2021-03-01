package com.workshop.github.ui.favorite

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.workshop.github.data.local.FavoriteUser
import com.workshop.github.data.local.FavoriteUserDao
import com.workshop.github.data.local.UserDatabase

class FavoriteUserViewModel(application: Application): AndroidViewModel(application) {

    private var userDb: UserDatabase? = UserDatabase.getDatabase(application)
    private var userDao: FavoriteUserDao? = userDb?.favoriteUserDataDao()

    fun getFavoritUser(): LiveData<List<FavoriteUser>>? = userDao?.getFavoriteUser()

}