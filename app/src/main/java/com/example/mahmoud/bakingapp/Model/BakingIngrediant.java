package com.example.mahmoud.bakingapp.Model;

/**
 * Created by mahmoud on 17/12/17.
 */

public class BakingIngrediant {

    String ingrediantsMeasure;
    String ingrediantsIngredient;
    int ingrediantsQuantity;

    public BakingIngrediant(String ingrediantsMeasure, String ingrediantsIngredient, int ingrediantsQuantity) {
        this.ingrediantsMeasure = ingrediantsMeasure;
        this.ingrediantsIngredient = ingrediantsIngredient;
        this.ingrediantsQuantity = ingrediantsQuantity;
    }

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
