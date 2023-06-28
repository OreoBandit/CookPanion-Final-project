package com.example.recipeapplec;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.recipeapplec.fragments.RecipeFragment;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class detailrecipe extends AppCompatActivity {

    Button savebtn;
    TextView foodname, fooddesc;
    ImageView foodImg;
    Button savebtnDetail;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailrecipe);
        foodname = findViewById(R.id.foodname);
        fooddesc = findViewById(R.id.foodDesc);
        foodImg = findViewById(R.id.gambarMakan);
        Intent intent=getIntent();
        String foodName=intent.getStringExtra("foodname");
        String foodDesc=intent.getStringExtra("fooddesc");
        String foodimg=intent.getStringExtra("image");
        Log.d("tes",foodimg);
        foodname.setText(foodName);
        fooddesc.setText(foodDesc);
        Picasso.get()
                .load(foodimg)
                .into(foodImg);

    }


}