package com.ide.todoapp.presentation.utils

import android.util.Patterns
import android.widget.EditText
import com.ide.todoapp.data.datasource.remote.AuthService
import com.ide.todoapp.data.datasource.remote.Repo
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.regex.Pattern

object Utils {fun CharSequence?.isValidEmail(): Boolean {
    return (!isNullOrEmpty()) && Patterns.EMAIL_ADDRESS.matcher(this).matches()
}

    fun CharSequence?.isValidPassword(): Boolean {
        val passwordPattern = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$"
        val pattern = Pattern.compile(passwordPattern)
        val matcher = pattern.matcher(this)
        return matcher.matches()
    }

    fun isValidateSignIn(email: EditText, password: EditText): Boolean {
        var emailText= email.text.toString()
        if (!emailText.isValidEmail()) {
            email.error = "Invalid email"
            return false

        }
        var passText= password.text.toString()
        if (!passText.isValidPassword()) {
            password.error = "Invalid password"
            return false
        }
        return true
    }
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(Repo.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val api: AuthService by lazy {
        retrofit.create(AuthService::class.java)
    }
}