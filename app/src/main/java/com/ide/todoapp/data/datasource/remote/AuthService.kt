package com.ide.todoapp.data.datasource.remote

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthService {
    @POST("/user/register")
    suspend fun postRegister(@Body post: Post): Response<Post>
}