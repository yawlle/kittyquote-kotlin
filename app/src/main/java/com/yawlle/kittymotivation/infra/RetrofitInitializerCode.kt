package com.yawlle.kittymotivation.infra

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface RetrofitInitializerCode {
    @GET("quote")
    fun getPhraseDune() : Call<PhraseCode>

    companion object {
        var url = "https://nodejs-quoteapp.herokuapp.com/"

        fun create() : RetrofitInitializerCode {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(url)
                .build()
            return retrofit.create(RetrofitInitializerCode::class.java)
        }

    }


}