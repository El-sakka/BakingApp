package com.example.mahmoud.bakingapp.Model;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mahmoud.bakingapp.BakingDetail;
import com.example.mahmoud.bakingapp.R;

import java.util.ArrayList;
import java.util.List;

public class BakingAdapter extends RecyclerView.Adapter<BakingAdapter.BakingViewHolder> {
    private static final String LOG_MEG = BakingAdapter.class.getSimpleName();
    List<BakingDetail> bakingList = new ArrayList<>();
    Context context;
    public BakingAdapter(Context context){
        this.context = context;
    }

    public void setBakingData(List<BakingDetail> list){
        bakingList = list;
        Log.i(LOG_MEG,bakingList.get(0).getRecipeCard());
        notifyDataSetChanged();
    }


    @Override
    public BakingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context)
                .inflate(R.layout.recipe_card,parent,false);
        BakingViewHolder viewHolder = new BakingViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(BakingViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return bakingList.size();
    }

    public class BakingViewHolder extends RecyclerView.ViewHolder{
        TextView mRecipeCardTextView ;
        BakingViewHolder(View itemView){
            super(itemView);
            mRecipeCardTextView = (TextView)itemView.findViewById(R.id.tv_recipe_card);
        }
        private void bind(final int position){
            mRecipeCardTextView.setText(bakingList.get(position).getRecipeCard());
        }
    }
}
