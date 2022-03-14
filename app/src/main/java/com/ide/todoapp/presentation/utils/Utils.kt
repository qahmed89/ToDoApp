package com.ide.todoapp.presentation.utils

import android.util.Patterns
import android.widget.EditText
import java.util.regex.Pattern

object Utils {
    fun CharSequence?.isValidEmail(): Boolean {
    return (!isNullOrEmpty()) && Patterns.EMAIL_ADDRESS.matcher(this).matches()
}
    fun CharSequence?.isValidUserName():Boolean{
        return (!isNullOrEmpty())
    }
    fun CharSequence?.isValidAge():Boolean{
        return(!isNullOrEmpty())
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
    fun isValidateSignUp(name:EditText,age:EditText,email: EditText, password: EditText): Boolean {
        var nameText=name.text.toString()
        if (!nameText.isValidUserName()){
            name.error="Enter User Name"
        return false
        }
        var ageText=age.text.toString()
        if(!ageText.isValidAge()){
            age.error="Enter your age"
            return false
        }
        var emailText= email.text.toString()
        if (!emailText.isValidEmail()) {
            email.error = "Invalid email"
            return false

        }
        var passText= password.text.toString()
        if (!passText.isValidPassword()) {
            password.error = "A minimum 8 characters password contains a combination of uppercase and lowercase letter and special charecter and number are required. "
            return false
        }
        return true
    }
    const val BASE_URL = "https://api-nodejs-todolist.herokuapp.com"


    }


