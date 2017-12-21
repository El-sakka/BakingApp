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
import android.widget.Toast;

import com.example.mahmoud.bakingapp.Adapter.StepsAdapter;
import com.example.mahmoud.bakingapp.Model.BakingDetail;

public class IngrediantActivity extends AppCompatActivity implements StepsAdapter.onListItemClickListener {

    private static final String LOG_MED = IngrediantActivity.class.getSimpleName();

    RecyclerView stepsRecyclerView;
    StepsAdapter mAdapter;
    TextView mTapTextView;
    BakingDetail bakingObject =null;

    Toast mToast;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingrediant);

        bakingObject = getIntent().getParcelableExtra(Intent.EXTRA_TEXT);

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
        mAdapter = new StepsAdapter(this,bakingObject,this);

        stepsRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void ListItemClicked(int clickedItemIndex) {
        if (mToast != null) {
            mToast.cancel();
        }
        String toastMessage = "Item #" + clickedItemIndex + " clicked.";
        mToast = Toast.makeText(this, toastMessage, Toast.LENGTH_LONG);

        mToast.show();

        Intent intent = new Intent(IngrediantActivity.this,StepActivity.class);
         intent.putExtra("object",bakingObject);
         intent.putExtra("position",clickedItemIndex);
        startActivity(intent);
    }

}
