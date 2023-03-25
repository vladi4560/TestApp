package com.example.testapp.network;

import android.content.Context;
import android.util.Log;

import com.example.testapp.model.Recipe;
import com.example.testapp.model.RecipeList;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RecipeHelper {

    private CallBack_Recipes callBack_recipes;

    static RecipeApi recipeApi = null;

    private Context context;

    public void setContext(Context context){
        this.context= context;
    }
    public static RecipeApi getRecipeApiAPi(){
        if(recipeApi==null){
            Retrofit retrofit= new Retrofit.Builder()
                    .baseUrl(RecipeApi.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            recipeApi = retrofit.create(RecipeApi.class);
        }
        return recipeApi;
    }

    public void fetchAllRecipes(String apiKey, CallBack_Recipes callBack_Recipes) {
        this.callBack_recipes = callBack_Recipes;
        RecipeApi recipeApi1 = getRecipeApiAPi();
        Call<RecipeList> call = recipeApi1.getPosts(apiKey,"5");
        ///Call<RecipeList> call = recipeApi1.getPosts();
        //Log.d("pttt",);
        call.enqueue(new Callback<RecipeList>() {
            @Override
            public void onResponse(Call<RecipeList> call, Response<RecipeList> response) {
                Log.d("pttt",response.code()+"");
                if (response.isSuccessful()) {
                RecipeList recipes = response.body();
                Log.d("pttt","works "+recipes.toString());
//                if (callBack_recipes != null) {
//                    callBack_recipes.movies(movies);
//                }
            } else {
                System.out.println(response.errorBody());
                    Log.d("pttt","fail "+response.errorBody().toString());
            }
            }

            @Override
            public void onFailure(Call<RecipeList> call, Throwable t) {
                t.printStackTrace();
                Log.d("pttt","error");
            }
        });
    }

    public interface CallBack_Recipes {
        void recipes(List<Recipe> movies);
    }

//    private static Interceptor newInterceptor() {
//
//        return new Interceptor() {
//            @Override
//            public okhttp3.Response intercept(Chain chain) throws IOException {
//                Request original =chain.request();
//                HttpUrl httpUrl = original.url().newBuilder().addQueryParameter("api_key","3930eda0423c873bc5ce559094909f9d").build();
//                original = original.newBuilder().url(httpUrl).build();
//                return chain.proceed(original);
//            }
//        };
//
//    }


}
