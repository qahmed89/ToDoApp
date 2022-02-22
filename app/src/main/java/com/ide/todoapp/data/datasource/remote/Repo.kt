package com.ide.todoapp.data.datasource.remote

import com.ide.todoapp.presentation.utils.Utils
import retrofit2.Response
import retrofit2.http.Body

class Repo {
    companion object {
        const val BASE_URL = "https://api-nodejs-todolist.herokuapp.com"
    }
    suspend fun postRegister(post: Post): Response<Post> {
    return Utils.api.postRegister(post)
    }
}