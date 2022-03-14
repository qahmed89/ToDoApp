package com.ide.todoapp.presentation

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.lifecycle.ViewModelProvider
import com.ide.todoapp.R
import com.ide.todoapp.data.datasource.model.body.PostRegister
import com.ide.todoapp.data.datasource.remote.ApiClient
import com.ide.todoapp.data.datasource.repo.Repo
import com.ide.todoapp.data.datasource.viewmmodel.MyViewModelFactory
//import com.ide.todoapp.data.datasource.viewmmodel.MyViewModelFactory
import com.ide.todoapp.data.datasource.viewmmodel.ToDoViewModel
import com.ide.todoapp.presentation.utils.Utils

class SignUpActivity : AppCompatActivity() {
    lateinit var viewModel: ToDoViewModel
    lateinit var sharedPrefrance: SharedPreferences
    lateinit var prefEditor:SharedPreferences.Editor
    lateinit var progressBarSignUp: ProgressBar



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        sharedPrefrance =
            this.getSharedPreferences("IDE_ToDo", MODE_PRIVATE)
         prefEditor = sharedPrefrance.edit()
        //xml file accessing
        val backToSignInImageView:ImageView=findViewById(R.id.back_signin)
        val signin_tv: TextView = findViewById(R.id.signin_tv)
        val signup_bt: Button = findViewById(R.id.signup_bt)
        val userNameEditText: EditText = findViewById(R.id.username_et)
        val userAgeEditText: EditText = findViewById(R.id.age_et)
        val emailEditTexrSignUp: EditText = findViewById(R.id.email_et)
        val passwordEditTextSignUp: EditText = findViewById(R.id.password_et)
        progressBarSignUp=findViewById(R.id.progressBarSignup)
        val viewModelFactory =
            MyViewModelFactory(application,Repo(ApiClient().createService()))
        viewModel = ViewModelProvider(this,viewModelFactory).get(ToDoViewModel::class.java)
        //when you click on "Sign in"you will back to signIn activity
        signin_tv.setOnClickListener { v ->
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }
        backToSignInImageView.setOnClickListener {
           val signInIntent=Intent(this,SignInActivity::class.java)
            startActivity(signInIntent)
        }
        observer()
        signup_bt.setOnClickListener { v ->

            if (!Utils.isValidateSignUp(
                    userNameEditText,
                    userAgeEditText,
                    emailEditTexrSignUp,
                    passwordEditTextSignUp
                )
            ){
                return@setOnClickListener
            }
            progressBarSignUp.visibility=View.VISIBLE
                if (!viewModel.hasInternetConnection()){
                    progressBarSignUp.visibility= View.GONE
                }

            viewModel.postRegister(
                PostRegister(
                    userNameEditText.text.toString(),
                    emailEditTexrSignUp.text.toString(),
                    passwordEditTextSignUp.text.toString(),
                    userAgeEditText.text.toString().toInt()
                ),this
            )

        }
    }

    private fun observer() {
        viewModel._registerMLData.observe(this, { result ->
            if (!result.isSuccessful) {
                Toast.makeText(this, "Email is already exaist", Toast.LENGTH_SHORT).show()
                progressBarSignUp.visibility=View.GONE
                return@observe
            }
            progressBarSignUp.visibility=View.GONE
            prefEditor.putString("token","${result.body()?.token}").apply()
            prefEditor.putString("name","${result.body()?.userInfo?.name}").apply()
            val intentMain = Intent(this, TODOActivity::class.java)
            startActivity(intentMain)
            finish()

        })
    }


}