package com.example.lakshya_coaching

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lakshya_coaching.adapters.ExamsAdapter
import com.example.lakshya_coaching.models.Exam
import com.example.lakshya_coaching.models.Subject
import com.example.lakshya_coaching.services.ExamsService
import com.example.lakshya_coaching.utils.ApiException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ExamFragment : Fragment(), AdapterView.OnItemSelectedListener, ExamsAdapter.OnClickListener
{
    private val subjects = arrayOf(
        Subject(Id = 1, Name = "Maths", Image = R.drawable.maths),
        Subject(Id = 2, Name = "Reasoning", Image = R.drawable.reasoning),
        Subject(Id = 3, Name = "English", Image = R.drawable.english),
        Subject(Id = 4, Name = "Entrepreneurship", Image = R.drawable.ent),
        Subject(Id = 5, Name = "General Knowledge", Image = R.drawable.knowledge),
        Subject(Id = 6, Name = "Data Interpretation", Image = R.drawable.di),
    )

    private lateinit var spnSubjects: Spinner
    private lateinit var examList: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        return inflater.inflate(R.layout.fragment_exam, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        examList = view.findViewById(R.id.examList)
        examList.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)

        spnSubjects = view.findViewById(R.id.spnSubjects)
        spnSubjects.adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, subjects.map { subject -> subject.Name })
        spnSubjects.onItemSelectedListener = this

        spnSubjects.setSelection(0)
    }

    // Executed when subject selection changes
    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long)
    {
        CoroutineScope(Dispatchers.IO).launch {
            val selectedSubject = subjects[position]
            val examsService = ExamsService()

            try
            {
                val exams = examsService.getForSubject(selectedSubject.Id)

                withContext(Dispatchers.Main) {
                    examList.adapter = ExamsAdapter(exams,selectedSubject.Image, this@ExamFragment)
                }
            }
            catch (ex: ApiException)
            {
                Log.e("Api", ex.stackTrace.toString())
            }
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) { }

    override fun onExamClick(exam: Exam)
    {
        val prefs = requireActivity().getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
        prefs.edit().apply {
            putInt("examId", exam.Id)
            apply()
        }

        requireContext().startActivity(Intent(requireActivity(), QuestionsActivity::class.java))
    }
}