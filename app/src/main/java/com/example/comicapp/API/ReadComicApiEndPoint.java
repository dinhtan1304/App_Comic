package com.example.comicapp.API;

import com.example.comicapp.object.Chapter;
import com.example.comicapp.object.ReadComic;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ReadComicApiEndPoint {
    @GET("api/chapview")
    Call<ArrayList<ReadComic>> getAllImgById(@Query("ChapterId") String idChap);
}
