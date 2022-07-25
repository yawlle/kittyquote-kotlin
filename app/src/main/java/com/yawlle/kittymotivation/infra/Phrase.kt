package com.yawlle.kittymotivation.infra

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Phrase(
    @Expose
    @SerializedName("author")
    val author: String,
    @Expose
    @SerializedName("en")
    val en: String
)
{
    override fun toString(): String {
            return en + "  " + author
        }
    }
