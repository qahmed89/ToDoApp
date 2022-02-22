package com.ide.todoapp.data.datasource.local

import androidx.room.*

@Dao
interface ToDoDao {
    @Insert
    fun instert (toDoEntinty: ToDoEntinty)
    @Update
    fun update (toDoEntinty: ToDoEntinty)
    @Delete
    fun delete (toDoEntinty: ToDoEntinty)
    @Query("SELECT * FROM todoapp")
    fun query ():List<ToDoEntinty>




}