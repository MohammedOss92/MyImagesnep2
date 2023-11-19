package com.sarrawi.myimagesni

import android.content.Context
import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class Imgs_ViewModel(private val context: Context,  private val imgsRepo:ImgRepository ): ViewModel() {

    private val retrofitServices = ApiService.provideRetrofitInstance()



    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading





//    val imageModelResponse = MutableLiveData<ImgsRespone2>()
//
//    fun fetchData() {
//        imgsRepo.getSnippets().enqueue(object : Callback<ImgsRespone2> {
//            override fun onResponse(call: Call<ImgsRespone2>, response: Response<ImgsRespone2>) {
//                if (response.isSuccessful) {
//                    imageModelResponse.value = response.body()
//                } else {
//                    // معالجة الحالة غير الناجحة هنا
//                }
//            }
//
//            override fun onFailure(call: Call<ImgsRespone2>, t: Throwable) {
//                // معالجة الأخطاء هنا
//            }
//        })
//    }

    val imageModelResponse: LiveData<ImgsRespone2?>
        get() = _imageModelResponse

    private val _imageModelResponse = MutableLiveData<ImgsRespone2?>()

    fun fetchData() {
        viewModelScope.launch {
            try {
                _imageModelResponse.value = imgsRepo.getSnippets()
            } catch (e: Exception) {
                // معالجة الأخطاء هنا
            }
        }
    }



}