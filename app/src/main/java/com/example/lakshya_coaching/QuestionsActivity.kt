package com.example.lakshya_coaching

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.lakshya_coaching.models.Question
import com.example.lakshya_coaching.models.SelectedAnswer
import com.example.lakshya_coaching.services.QuestionsService
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class QuestionsActivity : AppCompatActivity()
{
    private var examId: Int = 0
    private var currentQuestionIndex = -1

    private lateinit var questions: Array<Question>
    private var selectedAnswers: ArrayList<SelectedAnswer> = arrayListOf()

    private lateinit var questionsService: QuestionsService

    private lateinit var progressBar: ProgressBar
    private lateinit var txtTitle: TextView

    private lateinit var radioGroup: RadioGroup
    private lateinit var radOption1: RadioButton
    private lateinit var radOption2: RadioButton
    private lateinit var radOption3: RadioButton
    private lateinit var radOption4: RadioButton

    private lateinit var btnPrevious: Button
    private lateinit var btnNext: Button
    private lateinit var btnSubmit: Button

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_questions)

        progressBar = findViewById(R.id.progressBar)

        txtTitle = findViewById(R.id.txtTitle)

        radioGroup = findViewById(R.id.radOptions)
        radOption1 = findViewById(R.id.radOption1)
        radOption2 = findViewById(R.id.radOption2)
        radOption3 = findViewById(R.id.radOption3)
        radOption4 = findViewById(R.id.radOption4)

        btnPrevious = findViewById(R.id.btnPrevious)
        btnNext = findViewById(R.id.btnNext)
        btnSubmit = findViewById(R.id.btnSubmit)

        radOption1.setOnClickListener { answerSelected(R.id.radOption1) }
        radOption2.setOnClickListener { answerSelected(R.id.radOption2) }
        radOption3.setOnClickListener { answerSelected(R.id.radOption3) }
        radOption4.setOnClickListener { answerSelected(R.id.radOption4) }

        // radioGroup.setOnCheckedChangeListener { _, checkedId -> answerSelected(checkedId) }

        btnPrevious.setOnClickListener { showPreviousQuestion() }
        btnNext.setOnClickListener { showNextQuestion() }
        btnSubmit.setOnClickListener { submitAnswers() }

        val prefs = getSharedPreferences("my_prefs", MODE_PRIVATE)
        examId = prefs.getInt("examId", 0)

        if (examId == 0)
        {
            Toast.makeText(this, "Invalid exam", Toast.LENGTH_SHORT).show()
            finish()
        }

        questionsService = QuestionsService()
        CoroutineScope(Dispatchers.IO).launch {
            questions = questionsService.getForExam(examId)

            withContext(Dispatchers.Main) {
                progressBar.isEnabled = true
                if (questions.isEmpty())
                {
                    Toast.makeText(this@QuestionsActivity, "No questions in this exam!", Toast.LENGTH_SHORT).show()
                    finish()

                    return@withContext
                }

                showNextQuestion()
            }
        }
    }

    private fun showQuestion(index: Int)
    {
        progressBar.isEnabled = false
        txtTitle.text = questions[index].Title
        radOption1.text = questions[index].Option1
        radOption2.text = questions[index].Option2
        radOption3.text = questions[index].Option3
        radOption4.text = questions[index].Option4
    }

    private fun answerSelected(checkedId: Int)
    {
        val questionId = questions[currentQuestionIndex].Id

        val selectedOptionNumber = when (checkedId)
        {
            R.id.radOption1 -> 1
            R.id.radOption2 -> 2
            R.id.radOption3 -> 3
            R.id.radOption4 -> 4
            else -> 0
        }

        val selectedAnswer = selectedAnswers.find { sa -> sa.questionId == questionId }
        if (selectedAnswer == null)
            selectedAnswers.add(SelectedAnswer(questionId, selectedOptionNumber))
        else
            selectedAnswer.selectedOptionNumber = selectedOptionNumber
    }

    private fun showNextQuestion()
    {
        showQuestion(++currentQuestionIndex)
        updateButtonStates()
        selectOptionForCurrentQuestion()
    }

    private fun showPreviousQuestion()
    {
        showQuestion(--currentQuestionIndex)
        updateButtonStates()
        selectOptionForCurrentQuestion()
    }

    private fun selectOptionForCurrentQuestion()
    {
        val currentQuestion = questions[currentQuestionIndex]
        val selectedAnswer = selectedAnswers.find { sa -> sa.questionId == currentQuestion.Id }

        if (selectedAnswer == null)
        {
            radioGroup.clearCheck()
            return
        }

        when (selectedAnswer.selectedOptionNumber)
        {
            1 -> radOption1.isChecked = true
            2 -> radOption2.isChecked = true
            3 -> radOption3.isChecked = true
            4 -> radOption4.isChecked = true
        }
    }

    private fun submitAnswers()
    {
        var marks = 0
        for (question in questions)
        {
            val selectedAnswer = selectedAnswers.find { sa -> sa.questionId == question.Id } ?: continue
            if (selectedAnswer.selectedOptionNumber == question.CorrectOptionNumber)
                marks++
        }

//        val sharedPref = getSharedPreferences("exam_marks$examId", MODE_PRIVATE)
        val sharedPref = getSharedPreferences("exam_marks", MODE_PRIVATE)
        val editor = sharedPref.edit()

        editor.putInt("marks", marks)
        editor.apply()
        startActivity(Intent(this,MainActivity::class.java))

    }

    private fun updateButtonStates()
    {
        if (currentQuestionIndex == 0)
        {
            btnPrevious.isEnabled = false
            btnNext.isEnabled = true
            btnSubmit.isEnabled = true
        }
        else if (currentQuestionIndex >= questions.size - 1)
        {
            btnPrevious.isEnabled = true
            btnNext.isEnabled = false
            btnSubmit.isVisible = true
            btnSubmit.isEnabled = true
        }
        else
        {
            btnPrevious.isEnabled = true
            btnNext.isEnabled = true
        }
    }
}