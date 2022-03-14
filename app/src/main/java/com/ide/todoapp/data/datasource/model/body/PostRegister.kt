package com.ide.todoapp.data.datasource.model.body

import com.google.gson.annotations.SerializedName

data class PostRegister (
    @SerializedName("name")
    val name:String,
    @SerializedName("email")
    val email:String,
    @SerializedName("password")
    val password:String,
    @SerializedName("age")
    val age:Int
    )