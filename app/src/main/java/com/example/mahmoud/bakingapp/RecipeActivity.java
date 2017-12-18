package com.example.mahmoud.bakingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.mahmoud.bakingapp.Adapter.RecipeAdapter;
import com.example.mahmoud.bakingapp.Model.BakingDetail;

public class RecipeActivity extends AppCompatActivity {


    RecyclerView mRecipeRecyclerView;
    RecipeAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        BakingDetail bakingObject = getIntent().getParcelableExtra(Intent.EXTRA_TEXT);
        mRecipeRecyclerView = (RecyclerView)findViewById(R.id.rv_ingredients);
        mRecipeRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new RecipeAdapter(this,bakingObject);

        mRecipeRecyclerView.setAdapter(mAdapter);

    }
}
