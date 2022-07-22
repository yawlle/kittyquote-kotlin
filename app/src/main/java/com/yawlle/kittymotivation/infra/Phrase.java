package com.yawlle.kittymotivation.infra;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Phrase {
    @SerializedName("en")
    @Expose
    public String en;

    public String getPhrase() {
        return en;
    }

    public void setPhrase() {
        this.en = en;
    }

    @Override
    public String toString() {
        return "Data{" +
                ", title='" + en + '\'' +
                '}';
    }
}