package com.coding.mymvvmkoin.di.network

import com.coding.mymvvmkoin.model.GitHubUserModel
import retrofit2.Response
import retrofit2.http.*
import java.util.ArrayList


interface GitHubAPI {

    @GET(" users")
    @Headers("Content-Type: application/json")
    suspend fun getGitHubUsers(): Response<ArrayList<GitHubUserModel>>


}