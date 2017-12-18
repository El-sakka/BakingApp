package com.example.mahmoud.bakingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.mahmoud.bakingapp.Adapter.StepsAdapter;
import com.example.mahmoud.bakingapp.Model.BakingDetail;

public class IngrediantActivity extends AppCompatActivity {

    private static final String LOG_MED = IngrediantActivity.class.getSimpleName();

    RecyclerView stepsRecyclerView;
    StepsAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingrediant);

        BakingDetail bakingObject = getIntent().getParcelableExtra("T");


        Log.i(LOG_MED,bakingObject.getStepsList().size()+"@@");
        stepsRecyclerView = (RecyclerView)findViewById(R.id.rv_steps);
        stepsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new StepsAdapter(this,bakingObject);

        stepsRecyclerView.setAdapter(mAdapter);
    }
}
