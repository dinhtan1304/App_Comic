package com.example.comicapp.API;

import com.example.comicapp.object.Account;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface AccountApiEndPoint {
    @GET("api/account")
    Call<Account> getAccountById(@Path("id") int id);

    @GET("api/account")
    Call<ArrayList<Account>> getAllAccount();

    @POST("api/account")
    Call<Account> createNewPhoto(@Body Account newAccount);
}
