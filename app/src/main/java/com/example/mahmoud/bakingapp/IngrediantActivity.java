package com.example.mahmoud.bakingapp;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.mahmoud.bakingapp.Adapter.StepsAdapter;
import com.example.mahmoud.bakingapp.Model.BakingDetail;

public class IngrediantActivity extends AppCompatActivity {

    private static final String LOG_MED = IngrediantActivity.class.getSimpleName();

    RecyclerView stepsRecyclerView;
    StepsAdapter mAdapter;
    TextView mTapTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingrediant);

        final BakingDetail bakingObject = getIntent().getParcelableExtra(Intent.EXTRA_TEXT);

        mTapTextView = (TextView)findViewById(R.id.tv_tap_ingredient);
        mTapTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IngrediantActivity.this,RecipeActivity.class);
                intent.putExtra(Intent.EXTRA_TEXT, bakingObject);
                startActivity(intent);
            }
        });

        Log.i(LOG_MED,bakingObject.getStepsList().size()+"@@");
        stepsRecyclerView = (RecyclerView)findViewById(R.id.rv_steps);
        stepsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new StepsAdapter(this,bakingObject);

        stepsRecyclerView.setAdapter(mAdapter);
    }
}
