package com.example.mahmoud.bakingapp;

/**
 * Created by mahmoud on 17/12/17.
 */

public class BakingDetail {
    private String recipeCard;
    public BakingDetail(String recipeCard){
        this.recipeCard = recipeCard;

    }

    public String getRecipeCard() {
        return recipeCard;
    }

    public void setRecipeCard(String recipeCard) {
        this.recipeCard = recipeCard;
    }
}
