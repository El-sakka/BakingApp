package com.example.mahmoud.bakingapp;

import android.app.LoaderManager;
import android.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.mahmoud.bakingapp.Model.BakingAdapter;
import com.example.mahmoud.bakingapp.Model.BakingLoader;

import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<BakingDetail>> {

    private static final String LOG_MEG = MainActivity.class.getSimpleName();
    public static String BAKING_JSON= "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json";
    private final int LOADER_ID= 1;

    RecyclerView recipeCardRecyclerView;
    BakingAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAdapter = new BakingAdapter(this);
        recipeCardRecyclerView = (RecyclerView)findViewById(R.id.rv_recipe_card);
        recipeCardRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        getLoaderManager().initLoader(LOADER_ID,null,this);

        recipeCardRecyclerView.setAdapter(mAdapter);

    }

    @Override
    public Loader<List<BakingDetail>> onCreateLoader(int id, Bundle args) {
        return new BakingLoader(this,BAKING_JSON);
    }

    @Override
    public void onLoadFinished(Loader<List<BakingDetail>> loader, List<BakingDetail> data) {
        //Log.i(LOG_MEG,data.get(0).getRecipeCard());
        mAdapter.setBakingData(data);
    }

    @Override
    public void onLoaderReset(Loader<List<BakingDetail>> loader) {

    }
}
