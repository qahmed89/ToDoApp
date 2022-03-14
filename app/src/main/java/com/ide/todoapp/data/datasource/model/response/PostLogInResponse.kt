package com.ide.todoapp.data.datasource.model.response

import com.google.gson.annotations.SerializedName

data class PostLogInResponse(
    @SerializedName("user")
     val userInfo: UserInfo,
    @SerializedName("token")
     val token:String
)