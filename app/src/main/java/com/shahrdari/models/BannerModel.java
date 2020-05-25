package com.shahrdari.models;

import com.google.gson.annotations.SerializedName;

public class BannerModel {

    @SerializedName("mahfi")
    private int mahfi;

    @SerializedName("mahpic")
    private String mahpic;

    public int getMahfi() {
        return mahfi;
    }

    public String getMahpic() {
        return mahpic;
    }
}