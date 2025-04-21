package ru.malevichrp.superlearn.fragments.quiz.load.data.cloud

import com.google.gson.annotations.SerializedName

class QuizResponse(
    @SerializedName("response_code")
    val responseCode: Int,
    @SerializedName("results")
    val dataList: List<QuestionAndChoicesCloud>
)