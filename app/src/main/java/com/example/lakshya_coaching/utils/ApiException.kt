package com.example.lakshya_coaching.utils

class ApiException(
    val status: Int = 0,
    override val message: String = ""
) : Exception()