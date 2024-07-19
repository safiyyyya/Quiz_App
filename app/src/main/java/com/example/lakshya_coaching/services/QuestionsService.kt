package com.example.lakshya_coaching.services

import com.example.lakshya_coaching.models.Question
import com.example.lakshya_coaching.utils.ApiException
import com.example.lakshya_coaching.utils.ApiRequest
import com.google.gson.Gson
import java.net.HttpURLConnection

class QuestionsService
{
    fun getForExam(examId: Int): Array<Question>
    {
        val response = ApiRequest.get("${ApiRequest.QUESTIONS_URL}?examId=$examId")
        if (response.code == HttpURLConnection.HTTP_OK)
            return Gson().fromJson(response.message, Array<Question>::class.java)
        else
            throw ApiException(response.code, response.message)
    }
}