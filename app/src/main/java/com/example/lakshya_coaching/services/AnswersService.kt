package com.example.lakshya_coaching.services

import com.example.lakshya_coaching.models.SelectedAnswer
import com.example.lakshya_coaching.utils.ApiRequest
import com.google.gson.Gson

class AnswersService
{
    fun add(studentId: Int, examId: Int, selectedAnswers: ArrayList<SelectedAnswer>)
    {
        val json = Gson().toJson(selectedAnswers)
        val response = ApiRequest.post("${ApiRequest.ANSWERS_URL}?examId=$examId&studentId=$studentId", json)
    }
}