package com.yawlle.kittymotivation.infra

import android.content.Context
import android.content.SharedPreferences

class SecurityPreferences(context: Context) {

    private val security: SharedPreferences =
        context.getSharedPreferences("Motivation", Context.MODE_PRIVATE)

    fun storeString(key: String, str: String) {
        security.edit().putString(key, str).apply()
    }

    fun getString(key: String) : String {
        return security.getString(key, "") ?: ""
        // ?: se o primeiro for nulo, a outra será usada
        //chave e um valor caso o conteúdo de key não exista


    }
}
