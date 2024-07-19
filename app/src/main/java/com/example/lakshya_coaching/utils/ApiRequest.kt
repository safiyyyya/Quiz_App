package com.example.lakshya_coaching.utils

import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody

class ApiRequest
{
    companion object
    {
        @JvmStatic val BASE_URL = "http://172.28.0.1/lakshyaapi"
        @JvmStatic val LOGIN_URL = "$BASE_URL/login.php"
        @JvmStatic val REGISTER_URL = "$BASE_URL/register.php"
        @JvmStatic val QUESTIONS_URL = "$BASE_URL/questions.php"
        @JvmStatic val EXAMS_URL = "$BASE_URL/exams.php"
        @JvmStatic val ANSWERS_URL = "$BASE_URL/answers.php"

        @JvmStatic
        fun get(url: String): ApiResponse
        {
            return send(Request.Builder().url(url).build())
        }

        @JvmStatic
        fun post(url: String, body: String): ApiResponse
        {
            return send(Request.Builder().url(url).post(body.toRequestBody()).build())
        }

        @JvmStatic
        fun put(url: String, body: String): ApiResponse
        {
            return send(Request.Builder().url(url).put(body.toRequestBody()).build())
        }

        @JvmStatic
        fun delete(url: String): ApiResponse
        {
            return send(Request.Builder().url(url).delete().build())
        }

        @JvmStatic
        private fun send(request: Request): ApiResponse {
            val client = OkHttpClient()
            val response = client.newCall(request).execute()

            return ApiResponse(
                code = response.code,
                message = response.body!!.string()
            )
        }
    }
}