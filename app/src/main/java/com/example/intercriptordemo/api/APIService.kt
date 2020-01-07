package com.example.intercriptordemo.api

import com.example.intercriptordemo.model.Wallet
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface APIService {

    @GET("/")
    fun getWalletById(@Query("id") id: String?): Call<Wallet?>?
}