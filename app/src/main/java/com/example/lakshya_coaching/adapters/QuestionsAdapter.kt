package com.example.lakshya_coaching.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lakshya_coaching.R

class QuestionsAdapter(private val items: Array<String>) : RecyclerView.Adapter<QuestionsAdapter.ViewHolder>()
{
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
    {
        val imgDP: ImageView = view.findViewById(R.id.imgDP)
        val txtName: TextView = view.findViewById(R.id.txtName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
    {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.result_list_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int)
    {
        holder.imgDP.setImageResource(R.drawable.ic_android)
        holder.txtName.text = items[position]
    }

    override fun getItemCount(): Int = items.size
}