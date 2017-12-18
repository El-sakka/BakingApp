package com.example.mahmoud.bakingapp.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by mahmoud on 17/12/17.
 */

public class BakingDetail implements Parcelable{

    List<BakingIngrediant> Ingrediantlist;
    List<BakingSteps> stepsList;
    String recipeCard;
    int servings;



    public BakingDetail(String recipeCard,
                        int servings,
                        List<BakingIngrediant> ingrediantlist,
                        List<BakingSteps> stepsList
                        ){
        this.recipeCard = recipeCard;
        this.servings = servings;
        this.Ingrediantlist = ingrediantlist;
        this.stepsList = stepsList;
    }


    protected BakingDetail(Parcel in) {
        Ingrediantlist = in.createTypedArrayList(BakingIngrediant.CREATOR);
        stepsList = in.createTypedArrayList(BakingSteps.CREATOR);
        recipeCard = in.readString();
        servings = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(Ingrediantlist);
        dest.writeTypedList(stepsList);
        dest.writeString(recipeCard);
        dest.writeInt(servings);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<BakingDetail> CREATOR = new Creator<BakingDetail>() {
        @Override
        public BakingDetail createFromParcel(Parcel in) {
            return new BakingDetail(in);
        }

        @Override
        public BakingDetail[] newArray(int size) {
            return new BakingDetail[size];
        }
    };

    public List<BakingIngrediant> getIngrediantlist() {
        return Ingrediantlist;
    }

    public void setIngrediantlist(List<BakingIngrediant> ingrediantlist) {
        Ingrediantlist = ingrediantlist;
    }

    public List<BakingSteps> getStepsList() {
        return stepsList;
    }

    public void setStepsList(List<BakingSteps> stepsList) {
        this.stepsList = stepsList;
    }

    public BakingDetail(String recipeCard){
        this.recipeCard = recipeCard;

    }


    public String getRecipeCard() {
        return recipeCard;
    }

    public void setRecipeCard(String recipeCard) {
        this.recipeCard = recipeCard;
    }


    public int getServings() {
        return servings;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }

}
