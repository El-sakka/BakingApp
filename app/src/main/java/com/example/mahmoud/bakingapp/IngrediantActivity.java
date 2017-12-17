package com.example.mahmoud.bakingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.mahmoud.bakingapp.Model.BakingDetail;

public class IngrediantActivity extends AppCompatActivity {

    private static final String LOG_MED = IngrediantActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingrediant);

        BakingDetail bakingObject = getIntent().getExtras().getParcelable(Intent.EXTRA_TEXT);

        Log.i(LOG_MED,bakingObject.getRecipeCard());
    }
}
