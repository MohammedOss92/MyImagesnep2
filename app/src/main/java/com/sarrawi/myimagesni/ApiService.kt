package com.sarrawi.myimagesni


import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Call

interface ApiService {




    @GET("snippets") // قم بتعيين نهاية الطريق الخاصة بك
//    fun getSnipp(): Call<ImgsRespone2>
    suspend fun getSnipp(): ImgsRespone2?



    companion object {

        private var retrofitService: ApiService? = null

        fun provideRetrofitInstance(): ApiService {
            if (retrofitService == null) {
                val interceptor = HttpLoggingInterceptor()
                interceptor.level = Level.BODY

                val okHttpClient = OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    // أي إعدادات إضافية لـ OkHttpClient يمكنك إضافتها هنا
                    .build()
//
                val retrofit = Retrofit.Builder()
                    .baseUrl("http://www.sarrawi.bio/")
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

                retrofitService = retrofit.create(ApiService::class.java)
            }
            return retrofitService!!


        }
    }
}