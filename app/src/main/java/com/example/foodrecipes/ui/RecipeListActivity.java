package com.example.foodrecipes.ui;

import android.os.Bundle;
import android.view.View;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.foodrecipes.R;
import com.example.foodrecipes.models.Recipe;

import com.example.foodrecipes.util.Testing;
import com.example.foodrecipes.viewmodels.RecipeListViewModel;

import java.util.List;

public class RecipeListActivity extends BaseActivity {

    private static final String TAG = "RecipeListActivity";

    private RecipeListViewModel mRecipeListViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_list);

        mRecipeListViewModel = new ViewModelProvider(this).get(RecipeListViewModel.class);
        subscribeObservers();

        findViewById(R.id.test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                testRetrofitRequest();
            }
        });
    }

    private void subscribeObservers() {
        mRecipeListViewModel.getRecipes().observe(this, new Observer<List<Recipe>>() {
            @Override
            public void onChanged(List<Recipe> recipes) {
                if (recipes != null) {
                    Testing.printRecipes(recipes, "recipes test");
                }
            }
        });
    }

    private void testRetrofitRequest() {
        mRecipeListViewModel.searchRecipesApi("chicken", 1);
    }
    /*private void testRetrofitRequest() {
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
    }*/

}



























