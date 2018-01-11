package com.example.mahmoud.bakingapp;

import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.content.SharedPreferences;
import android.os.Parcelable;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.mahmoud.bakingapp.Adapter.BakingAdapter;
import com.example.mahmoud.bakingapp.Loader.BakingLoader;
import com.example.mahmoud.bakingapp.Model.BakingDetail;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<BakingDetail>>
,BakingAdapter.ListItemClickListener
{

    private static final String LOG_MEG = MainActivity.class.getSimpleName();
    private static final String SAVE_INSTANCE_BAKING_LIST = "mBakingList";
    private static final String CURRENT_SATE = "current-state";
    public static String BAKING_JSON= "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json";
    private final int LOADER_ID= 1;

    List<BakingDetail> mBakingList;


    RecyclerView recipeCardRecyclerView;
    RecyclerView.LayoutManager mlayout ;
    BakingAdapter mAdapter;
    Toast mToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAdapter = new BakingAdapter(this,this);

        recipeCardRecyclerView = (RecyclerView)findViewById(R.id.rv_recipe_card);

        if(getResources().getBoolean(R.bool.isTablet) || getResources().getBoolean(R.bool.isLand)){
            mlayout = new GridLayoutManager(this,3);
        }
        else if(getResources().getBoolean(R.bool.isPortrait)){
            mlayout = new LinearLayoutManager(this);
        }


        recipeCardRecyclerView.setLayoutManager(mlayout);

        if(savedInstanceState == null){
            getLoaderManager().initLoader(LOADER_ID,null,this);
        }else{
            mBakingList = savedInstanceState.getParcelableArrayList(SAVE_INSTANCE_BAKING_LIST);
            mAdapter.setBakingData(mBakingList);
        }
        recipeCardRecyclerView.setAdapter(mAdapter);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if(savedInstanceState != null){
            Parcelable parcelable = savedInstanceState.getParcelable(CURRENT_SATE);
            recipeCardRecyclerView.getLayoutManager().onRestoreInstanceState(parcelable);
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(SAVE_INSTANCE_BAKING_LIST, (ArrayList<? extends Parcelable>) mBakingList);
        outState.putParcelable(CURRENT_SATE,recipeCardRecyclerView.getLayoutManager().onSaveInstanceState());
    }

    @Override
    public Loader<List<BakingDetail>> onCreateLoader(int id, Bundle args) {
        return new BakingLoader(this,BAKING_JSON);
    }

    @Override
    public void onLoadFinished(Loader<List<BakingDetail>> loader, List<BakingDetail> data) {
        //Log.i(LOG_MEG,data.get(0).getRecipeCard());


        mBakingList = data;
        mAdapter.setBakingData(data);
    }

    @Override
    public void onLoaderReset(Loader<List<BakingDetail>> loader) {

    }

    @Override
    public void onListItemClick(int clickedItemIndex) {
        if (mToast != null) {
            mToast.cancel();
        }
        String toastMessage = "Item #" + clickedItemIndex + " clicked.";
        mToast = Toast.makeText(this, toastMessage, Toast.LENGTH_LONG);

        mToast.show();

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("currentPos",clickedItemIndex);
        editor.apply();


        // to deal with communicator

        Intent intent = new Intent(MainActivity.this,Communicator.class);
        BakingDetail bakingObject = mBakingList.get(clickedItemIndex);
        //Log.i(LOG_MEG,bakingObject.getStepsList().size()+"##");
        intent.putExtra(Intent.EXTRA_TEXT,bakingObject);

        startActivity(intent);
    }
}
