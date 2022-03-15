package com.ide.todoapp.data.datasource.remote

import com.ide.todoapp.data.datasource.model.body.PostLogIn
import com.ide.todoapp.data.datasource.model.body.PostRegister
import com.ide.todoapp.data.datasource.model.body.PostTask
import com.ide.todoapp.data.datasource.model.response.LogoutResponse
import com.ide.todoapp.data.datasource.model.response.PostLogInResponse
import com.ide.todoapp.data.datasource.model.response.PostResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface AuthService {
    @Headers("Content-Type:application/json",
        "Accept:application/json")
    @POST("/user/register")
    fun postRegister(@Body postRegister: PostRegister): Call<PostResponse>
    @POST("/user/login")
    fun postLogIn(@Body postLogIn: PostLogIn):Call<PostLogInResponse>

    @POST("user/logout")
    fun logout(@Header("Authorization") token: String): Call<LogoutResponse>
}