package com.ide.todoapp.presentation

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ide.todoapp.R
import com.ide.todoapp.data.datasource.remote.ApiClient
import com.ide.todoapp.data.datasource.remote.Post
import com.ide.todoapp.data.datasource.remote.Repo
import com.ide.todoapp.data.datasource.remote.ViewModelFactory


class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: ApiClient
    lateinit var tvResponse :TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvResponse=findViewById(R.id.tvResponse)

        val repo=Repo()
        val viewModelFactory = ViewModelFactory(repo)
        viewModel= ViewModelProvider(this,viewModelFactory).get(ApiClient::class.java)
        val post=Post("Muhammad Nur Ali","muh.nurali43@gmail.com","12345678",20)
        viewModel.postRegister(post)
        viewModel.myResponse.observe(this, Observer { response ->
            if (response.isSuccessful){
                Log.d("Main",response.body().toString())
                Log.d("Main",response.code().toString())
                Log.d("Main",response.message())
            }

        })



    }
}


