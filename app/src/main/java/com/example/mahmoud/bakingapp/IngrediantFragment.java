package com.example.mahmoud.bakingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mahmoud.bakingapp.Adapter.StepsAdapter;
import com.example.mahmoud.bakingapp.Model.BakingDetail;

/**
 * Created by mahmoudelsakka on 27/12/17.
 */

public class IngrediantFragment extends Fragment implements StepsAdapter.onListItemClickListener{


    private static final String BAKING_OBJECT = "baking-object";
    private static final String LOG_MED = IngrediantFragment.class.getSimpleName();
    private static final String POS = "position";
    BakingDetail bakingObject =null;
    private ListenerCommunicator mListener;

    RecyclerView stepsRecyclerView;
    StepsAdapter mAdapter;
    TextView mTapTextView;
    Toast mToast;


    public IngrediantFragment(){

    }

    void setBundleListener(ListenerCommunicator mListener){
        this.mListener = mListener;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_ingrediant,container,false);

        //bakingObject = getArguments().getParcelable(BAKING_OBJECT);

        Bundle bundle = getArguments();
        bakingObject = bundle.getParcelable(BAKING_OBJECT);

        mTapTextView = (TextView)rootView.findViewById(R.id.tv_tap_ingredient);
        mTapTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get Activity or ingredient fragment test this
                Intent intent = new Intent(getActivity(),IngrediantDetails.class);
                intent.putExtra(Intent.EXTRA_TEXT, bakingObject);
                startActivity(intent);
            }
        });


        Log.i(LOG_MED,bakingObject.getStepsList().size()+"@@");
        stepsRecyclerView = (RecyclerView)rootView.findViewById(R.id.rv_steps);
        stepsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new StepsAdapter(getActivity(),bakingObject,this);

        stepsRecyclerView.setAdapter(mAdapter);

        return rootView;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

    }

    @Override
    public void ListItemClicked(int clickedItemIndex) {
        if (mToast != null) {
            mToast.cancel();
        }
        String toastMessage = "Item #" + clickedItemIndex + " clicked.";
        mToast = Toast.makeText(getActivity(), toastMessage, Toast.LENGTH_LONG);

        mToast.show();
        Bundle bundle = new Bundle();
        bundle.putParcelable(BAKING_OBJECT,bakingObject);
        bundle.putInt(POS,clickedItemIndex);
        mListener.setBundle(bundle);
      /*  if(getResources().getBoolean(R.bool.isTablet) == false) {
            Intent intent = new Intent(getActivity(), StepActivity.class);
            intent.putExtra(BAKING_OBJECT, bakingObject);
            intent.putExtra(POS, clickedItemIndex);

            startActivity(intent);
        }
        else{
            Bundle bundle = new Bundle();
            bundle.putParcelable(BAKING_OBJECT,bakingObject);
            bundle.putInt(POS,clickedItemIndex);
            mListener.setBundle(bundle);
        }*/

    }
}
