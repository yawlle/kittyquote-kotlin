package com.yawlle.kittymotivation.infra

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface RetrofitInitializer {
    @GET("quotes/random")
    fun getPhrase() : Call<Phrase>

    companion object {
        var url = "https://programming-quotes-api.herokuapp.com/index.html"

        fun create() : RetrofitInitializer {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(url)
                .build()
            return retrofit.create(RetrofitInitializer::class.java)
        }

    }


}