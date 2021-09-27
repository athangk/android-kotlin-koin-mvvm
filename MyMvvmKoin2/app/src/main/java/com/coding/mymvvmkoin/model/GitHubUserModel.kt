package com.coding.mymvvmkoin.model

import com.google.gson.annotations.SerializedName

data class GitHubUserModel(
    @SerializedName("login")
    var username:String,
    @SerializedName ("avatar_url")
    var avatarURL:String)
