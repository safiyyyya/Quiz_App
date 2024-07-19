package com.example.lakshya_coaching

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lakshya_coaching.adapters.QuestionsAdapter

class ResultFragment : Fragment()
{

    private lateinit var txtMark: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?
    {

        return inflater.inflate(R.layout.fragment_result, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)

        val sharedPreferences = requireActivity().getSharedPreferences("exam_marks", AppCompatActivity.MODE_PRIVATE)
        val marks = sharedPreferences.getInt("marks", 0)

        txtMark = view.findViewById(R.id.txtMarks)
        txtMark.text = marks.toString()

    }
}