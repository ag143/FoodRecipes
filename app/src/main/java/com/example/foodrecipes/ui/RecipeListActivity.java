package com.example.foodrecipes.ui;

import android.os.Bundle;
import android.util.Log;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.foodrecipes.R;
import com.example.foodrecipes.models.Recipe;
import com.example.foodrecipes.requests.RecipeApi;
import com.example.foodrecipes.requests.ServiceGenerator;
import com.example.foodrecipes.requests.responses.RecipeResponse;
import com.example.foodrecipes.util.Constants;
import com.example.foodrecipes.viewmodels.RecipeListViewModel;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecipeListActivity extends BaseActivity {

    private static final String TAG = "RecipeListActivity";

    private RecipeListViewModel mRecipeListViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_list);

        mRecipeListViewModel = new ViewModelProvider(this).get(RecipeListViewModel.class);
        subscribeObservers();
    }

    private void subscribeObservers() {
        mRecipeListViewModel.getRecipes().observe(this, new Observer<List<Recipe>>() {
            @Override
            public void onChanged(List<Recipe> recipes) {

            }
        });
    }

//    private void testRetrofitRequest() {
////        RecipeApi recipeApi = ServiceGenerator.getRecipeApi();
////
////        Call<RecipeSearchResponse> responseCall = recipeApi
////                .searchRecipe(
////                        Constants.API_KEY,
////                        "chicken",
////                        "1"
////                );
////        responseCall.enqueue(new Callback<RecipeSearchResponse>() {
////            @Override
////            public void onResponse(Call<RecipeSearchResponse> call, Response<RecipeSearchResponse> response) {
////                Log.d(TAG, "onResponse: server response " + response.toString());
////                if (response.code() ==  200) {
////                    Log.d(TAG, "onResponse: " + response.body().toString());
////                    List<Recipe> recipes = new ArrayList<>(response.body().getRecipes());
////                    for (Recipe recipe: recipes) {
////                        Log.d(TAG, "onResponse: " + recipe.getTitle());
////                    }
////
////                } else {
////                    try {
////                        Log.d(TAG, "onResponse: " + response.errorBody().string());
////                    } catch (IOException e){
////                        e.printStackTrace();
////                    }
////                }
////            }
////
////            @Override
////            public void onFailure(Call<RecipeSearchResponse> call, Throwable t) {
////
////            }
////        });
////    }

    private void testRetrofitRequest() {
        RecipeApi recipeApi = ServiceGenerator.getRecipeApi();

        Call<RecipeResponse> responseCall = recipeApi
                .getRecipe(
                        Constants.API_KEY,
                        "807602"
                );
        responseCall.enqueue(new Callback<RecipeResponse>() {
            @Override
            public void onResponse(Call<RecipeResponse> call, Response<RecipeResponse> response) {
                Log.d(TAG, "onResponse: server response " + response.toString());
                if (response.code() == 200) {
                    Log.d(TAG, "onResponse: " + response.body().toString());
                    Recipe recipe = response.body().getRecipe();
                    Log.d(TAG, "onResponse: RETRIEVE RECIPE: " + recipe.toString());

                } else {
                    try {
                        Log.d(TAG, "onResponse: " + response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<RecipeResponse> call, Throwable t) {

            }
        });
    }

}



























