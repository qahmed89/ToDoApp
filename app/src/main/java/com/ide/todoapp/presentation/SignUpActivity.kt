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

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val signin_tv: TextView = findViewById(R.id.signin_tv)
        signin_tv.setOnClickListener { v ->
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }

        val emailEditTexrSignUp: EditText = findViewById(R.id.email_et)
        val passwordEditTextSignUp: EditText = findViewById(R.id.password_et)


        val signup_bt: Button = findViewById(R.id.signup_bt)
        signup_bt.setOnClickListener { v ->
            if (! Utils.isValidateSignIn(emailEditTexrSignUp, passwordEditTextSignUp)) {
                return@setOnClickListener
            }
            val intentMain = Intent(this, MainActivity::class.java)
            startActivity(intentMain)
        }
    }




}