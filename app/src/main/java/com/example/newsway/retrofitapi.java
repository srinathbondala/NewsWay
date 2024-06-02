package com.example.newsway;
import java.net.URL;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface retrofitapi {
    @GET
    Call<newsdata> getALlNews(@Url String url);
    @GET
    Call<newsdata> getNewsByCategory(@Url String url);
    @GET
    Call<newsdata> getSearchNews(@Url String url);
}
