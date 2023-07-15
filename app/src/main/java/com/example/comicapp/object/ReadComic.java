package com.example.comicapp.object;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ReadComic implements Serializable {
    @SerializedName("_id")
    private String IdRead;
    @SerializedName("Image")
    private String UrlImg;
    @SerializedName("ChapterId")
    private String IdChap;

    public ReadComic(String idRead, String urlImg, String idChap) {
        IdRead = idRead;
        UrlImg = urlImg;
        IdChap = idChap;
    }

    public String getIdRead() {
        return IdRead;
    }

    public String getUrlImg() {
        return UrlImg;
    }

    public String getIdChap() {
        return IdChap;
    }
}
