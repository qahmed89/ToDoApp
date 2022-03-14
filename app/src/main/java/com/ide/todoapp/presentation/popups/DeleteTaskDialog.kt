package com.ide.todoapp.presentation.popups

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.ide.todoapp.R
import com.ide.todoapp.data.datasource.local.*
import com.ide.todoapp.presentation.utils.RecyclerAdappter

class DeleteTaskDialog constructor(toDoEntinty: ToDoEntinty) : DialogFragment() {

    val toDoEntinty=toDoEntinty

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var rootView:View=inflater.inflate(R.layout.delete_popup,container,false)
        val yesButton:Button=rootView.findViewById(R.id.yes_bt)
        val noButton:Button=rootView.findViewById(R.id.no_bt)
        val recyclerAdappter=RecyclerAdappter()
        val viewModel:DatabaseViewModel
        val viewModelFactory=
            DatabaseViewModelFactory(DatabaseRepo(ToDoDatabase.getINSTANCE(requireContext())!!.toDoDao()))
        viewModel=ViewModelProvider(this,viewModelFactory).get(DatabaseViewModel::class.java)

        noButton.setOnClickListener {
            dismiss()
        }
        yesButton.setOnClickListener {
            //Api delete and database will be use here
            viewModel.delete(toDoEntinty)
            dismiss()
        }
        return rootView
    }
}