package com.ide.todoapp.data.datasource.repo

import androidx.annotation.WorkerThread
import com.ide.todoapp.data.datasource.model.body.PostLogIn
import com.ide.todoapp.data.datasource.model.body.PostRegister
import com.ide.todoapp.data.datasource.model.response.LogoutResponse
import com.ide.todoapp.data.datasource.model.response.PostLogInResponse
import com.ide.todoapp.data.datasource.model.response.PostResponse
import com.ide.todoapp.data.datasource.remote.AuthService

import retrofit2.Response


class Repo (val api: AuthService)  {
    @WorkerThread
    fun postRegister(postRegister: PostRegister): Response<PostResponse>{
        val result =api.postRegister(postRegister).execute()
        return result
    }
    fun postLogIn(postLogIn: PostLogIn): Response<PostLogInResponse>{
        val result =api.postLogIn(postLogIn).execute()
        return result
    }
    fun logout():Response<LogoutResponse>{
        val result =api.logout().execute()
        return result
    }

}