package com.example.lakshya_coaching.services

import com.example.lakshya_coaching.models.Exam
import com.example.lakshya_coaching.utils.ApiException
import com.example.lakshya_coaching.utils.ApiRequest
import com.google.gson.Gson
import java.net.HttpURLConnection

class ExamsService
{
    fun getAll(): Array<Exam>
    {
        val response = ApiRequest.get(ApiRequest.EXAMS_URL)
        if (response.code == HttpURLConnection.HTTP_OK)
            return Gson().fromJson(response.message, Array<Exam>::class.java)
        else
            throw ApiException(response.code, response.message)
    }

    fun getOne(Id: Int): Exam
    {
        val response = ApiRequest.get("${ApiRequest.EXAMS_URL}?Id=$Id")
        if (response.code == HttpURLConnection.HTTP_OK)
            return Gson().fromJson(response.message, Exam::class.java)
        else
            throw ApiException(response.code, response.message)
    }

    fun getForSubject(subjectId: Int): Array<Exam>
    {
        val response = ApiRequest.get("${ApiRequest.EXAMS_URL}?subjectId=$subjectId")
        if (response.code == HttpURLConnection.HTTP_OK)
            return Gson().fromJson(response.message, Array<Exam>::class.java)
        else
            throw ApiException(response.code, response.message)
    }
}