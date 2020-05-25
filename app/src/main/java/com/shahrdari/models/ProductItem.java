package com.shahrdari.models;

import android.text.TextUtils;

import com.google.gson.annotations.SerializedName;

public class ProductItem {

    @SerializedName("mahdetail")
    private String mahdetail;

    @SerializedName("mahid")
    private int mahid;

    @SerializedName("mahcod")
    private long mahcod;

    @SerializedName("mahname")
    private String mahname;

    @SerializedName("mahnoe")
    private Object mahnoe;

    @SerializedName("mahvahed")
    private String mahvahed;

    @SerializedName("mahsize")
    private String mahsize;

    @SerializedName("mahfi")
    private int mahfi;

    @SerializedName("mahdarajeh")
    private String mahdarajeh;

    @SerializedName("mahtaqirat")
    private Object mahtaqirat;

    @SerializedName("mahpic")
    private String mahpic;

    public String getMahdetail() {
        return TextUtils.isEmpty(mahdetail) ? "" : mahdetail;
    }

    public int getMahid() {
        return mahid;
    }

    public long getMahcod() {
        return mahcod;
    }

    public String getMahname() {
        return mahname;
    }

    public Object getMahnoe() {
        return mahnoe;
    }

    public String getMahvahed() {
        return mahvahed;
    }

    public String getMahsize() {
        return TextUtils.isEmpty(mahsize) ? "" : ("سایز " + mahsize + " - ");
    }

    public int getMahfi() {
        return mahfi;
    }

    public String getMahdarajeh() {
        return TextUtils.isEmpty(mahdarajeh) ? "" : ("درجه " + mahdarajeh);
    }

    public Object getMahtaqirat() {
        return mahtaqirat;
    }

    public String getMahpic() {
        return mahpic;
    }
}