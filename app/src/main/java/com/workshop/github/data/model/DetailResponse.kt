package com.workshop.github.data.model

data class DetailResponse(
        val login: String,
        val id: Int,
        val avatar_url: String,
        val followers_url: String,
        val following_url: String,
        val name: String,
        val following: String,
        val followers: String
)
