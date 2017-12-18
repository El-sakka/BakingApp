package com.example.mahmoud.bakingapp.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by mahmoud on 17/12/17.
 */

public class BakingIngrediant implements Parcelable{

    String ingrediantsMeasure;
    String ingrediantsIngredient;
    int ingrediantsQuantity;

    public BakingIngrediant(String ingrediantsMeasure, String ingrediantsIngredient, int ingrediantsQuantity) {
        this.ingrediantsMeasure = ingrediantsMeasure;
        this.ingrediantsIngredient = ingrediantsIngredient;
        this.ingrediantsQuantity = ingrediantsQuantity;
    }

    protected BakingIngrediant(Parcel in) {
        ingrediantsMeasure = in.readString();
        ingrediantsIngredient = in.readString();
        ingrediantsQuantity = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(ingrediantsMeasure);
        dest.writeString(ingrediantsIngredient);
        dest.writeInt(ingrediantsQuantity);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<BakingIngrediant> CREATOR = new Creator<BakingIngrediant>() {
        @Override
        public BakingIngrediant createFromParcel(Parcel in) {
            return new BakingIngrediant(in);
        }

        @Override
        public BakingIngrediant[] newArray(int size) {
            return new BakingIngrediant[size];
        }
    };

    public String getIngrediantsMeasure() {
        return ingrediantsMeasure;
    }

    public void setIngrediantsMeasure(String ingrediantsMeasure) {
        this.ingrediantsMeasure = ingrediantsMeasure;
    }

    public String getIngrediantsIngredient() {
        return ingrediantsIngredient;
    }

    public void setIngrediantsIngredient(String ingrediantsIngredient) {
        this.ingrediantsIngredient = ingrediantsIngredient;
    }

    public int getIngrediantsQuantity() {
        return ingrediantsQuantity;
    }

    public void setIngrediantsQuantity(int ingrediantsQuantity) {
        this.ingrediantsQuantity = ingrediantsQuantity;
    }
}
