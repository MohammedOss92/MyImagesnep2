package com.sarrawi.myimagesni

import android.app.Application
import retrofit2.Call

import retrofit2.Response


class ImgRepository(val apiService: ApiService,app:Application) {



//    fun getSnippets(): Call<ImgsRespone2> {
    suspend fun getSnippets(): ImgsRespone2? {
        return apiService.getSnipp()
}







}

