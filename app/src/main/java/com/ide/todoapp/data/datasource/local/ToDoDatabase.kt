package com.ide.todoapp.data.datasource.local

import android.content.Context
import android.os.Parcelable
import androidx.room.*

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.versionedparcelable.ParcelField

@Database(entities = [ToDoEntinty::class], version = 1)
abstract class ToDoDatabase : RoomDatabase() {
    abstract fun toDoDao(): ToDoDao

    companion object {


        @Volatile
        private var INSTANCE: ToDoDatabase? = null

        fun getINSTANCE(context: Context): ToDoDatabase? {
            val inctance = INSTANCE
            if (inctance!=null){
                return inctance
            }
            synchronized(this){
                val tempInctanse=Room.databaseBuilder(context.applicationContext,
                ToDoDatabase::class.java,"todo_database").build()
                INSTANCE=tempInctanse
                return tempInctanse
            }
        }
    }
}


@Entity(tableName = "todo_tasks")

data class ToDoEntinty(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var date: String,
    var content: String,
)