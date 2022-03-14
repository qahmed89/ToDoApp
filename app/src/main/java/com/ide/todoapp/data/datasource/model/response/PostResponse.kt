package com.ide.todoapp.data.datasource.model.response

import com.google.gson.annotations.SerializedName

data class PostResponse (
    @SerializedName("user")
    val userInfo: UserInfo,
    @SerializedName("token")
    val token:String)
data class UserInfo (
    @SerializedName("name")
    val name:String,
    @SerializedName("age")
    val age:Int,
    @SerializedName("email")
    val email:String)