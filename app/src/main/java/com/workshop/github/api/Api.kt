package com.workshop.github.api

import com.workshop.github.data.model.DetailResponse
import com.workshop.github.data.model.User
import com.workshop.github.data.model.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {

    @GET("search/users")
    @Headers("Authorization: token 73c0bf9feb76e36ee456b2d196f03d7c934c3aa6")
    fun getSearchUsers(
            @Query("q") query: String
    ): Call<UserResponse>

    @GET("users/{username}")
    @Headers("Authorization: token 73c0bf9feb76e36ee456b2d196f03d7c934c3aa6")
    fun getUserDetail(
            @Path("username") username: String
    ): Call<DetailResponse>

    @GET("users/{username}/followers")
    @Headers("Authorization: token 73c0bf9feb76e36ee456b2d196f03d7c934c3aa6")
    fun getFollowers(
            @Path("username") username: String
    ): Call<ArrayList<User>>

    @GET("users/{username}/following")
    @Headers("Authorization: token 73c0bf9feb76e36ee456b2d196f03d7c934c3aa6")
    fun getFollowing(
            @Path("username") username: String
    ): Call<ArrayList<User>>

}