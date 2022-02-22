package com.ide.todoapp.data.datasource.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.Dispatcher
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient(private val repo: Repo):ViewModel(){

   var myResponse: MutableLiveData<Response<Post>> = MutableLiveData()

    fun postRegister(post: Post){
    viewModelScope.launch {
        val response:Response<Post> = repo.postRegister(post)
        myResponse.value= response
    }
    }
}