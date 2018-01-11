package com.example.mahmoud.bakingapp;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.example.mahmoud.bakingapp.Model.BakingDetail;

public class IngrediantActivity extends AppCompatActivity {
    BakingDetail bakingObject =null;

    private static final String LOG_MED = IngrediantActivity.class.getSimpleName();
    private static final String BAKING_OBJECT = "baking-object";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ingrediant_fragment);
        bakingObject = getIntent().getParcelableExtra(Intent.EXTRA_TEXT);

       IngrediantFragment fragment =  new IngrediantFragment();
       Bundle bundle = new Bundle();
       bundle.putParcelable(BAKING_OBJECT,bakingObject);
       fragment.setArguments(bundle);

        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.ingrediant_fragment_container,fragment)
                .commit();

    }

}
