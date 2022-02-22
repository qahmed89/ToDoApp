package com.ide.todoapp.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.ide.todoapp.R
import com.ide.todoapp.presentation.utils.Utils

import java.util.regex.Pattern

class SignInActivity : AppCompatActivity() {
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        emailEditText = findViewById(R.id.editTextTextEmailAddress)
        passwordEditText = findViewById(R.id.editTextTextPassword)
        val signup_tv: TextView = findViewById(R.id.signup_tv)
        signup_tv.setOnClickListener { v ->
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }


        val signInButton : Button = findViewById(R.id.button)
        signInButton.setOnClickListener {
            if (! Utils.isValidateSignIn(emailEditText, passwordEditText)) {
                return@setOnClickListener
            }
            val intentMain = Intent(this, MainActivity::class.java)
            startActivity(intentMain)

        }


    }




}









