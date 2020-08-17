package com.shahrdari.models;

import com.google.gson.annotations.SerializedName;

public class FestivalModel {

    @SerializedName("titr")
    private String titr;

    @SerializedName("gmap")
    private String gmap;

    @SerializedName("date_s_e")
    private String date_s_e;

    @SerializedName("name")
    private String name;

    @SerializedName("pic")
    private String pic;

    @SerializedName("adres")
    private String adres;

    @SerializedName("mas")
    private String mas;

    @SerializedName("qorfe")
    private String qorfe;

    public String getTitr() {
        return titr;
    }

    public String getGmap() {
        return gmap;
    }

    public String getDate_S_E() {
        return date_s_e;
    }

    public String getName() {
        return name;
    }

    public String getPic() {
        return pic;
    }

    public String getAdr() {
        return adres;
    }

    public String getMas() {
        return mas;
    }

    public String getQorfe() {
        return qorfe;
    }
}