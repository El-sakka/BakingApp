package com.example.mahmoud.bakingapp.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mahmoud.bakingapp.Model.BakingDetail;
import com.example.mahmoud.bakingapp.R;

/**
 * Created by mahmoud on 18/12/17.
 */

public class StepsAdapter extends RecyclerView.Adapter<StepsAdapter.StepsViewHolder> {
    private static final String LOG_MEG = StepsAdapter.class.getSimpleName();
    Context mContext;
    BakingDetail mBakingObject;

    public StepsAdapter(Context context, BakingDetail mBakingObject){
        mContext = context;
        this.mBakingObject = mBakingObject;
    }

    @Override
    public StepsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context)
                .inflate(R.layout.steps,parent,false);
        StepsViewHolder viewHolder = new StepsViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(StepsViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        int count = 0;
        try {
            count = mBakingObject.getStepsList().size();

        }catch (Exception e){
        }
        Log.i(LOG_MEG,count+"@");
        return count;
    }

    public class StepsViewHolder extends RecyclerView.ViewHolder{
        TextView mStepsTextView;
        TextView mDescriptionTextView;
        StepsViewHolder(View itemView){
            super(itemView);
            mStepsTextView = (TextView)itemView.findViewById(R.id.tv_step_number);
            mDescriptionTextView = (TextView)itemView.findViewById(R.id.tv_short_description);
        }
        private void bind(final int position){
            Log.i(LOG_MEG,mBakingObject.getStepsList().get(2).getStepShortDescription());
            mStepsTextView.append(" #"+mBakingObject.getStepsList().get(position).getStepId());
            mDescriptionTextView.setText(mBakingObject.getStepsList().get(position).getStepShortDescription());
        }
    }
}
