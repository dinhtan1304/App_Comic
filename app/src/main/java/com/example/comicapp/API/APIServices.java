package com.example.comicapp.API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIServices {
    public static final String BASE_URL = "https://comicapp-api.onrender.com/"; //"https://64adcbcab470006a5ec669f4.mockapi.io/";
    public static final String URL_CHAP = "https://64aec4f8c85640541d4dab86.mockapi.io/";

    private Retrofit retrofit;
    private ComicApiEndPoint comicApiEndPoint;
    private ChapterApiEndPoint chapterApiEndPoint;
    private AccountApiEndPoint accountApiEndPoint;
    private ReadComicApiEndPoint readComicApiEndPoint;

    public APIServices() {
        retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        comicApiEndPoint = retrofit.create(ComicApiEndPoint.class);

        retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        chapterApiEndPoint = retrofit.create(ChapterApiEndPoint.class);

        retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        readComicApiEndPoint = retrofit.create(ReadComicApiEndPoint.class);

        retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        accountApiEndPoint = retrofit.create(AccountApiEndPoint.class);

    }

    private static APIServices instance = null;
    public static APIServices getInstance() {
        if (instance == null) {
            instance = new APIServices();
        }
        return instance;
    }
    //Comic
    public ComicApiEndPoint getComicsApiEndPoint() {
        return comicApiEndPoint;
    }
    public static ComicApiEndPoint getComicApiEndPoint() {
        return getInstance().getComicsApiEndPoint();
    }
    //Chap
    public ChapterApiEndPoint getChaptersApiEndPoint() {
        return chapterApiEndPoint;
    }
    public static ChapterApiEndPoint getChapterApiEndPoint(){
        return getInstance().getChaptersApiEndPoint();
    }
    //Read
    public ReadComicApiEndPoint getReadComicsApiEndPoint(){ return  readComicApiEndPoint; }
    public static ReadComicApiEndPoint getReadComicApiEndPoint(){
        return getInstance().getReadComicsApiEndPoint();
    }
    //Acc
    public AccountApiEndPoint getAccountsApiEndPoint(){
        return accountApiEndPoint;
    }

    public static AccountApiEndPoint getAccountApiServies(){
        return getInstance().getAccountsApiEndPoint();
    }

}
