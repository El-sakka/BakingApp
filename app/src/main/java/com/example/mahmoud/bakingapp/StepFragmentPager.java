package com.example.mahmoud.bakingapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mahmoud.bakingapp.Adapter.StepFragmentPagerAdapter;
import com.example.mahmoud.bakingapp.Model.BakingDetail;

/**
 * Created by mahmoudelsakka on 31/12/17.
 */

public class StepFragmentPager extends Fragment {


    private static final String BAKING_OBJECT = "baking-object";
    private static final String POS = "position";
    private static final String COUNT = "count";

    BakingDetail bakingObject;
    StepFragmentPagerAdapter mAdapter;
    ViewPager mViewPager;
    int count;
    int position;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.category,container,false);



        Bundle getBundle = getArguments();
        bakingObject = getBundle.getParcelable(BAKING_OBJECT);
        //count = getBundle.getInt(COUNT);
        position= getBundle.getInt(POS);
        count = bakingObject.getStepsList().size();

        mViewPager = rootView.findViewById(R.id.viewPager);


        mAdapter = new StepFragmentPagerAdapter(getActivity(),getActivity().getSupportFragmentManager(),count,bakingObject,position);

        mViewPager.setAdapter(mAdapter);
        mViewPager.setCurrentItem(position);

        TabLayout tabLayout =(TabLayout) rootView.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        return rootView;
    }
}
