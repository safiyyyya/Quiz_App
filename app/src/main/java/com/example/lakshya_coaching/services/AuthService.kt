package com.example.lakshya_coaching.services

import com.example.lakshya_coaching.models.Student
import com.example.lakshya_coaching.utils.ApiRequest
import com.example.lakshya_coaching.utils.ApiResponse
import com.google.gson.Gson

class AuthService
{
    fun login(students : Student): ApiResponse
    {
        return ApiRequest.post(ApiRequest.LOGIN_URL, Gson().toJson(students))
    }
}