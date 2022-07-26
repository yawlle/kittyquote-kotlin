package com.yawlle.kittymotivation.infra

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface RetrofitInitializerAnime {
    @GET("random")
    fun getPhraseAnime() : Call<PhraseAnime>

    companion object {
        var url = "https://animechan.vercel.app/api/"

        fun create() : RetrofitInitializerAnime {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(url)
                .build()
            return retrofit.create(RetrofitInitializerAnime::class.java)
        }

    }


}