package com.example.lakshya_coaching.models

data class Question(
    val Id: Int = 0,
    val Title: String = "",
    val Option1: String = "",
    val Option2: String = "",
    val Option3: String = "",
    val Option4: String = "",
    val CorrectOptionNumber: Int = 0,
    val ExamId: Int = 0
)