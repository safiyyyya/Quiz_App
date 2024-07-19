package com.example.lakshya_coaching.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import com.example.lakshya_coaching.R
import com.example.lakshya_coaching.models.Exam

class ExamsAdapter(
    private val exams: Array<Exam>,
    private val imageResourceId: Int,
    private val clickListener: OnClickListener
) : RecyclerView.Adapter<ExamsAdapter.ViewHolder>()
{
    interface OnClickListener
    {
        fun onExamClick(exam: Exam)
    }

    inner class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view)
    {
        private val imgDP: ImageView = view.findViewById(R.id.imgDP)
        private val txtName: TextView = view.findViewById(R.id.txtName)

        fun bind(position: Int)
        {
            imgDP.setImageResource(imageResourceId)
            txtName.text = exams[position].Name

            view.setOnClickListener { clickListener.onExamClick(exams[position]) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
    {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.exam_list_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int)
    {
        holder.bind(position)
    }

    override fun getItemCount(): Int = exams.size
}