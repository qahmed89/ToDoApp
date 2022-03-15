package com.ide.todoapp.presentation

import android.app.Application
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.lifecycle.ViewModelProvider
import com.ide.todoapp.R
import com.ide.todoapp.data.datasource.model.body.PostLogIn
import com.ide.todoapp.data.datasource.remote.ApiClient
import com.ide.todoapp.data.datasource.repo.Repo
import com.ide.todoapp.data.datasource.viewmmodel.MyViewModelFactory
//import com.ide.todoapp.data.datasource.viewmmodel.MyViewModelFactory
import com.ide.todoapp.data.datasource.viewmmodel.ToDoViewModel
import com.ide.todoapp.presentation.utils.Utils

class SignInActivity : AppCompatActivity() {
    private lateinit var viewModel: ToDoViewModel
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    lateinit var sharedPrefrance: SharedPreferences
    lateinit var prefEditor:SharedPreferences.Editor
    lateinit var progressBar: ProgressBar



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        progressBar=findViewById(R.id.progressBarSignin)
        emailEditText = findViewById(R.id.editTextTextEmailAddress)
        passwordEditText = findViewById(R.id.editTextTextPassword)
        val signup_tv: TextView = findViewById(R.id.signup_tv)
         sharedPrefrance=this.getSharedPreferences("IDE_ToDo", MODE_PRIVATE)
        prefEditor = sharedPrefrance.edit()

        val viewModelFactory =
            MyViewModelFactory(application,Repo(ApiClient().createService()))
        viewModel = ViewModelProvider(this,viewModelFactory).get(ToDoViewModel::class.java)

        signup_tv.setOnClickListener { v ->
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }


        val signInButton: Button = findViewById(R.id.button)
        observer()
        signInButton.setOnClickListener {
            if (!Utils.isValidateSignIn(emailEditText, passwordEditText)) {
                return@setOnClickListener
            }

            progressBar.visibility=View.VISIBLE
            if (!viewModel.hasInternetConnection()){
                progressBar.visibility= View.GONE
                return@setOnClickListener
            }
            viewModel.postLogIn(PostLogIn(
                emailEditText.text.toString(),
                passwordEditText.text.toString())
            ,this
            )




        }


    }



    private fun observer() {
        viewModel._logInMLData.observe(this, { result ->

            if (!result.isSuccessful) {
                Toast.makeText(this, "wronge Email or password", Toast.LENGTH_SHORT).show()
                progressBar.visibility=View.GONE
                return@observe
            }
            progressBar.visibility=View.GONE
            prefEditor.putString("token","Bearer ${result.body()?.token}").apply()
            val intentMain = Intent(this, TODOActivity::class.java)
            intentMain.putExtra("name",result.body()?.userInfo?.name)
            startActivity(intentMain)
            finish()

        })
    }


}









