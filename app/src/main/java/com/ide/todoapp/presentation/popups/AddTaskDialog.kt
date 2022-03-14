package com.ide.todoapp.presentation.popups

import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.ide.todoapp.R
import com.ide.todoapp.data.datasource.local.*
import java.util.*

class AddTaskDialog : DialogFragment() {
    private lateinit var databaseViewModel: DatabaseViewModel
    lateinit var dateEditText: EditText
    lateinit var contentEditText: EditText
    lateinit var saveButton: Button
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val viewModelFactory=
            DatabaseViewModelFactory(DatabaseRepo(ToDoDatabase.getINSTANCE(requireContext())!!.toDoDao()))
        databaseViewModel=ViewModelProvider(this,viewModelFactory).get(DatabaseViewModel::class.java)

        var rootView:View=inflater.inflate(R.layout.addtask_popup,container,false)
        contentEditText=rootView.findViewById(R.id.content_et)
        saveButton=rootView.findViewById(R.id.save_bt)
        saveButton.setOnClickListener {
            //Api save task and database will e use here
        addTask()

        }
        val arrowImage:ImageButton=rootView.findViewById(R.id.arrow_iv)
        arrowImage.setOnClickListener {
        showDateDialog()
        }
        dateEditText=rootView.findViewById(R.id.date_et)
        return rootView
    }

    private fun addTask() {
        val date= dateEditText.text.toString()
        val content= contentEditText.text.toString()
        if (inputCheck(date, content)){
            //Creat Object
            val todoEntity = ToDoEntinty(0,date,content)
            databaseViewModel.addTask(todoEntity)
            Toast.makeText(requireContext(),"Successfully add!",Toast.LENGTH_LONG).show()
            dismiss()

        }else{
            Toast.makeText(requireContext(),"Please fill out all fields",Toast.LENGTH_LONG).show()
        }


    }

    private fun showDateDialog() {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)


        val dpd = context?.let {
            DatePickerDialog(
                it,
                DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->

                    // Display Selected date in textbox
                    dateEditText.setText("$dayOfMonth/$monthOfYear/$year")

                },
                year,
                month,
                day
            )
        }

        dpd?.show()
    }
    private fun inputCheck(date:String,content:String):Boolean{
        return !(TextUtils.isEmpty(date)&&TextUtils.isEmpty(content))
    }
}