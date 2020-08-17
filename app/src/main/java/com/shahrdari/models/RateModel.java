package com.shahrdari.models;

import com.google.gson.annotations.SerializedName;

public class RateModel {

    @SerializedName("nerkh_cod")
    private String nerkhCod;

    @SerializedName("nerkh_e")
    private String nerkhE;

    @SerializedName("nerkh_g_name")
    private String nerkhGName;

    @SerializedName("nerkh_s")
    private String nerkhS;

    @SerializedName("nerkh_da_sabt")
    private String nerkhDaSabt;

    @SerializedName("details")
    private String details;

    public String getNerkhCod() {
        return nerkhCod;
    }

    public String getNerkhE() {
        return nerkhE;
    }

    public String getNerkhGName() {
        return nerkhGName;
    }

    public String getNerkhS() {
        return nerkhS;
    }

    public String getNerkhDaSabt() {
        return nerkhDaSabt;
    }

    public String getDetails() {
        return details;
    }
}