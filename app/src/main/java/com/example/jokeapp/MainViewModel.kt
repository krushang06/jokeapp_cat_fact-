package com.example.jokeapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import timber.log.Timber

class MainViewModel:ViewModel() {

    private val _response = MutableLiveData<responceDataClass2?>()
    val response = _response

    fun fetchJoke() {
        viewModelScope.launch {
            try {
                val retIn = RetrofitInstance.getRetrofitInstance()
                val response = retIn.getData()

                if (response.isSuccessful) {
                    val responseData = response.body()
                    if (responseData != null) {
                        _response.postValue(responseData)
                    } else {
                        Timber.e("Response body is null")
                    }
                } else {
                    Timber.e("Response unsuccessful: ${response.code()}")
                }
            } catch (e: Exception) {
                Timber.e(e, "Error fetching joke")
            }
        }
    }

}