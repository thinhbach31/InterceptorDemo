package com.example.intercriptordemo.api

import okhttp3.OkHttpClient
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit

class RestClient {

    companion object{
        var mRestService: APIService? = null

        fun getClient():APIService {
            if (mRestService == null){
                val client = OkHttpClient()
                val fakeInterceptor = FakeInterceptor()
                client.interceptors().add(fakeInterceptor)
                val retrofit = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("http://www.vavian.com/")
                    .client(client)
                    .build()
                mRestService = retrofit.create(APIService::class.java)
            }
            return mRestService!!
        }
    }

}