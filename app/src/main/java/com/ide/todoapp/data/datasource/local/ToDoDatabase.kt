package com.ide.todoapp.data.datasource.local

import android.content.Context
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Room
import androidx.room.RoomDatabase

abstract class ToDoDatabase : RoomDatabase() {
    companion object{

        private var instance : ToDoDatabase?=null

        fun getINSTANCE(context :Context):ToDoDatabase? {
            synchronized(this){
            if (instance == null) {
                return Room.databaseBuilder(
                    context.applicationContext, ToDoDatabase::class.java,
                    "ToDo_db"
                ).build()
            } else {
                return instance
            }
        }
        }
    }






}

@Entity(tableName = "ToDoApp")
data class ToDoEntinty(
    val userName : String?=null,
    val uesrEmail: String?=null,
    val userPassword:String?=null
){
    @PrimaryKey(autoGenerate = true)
    val id :Int?=null
}