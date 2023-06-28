package com.example.recipeapplec;

import androidx.recyclerview.widget.RecyclerView;

public class recipe{
    String foodname, foodDesc, calorie, image;

    private String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
    public recipe(String foodname, String foodDesc, String calorie, String image) {
        this.foodname = foodname;
        this.foodDesc = foodDesc;
        this.calorie = calorie;
        this.image = image;
    }

    public String getFoodname() {
        return foodname;
    }

    public void setFoodname(String foodname) {
        this.foodname = foodname;
    }

    public String getFoodDesc() {
        return foodDesc;
    }

    public void setFoodDesc(String foodDesc) {
        this.foodDesc = foodDesc;
    }

    public String getCalorie() {
        return calorie;
    }

    public void setCalorie(String calorie) {
        this.calorie = calorie;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public recipe(){

    }
}
