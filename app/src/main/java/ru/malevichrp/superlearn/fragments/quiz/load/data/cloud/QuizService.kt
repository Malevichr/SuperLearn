package ru.malevichrp.superlearn.fragments.quiz.load.data.cloud

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface QuizService {
    @GET("api.php")
    fun questionAndChoices(
        @Query("amount") amount: Int,
        @Query("type") type: String = "multiple"
    ): Call<QuizResponse>
}

