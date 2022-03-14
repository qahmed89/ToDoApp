package com.ide.todoapp.presentation

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import com.ide.todoapp.R

class SplashActivity : AppCompatActivity() {
    lateinit var sharedPrefrance: SharedPreferences
    lateinit var prefEditor: SharedPreferences.Editor
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        sharedPrefrance=this.getSharedPreferences("IDE_ToDo", MODE_PRIVATE)
        prefEditor = sharedPrefrance.edit()
        Log.i("hasToken?",sharedPrefrance.getString("token","null").toString())
        val imageView: ImageView = findViewById(R.id.iv_note)
        imageView.alpha = 0f
        imageView.animate().setDuration(2000).alpha(1f).withEndAction {


            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            checkToken("token")


        }
    }
    fun checkToken(prefKey:String){
        if (sharedPrefrance.getString(prefKey, null)!=null){
            val mainIntent = Intent(this, TODOActivity::class.java)
            Log.wtf("token",sharedPrefrance.getString(prefKey, ""))
            startActivity(mainIntent)
            finish()
        }
        else{
            val signInIntent = Intent(this, SignInActivity::class.java)
            startActivity(signInIntent)
            finish()
        }
    }
}