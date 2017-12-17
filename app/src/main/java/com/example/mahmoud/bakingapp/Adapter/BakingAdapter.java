package com.example.mahmoud.bakingapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mahmoud.bakingapp.IngrediantActivity;
import com.example.mahmoud.bakingapp.Model.BakingDetail;
import com.example.mahmoud.bakingapp.R;

import java.util.ArrayList;
import java.util.List;

public class BakingAdapter extends RecyclerView.Adapter<BakingAdapter.BakingViewHolder> {
    private static final String LOG_MEG = BakingAdapter.class.getSimpleName();

    final private ListItemClickListener mOnClickListener;
    List<BakingDetail> bakingList = new ArrayList<>();
    Context context;

    public interface ListItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }

    public BakingAdapter(Context context,ListItemClickListener listener){
        this.context = context;
        mOnClickListener = listener;
    }

    public void setBakingData(List<BakingDetail> list){
        bakingList = list;
//        Log.i(LOG_MEG,bakingList.get(0).getRecipeCard());
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
        int count = 0;
        try {
            count = bakingList.size();
        }catch (Exception e){

        }
        return count;
    }

    public class BakingViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView mRecipeCardTextView ;
        TextView mServingsTextView;
        BakingDetail bakingObject;


        BakingViewHolder(View itemView){
            super(itemView);
            mRecipeCardTextView = (TextView)itemView.findViewById(R.id.tv_recipe_card);
            mServingsTextView = (TextView)itemView.findViewById(R.id.tv_serving);
            itemView.setOnClickListener(this);
           /* int clickPostion = getAdapterPosition();
            Log.i(LOG_MEG,clickPostion+"#");

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, IngrediantActivity.class);
                    context.startActivity(intent);
                }
            });*/
        }
        private void bind(final int position){
            mRecipeCardTextView.setText(bakingList.get(position).getRecipeCard());
            mServingsTextView.setText("#"+bakingList.get(position).getServings());

        }

        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            Log.i(LOG_MEG,clickedPosition+"#");
            mOnClickListener.onListItemClick(clickedPosition);
        }
    }
}
