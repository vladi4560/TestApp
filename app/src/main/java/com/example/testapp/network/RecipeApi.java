package com.example.testapp.network;

import com.example.testapp.model.Recipe;
import com.example.testapp.model.RecipeList;

import java.util.List;
import java.util.Map;

import retrofit2.Call;

import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface RecipeApi {
    public static final String BASE_URL = "https://api.spoonacular.com/";

    @GET("recipes/random?apiKey=3a34ee6b52ee429c898356acf9e5f7b2&number=1")
    Call<RecipeList> getPosts();

    @GET("recipes/random?")
    Call<RecipeList> getPosts(@Query("apiKey") String apiKey,@Query("number") String number);


}
