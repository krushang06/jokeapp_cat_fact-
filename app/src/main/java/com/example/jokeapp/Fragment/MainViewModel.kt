package com.example.jokeapp.Fragment

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jokeapp.Datas
import com.example.jokeapp.QuestionId
import com.example.jokeapp.Questions
import com.example.jokeapp.RetrofitInstance
import com.example.jokeapp.responceDataClass
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _response = MutableLiveData<QuestionId?>()

    val response: MutableLiveData<QuestionId?> = _response

    fun fetchJoke(context: Context, token: String, gradeId: String, batchId: String, quizId: String) {
        viewModelScope.launch {
            try {
                val apiService = RetrofitInstance.getRetrofitInstance()
                val response = apiService.getData("5e9db235f722de4b06ac7274", "65ab9848601f97e50b0946a3", "65eacd79848cd1523998488a")

                if (response.isSuccessful) {
//                    response.body()?.let { responseBody ->
//                        _response.value = responseBody.datas.questions[0].questionId
//                    }
                    response.body()?.let { responseBody ->
                        responseBody.datas.questions.forEach { question ->
                            _response.value = question.questionId
                            // Handle the current question here
                        }
                    }


                } else {
                    Log.e("MainViewModel", "Response unsuccessful: ${response.code()}")
                }
            } catch (e: Exception) {
                Log.e("MainViewModel", "Exception: ${e.message}")
            }
        }
    }
}
