package com.example.comicapp.API;

import com.example.comicapp.object.Chapter;
import com.example.comicapp.object.Comic;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ComicApiEndPoint {
    @GET("api/comic")
    Call<ArrayList<Comic>> getAllComics();

}
