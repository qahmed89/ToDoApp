package com.ide.todoapp.data.datasource.model.response

import com.google.gson.annotations.SerializedName

data class LogoutResponse (
    @SerializedName("success")
    val success:Boolean
    )