package com.example.mahmoud.bakingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.mahmoud.bakingapp.Model.BakingDetail;

/**
 * Created by mahmoudelsakka on 31/12/17.
 */

public class Communicator extends AppCompatActivity implements ListenerCommunicator{

    private static final String BAKING_OBJECT = "baking-object";
    private static final String BUNDLE = "bundle";
    boolean mIsTwoPan = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.communicator_layout);

        BakingDetail object  = getIntent().getParcelableExtra(Intent.EXTRA_TEXT);

        IngrediantFragment mIngredientFragment = new IngrediantFragment();
        //StepFragmentPager mStepFragment = new StepFragmentPager();
        mIngredientFragment.setBundleListener(this);
        Bundle bundle = new Bundle();
        bundle.putParcelable(BAKING_OBJECT,object);
        mIngredientFragment.setArguments(bundle);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.f_ingredent,mIngredientFragment).commit();
       // mStepFragment.setArguments(bundle);

       // mIngredientFragment.setBundle(this);

       /* if(findViewById(R.id.f_steps) != null){
            getResources().getBoolean(R.bool.IsTwoPan) = true;
        }*/

        /*if(mIsTwoPan == false){
            getSupportFragmentManager().beginTransaction().add(R.id.f_ingredent,mIngredientFragment).commit();
        }
        else{
            getSupportFragmentManager().beginTransaction().add(R.id.f_ingredent,mIngredientFragment)
                    .replace(R.id.f_steps,mStepFragment)
                    .commit();
        }*/
    }

    @Override
    public void setBundle(Bundle bundle) {
        if(getResources().getBoolean(R.bool.isTablet) == false){
            Intent intent = new Intent(this,StepActivity.class);
            intent.putExtra(BUNDLE,bundle);
            startActivity(intent);
        }
        else{
            StepFragmentPager mStepFragment = new StepFragmentPager();
            mStepFragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction().replace(R.id.f_steps,mStepFragment).commit();
        }
    }
}
