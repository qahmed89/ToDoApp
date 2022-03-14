package com.ide.todoapp.data.datasource.local

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ToDoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addTODO (toDoEntinty: ToDoEntinty)
    @Delete
    fun deleteTODO (toDoEntinty: ToDoEntinty)
    @Query("SELECT * FROM todo_tasks ORDER BY id DESC")
    fun getTODOsOrderdByDESC ():LiveData<List<ToDoEntinty>>
    @Update
    fun updateTODO(toDoEntinty: ToDoEntinty)

}