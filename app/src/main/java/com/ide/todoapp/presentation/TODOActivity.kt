package com.ide.todoapp.presentation

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.ide.todoapp.R
import com.ide.todoapp.data.datasource.local.*
import com.ide.todoapp.data.datasource.remote.ApiClient
import com.ide.todoapp.data.datasource.repo.Repo
import com.ide.todoapp.data.datasource.viewmmodel.MyViewModelFactory
import com.ide.todoapp.data.datasource.viewmmodel.ToDoViewModel
import com.ide.todoapp.presentation.popups.AddTaskDialog
import com.ide.todoapp.presentation.popups.DeleteTaskDialog
import com.ide.todoapp.presentation.popups.UpdateDialog
import com.ide.todoapp.presentation.utils.OnRecyclerClickListener
import com.ide.todoapp.presentation.utils.RecyclerAdappter
import kotlin.properties.Delegates


class TODOActivity : AppCompatActivity() {
    lateinit var apiViewModel:ToDoViewModel
    lateinit var sharedPrefrance: SharedPreferences
    lateinit var prefEditor: SharedPreferences.Editor
    lateinit var nameActoinBar:TextView
    lateinit var progressBar:ProgressBar
    lateinit var databaseViewModel: DatabaseViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sharedPrefrance =
            this.getSharedPreferences("IDE_ToDo", MODE_PRIVATE)
        prefEditor = sharedPrefrance.edit()
        progressBar=findViewById(R.id.progressBarMain)

        val apiViewModelFactory =
            MyViewModelFactory(application, Repo(ApiClient().createService()))
        apiViewModel = ViewModelProvider(this,apiViewModelFactory).get(ToDoViewModel::class.java)

        val toolbar: Toolbar? = findViewById(R.id.main_toolbar)
        setActionBar(toolbar)
        nameActoinBar = findViewById(R.id.mainName_toolbar)
        val intent=intent
        nameActoinBar.text=intent.getStringExtra("name")
        //logout
        //api logout problem
        observer()
        val logOut:ImageView=findViewById(R.id.mainBack_bt)
        logOut.setOnClickListener {
            progressBar.visibility=View.VISIBLE
            if (!apiViewModel.hasInternetConnection()){
                progressBar.visibility= View.GONE
                return@setOnClickListener
            }
            var token =sharedPrefrance.getString("token",null).toString()
            apiViewModel.logout(this,token)
            //prefEditor.clear().apply()
            //intent= Intent(this,SignInActivity::class.java)
            //startActivity(intent)
            //finish()

        }


        //recycler view
        val recyclerAdappter = RecyclerAdappter()
        val recyclerView: RecyclerView = findViewById(R.id.task_rec)
        recyclerView.adapter = recyclerAdappter
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerAdappter.setOnItemClickListener(object : OnRecyclerClickListener {
            override fun onItemClick(position: Int) {
                val updateDialog=UpdateDialog(recyclerAdappter.getData(position))
                updateDialog.show(supportFragmentManager, "updateTask")
            }

            override fun onImageClick(position: Int, imageView: ImageView) {

                val view=View.inflate(this@TODOActivity,R.layout.delete_popup,null)
                val dialogBuilder=AlertDialog.Builder(this@TODOActivity)
                dialogBuilder.setView(view)
                val dialog =dialogBuilder.create()
                dialog.show()
                dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
                val yesButton:Button=view.findViewById(R.id.yes_bt)
                yesButton.setOnClickListener {
                    databaseViewModel.delete(recyclerAdappter.getData(position))
                    dialog.dismiss()
                }
                val noButton:Button=view.findViewById(R.id.no_bt)
                noButton.setOnClickListener {
                    dialog.dismiss()
                }
            }
        })

        //database view model

       val viewModelFactory=DatabaseViewModelFactory(DatabaseRepo(ToDoDatabase.getINSTANCE(this)!!.toDoDao()))
        databaseViewModel=ViewModelProvider(this,viewModelFactory).get(DatabaseViewModel::class.java)
        databaseViewModel.readAllData.observe(this, Observer { task->
            recyclerAdappter.setData(task)
        })


        val addTaskButton: FloatingActionButton = findViewById(R.id.addTask_bt)
        addTaskButton.setOnClickListener {
            val dialog=AddTaskDialog()
            dialog.show(supportFragmentManager,"addTODO")
        }

    }

    private fun observer() {
        apiViewModel._logoutMLData.observe(this, { result ->

            if (!result.isSuccessful) {
                Toast.makeText(this, "Logout Failed", Toast.LENGTH_SHORT).show()
                progressBar.visibility=View.GONE
                return@observe
            }
            progressBar.visibility=View.GONE
            prefEditor.clear().apply()
            Toast.makeText(this, "Logout Success!", Toast.LENGTH_SHORT).show()
            intent= Intent(this,SignInActivity::class.java)
            startActivity(intent)
            finish()

        })
    }




}




