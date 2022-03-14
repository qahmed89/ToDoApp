package com.ide.todoapp.data.datasource.local

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ide.todoapp.data.datasource.repo.Repo
import com.ide.todoapp.data.datasource.viewmmodel.ToDoViewModel
import java.util.concurrent.Executors

class DatabaseViewModel(private val databaseRepo: DatabaseRepo):ViewModel() {
     val readAllData:LiveData<List<ToDoEntinty>>

    init {
        readAllData=databaseRepo.getTODOsOrderdByDESC
    }
    fun addTask(toDoEntinty: ToDoEntinty) {
        val executors = Executors.newSingleThreadExecutor()
        executors.execute {
            databaseRepo.addTODO(toDoEntinty)
        }
    }
        fun updateTask(toDoEntinty: ToDoEntinty) {
            val executors = Executors.newSingleThreadExecutor()
            executors.execute {
                databaseRepo.updateTODO(toDoEntinty)
            }

        }

        fun delete(toDoEntinty: ToDoEntinty) {
            val executors = Executors.newSingleThreadExecutor()
            executors.execute {
                databaseRepo.deleteTODO(toDoEntinty)
            }
        }
    }
class DatabaseViewModelFactory (private val repo: DatabaseRepo) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(DatabaseViewModel::class.java!!)) {
            DatabaseViewModel(repo) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}