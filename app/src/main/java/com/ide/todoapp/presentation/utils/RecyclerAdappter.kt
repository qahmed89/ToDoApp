package com.ide.todoapp.presentation.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ide.todoapp.R
import com.ide.todoapp.data.datasource.local.ToDoEntinty

class RecyclerAdappter : RecyclerView.Adapter<RecyclerAdappter.ItemHolder>() {
private lateinit var onRecyclerClickListener: OnRecyclerClickListener
private var taskList= emptyList<ToDoEntinty>()



    class ItemHolder(itemView: View,listener: OnRecyclerClickListener) : RecyclerView.ViewHolder(itemView) {
        val content: TextView = itemView.findViewById(R.id.mainTitle_tv)
        val date: TextView = itemView.findViewById(R.id.mainDate_tv)
        val deleteIcon :ImageView=itemView.findViewById(R.id.delete_iv)

        init {
        itemView.setOnClickListener {
            listener.onItemClick(adapterPosition)
        }
            deleteIcon.setOnClickListener { listener.onImageClick(adapterPosition,deleteIcon) }
        }
    }


    fun setOnItemClickListener(listener: OnRecyclerClickListener){
        onRecyclerClickListener=listener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.recycler_item,
                parent,
                false
            ),onRecyclerClickListener
        )
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        val curruntItem=taskList[position]
        holder.content.text=curruntItem.content
        holder.date.text=curruntItem.date
    }

    override fun getItemCount(): Int {
        return taskList.size
    }
    fun setData(toDoEntinty: List<ToDoEntinty>){
        this.taskList=toDoEntinty
        notifyDataSetChanged()
    }
    fun getData(position: Int):ToDoEntinty{
        var toDoEntinty:ToDoEntinty=taskList[position]
        return toDoEntinty
    }
}
interface OnRecyclerClickListener{
    fun onItemClick(position: Int)
    fun onImageClick(position: Int,imageView: ImageView)
}