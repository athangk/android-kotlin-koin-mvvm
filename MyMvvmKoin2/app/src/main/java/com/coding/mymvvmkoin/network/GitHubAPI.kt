package com.coding.mymvvmkoin.network

import com.coding.mymvvmkoin.model.GitHubItemModel
import retrofit2.Response
import retrofit2.http.*


interface GitHubAPI {

    @GET("search/users")
    @Headers("Content-Type: application/json")
    suspend fun getGitHubUsers(@Query("q") q:String): Response<GitHubItemModel>

}