package com.example.jokeapp.Fragment

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jokeapp.Options
import com.example.jokeapp.QuestionId
import com.example.jokeapp.RetrofitInstance
import kotlinx.coroutines.launch
import timber.log.Timber

class MainViewModel : ViewModel() {
    private val _response = MutableLiveData<QuestionId?>()
    val response: LiveData<QuestionId?> = _response



    fun fetchJoke(context: Context, token: String, gradeId: String, batchId: String, quizId: String) {
        viewModelScope.launch {
            try {
                val apiService = RetrofitInstance.getRetrofitInstance()
                val response = apiService.getData("5e9db235f722de4b06ac7274", "65ab9848601f97e50b0946a3", "65eacd79848cd1523998488a")
                if (response.isSuccessful) {
                    response.body()?.let { responseBody ->
                        responseBody.datas.questions.forEach { question ->
                            _response.value = question.questionId
                        }
                    }
                } else {
                    Timber.tag("MainViewModel").e("Response unsuccessful: " + response.code())
                }
            } catch (e: Exception) {
                Timber.tag("MainViewModel").e("Exception: " + e.message)
            }
        }
    }
}

/*
class MainViewModel : ViewModel() {

    private val _response = MutableLiveData<QuestionId?>()

    val response: MutableLiveData<QuestionId?> = _response

    fun fetchJoke(context: Context, token: String, gradeId: String, batchId: String, quizId: String) {
        viewModelScope.launch {
            try {
                val apiService = RetrofitInstance.getRetrofitInstance()
                val response = apiService.getData("5e9db235f722de4b06ac7274", "65ab9848601f97e50b0946a3", "65eacd79848cd1523998488a")

                if (response.isSuccessful) {
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
*/

/*

fun main() {

    test()
}

fun test(){
    val string: ArrayList<String> = ArrayList()
    string.add("cat")
    string.add("dog")
    string.add("monkey")
    string.add("elephant")
    string.add("yguguygg")
    string.add("i")
    string.add("hi")
    string.add("hb")
    string.add("h")
    string.add("hanki")
//    val string2 = string.filter { it != "dog" }.toTypedArray()

//    println(string2.joinToString())

    println(string.filter { it.length > 4 })

//      string.remove("yguguygg")

//    val sizee = string.size

//    val count = string.contains("h")

//    val chila = string.indexOf("h")

//    string.reverse()   z to A

//    string.sort()   A to z

//    println(string)

}*/
