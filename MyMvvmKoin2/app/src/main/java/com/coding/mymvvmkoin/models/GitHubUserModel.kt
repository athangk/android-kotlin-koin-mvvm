package com.coding.mymvvmkoin.models

import com.google.gson.annotations.SerializedName

data class GitHubUserModel(
    @SerializedName("login")
    var username:String,
    @SerializedName ("avatar_url")
    var avatarURL:String)
