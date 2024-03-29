package com.yawlle.kittymotivation.infra

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface RetrofitInitializerProgramming {
    @GET("Quotes/random")
    fun getPhrase() : Call<Phrase>

    companion object {
        var url = "https://programming-quotes-api.herokuapp.com/"

        fun create() : RetrofitInitializerProgramming {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(url)
                .build()
            return retrofit.create(RetrofitInitializerProgramming::class.java)
        }

    }


}