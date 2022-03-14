package com.ide.todoapp.data.datasource.local

import androidx.lifecycle.LiveData

class DatabaseRepo(private val toDoDao: ToDoDao) {

    val getTODOsOrderdByDESC:LiveData<List<ToDoEntinty>> = toDoDao.getTODOsOrderdByDESC()
    fun addTODO(entinty: ToDoEntinty){
        toDoDao.addTODO(entinty)
    }
    fun deleteTODO(toDoEntinty: ToDoEntinty){
        toDoDao.deleteTODO(toDoEntinty)
    }
    fun updateTODO(toDoEntinty: ToDoEntinty){
        toDoDao.updateTODO(toDoEntinty)
    }
}