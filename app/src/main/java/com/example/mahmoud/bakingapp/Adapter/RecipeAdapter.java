package com.example.mahmoud.bakingapp.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mahmoud.bakingapp.Model.BakingDetail;
import com.example.mahmoud.bakingapp.R;

/**
 * Created by mahmoud on 18/12/17.
 */

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>{
    BakingDetail bakingObject ;
    Context context;

    public RecipeAdapter(Context context,BakingDetail bakingObject){
        this.context = context;
        this.bakingObject = bakingObject;
    }
    @Override
    public RecipeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context)
                .inflate(R.layout.ingredients_details,parent,false);
        RecipeViewHolder viewHolder = new RecipeViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecipeViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        int count = 0;
        try{
            count = bakingObject.getIngrediantlist().size();
        }catch (Exception e){

        }
        return count;
    }


    public class RecipeViewHolder extends RecyclerView.ViewHolder{

        TextView mIngredientTextView;
        TextView mQuantityTextView;
        RecipeViewHolder(View itemView){
            super(itemView);
            mIngredientTextView = (TextView) itemView.findViewById(R.id.tv_ingredient_detail);
            mQuantityTextView = (TextView)itemView.findViewById(R.id.tv_ingredient_quantity);
        }
        private void bind(final int position){
            mIngredientTextView.setText(bakingObject.getIngrediantlist().get(position).getIngrediantsIngredient());
            mQuantityTextView.setText(bakingObject.getIngrediantlist().get(position).getIngrediantsQuantity()+" ");
            mQuantityTextView.append(bakingObject.getIngrediantlist().get(position).getIngrediantsMeasure());
        }
    }
}
