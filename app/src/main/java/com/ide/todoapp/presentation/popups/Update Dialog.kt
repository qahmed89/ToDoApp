package com.ide.todoapp.presentation.popups

import android.app.DatePickerDialog
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.ide.todoapp.R
import com.ide.todoapp.data.datasource.local.*
import com.ide.todoapp.presentation.utils.RecyclerAdappter
import java.util.*

class UpdateDialog constructor(toDoEntinty: ToDoEntinty) : DialogFragment() {
    val toDoEntinty = toDoEntinty
    lateinit var dateEditText: EditText
    lateinit var contentEditText: EditText
    lateinit var saveUpdateButton: Button
    lateinit var arrowImageButton: ImageButton
    lateinit var databaseViewModel: DatabaseViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var rootView: View = inflater.inflate(R.layout.addtask_popup, container, false)
        dateEditText = rootView.findViewById(R.id.date_et)
        contentEditText = rootView.findViewById(R.id.content_et)
        saveUpdateButton = rootView.findViewById(R.id.save_bt)
        arrowImageButton = rootView.findViewById(R.id.arrow_iv)
        dateEditText.setText(toDoEntinty.date)
        contentEditText.setText(toDoEntinty.content)
        val viewModelFactory=
            DatabaseViewModelFactory(DatabaseRepo(ToDoDatabase.getINSTANCE(requireContext())!!.toDoDao()))
        databaseViewModel=ViewModelProvider(this,viewModelFactory).get(DatabaseViewModel::class.java)


        val recyclerAdappter = RecyclerAdappter()
        val viewModel: DatabaseViewModel
        viewModel = ViewModelProvider(this).get(DatabaseViewModel::class.java)
        arrowImageButton.setOnClickListener {
            showDateDialog()
        }
        saveUpdateButton.setOnClickListener {
            updateTask()
            dismiss()
        }


        return rootView
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

    private fun inputCheck(date: String, content: String): Boolean {
        return !(TextUtils.isEmpty(date) && TextUtils.isEmpty(content))
    }

    private fun updateTask() {
        val date = dateEditText.text.toString()
        val content = contentEditText.text.toString()
        if (inputCheck(date, content)) {

            toDoEntinty.content=content
            toDoEntinty.date=date
            databaseViewModel.updateTask(toDoEntinty)
            Toast.makeText(requireContext(), "Successfully Updated!", Toast.LENGTH_LONG).show()
            dismiss()

        } else {
            Toast.makeText(requireContext(), "Please fill out all fields", Toast.LENGTH_LONG).show()
        }


    }
}