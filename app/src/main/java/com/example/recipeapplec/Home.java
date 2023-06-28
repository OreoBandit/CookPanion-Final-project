package com.example.recipeapplec;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.recipeapplec.fragments.HomeFragment;
import com.example.recipeapplec.fragments.RecipeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class Home extends AppCompatActivity {
    BottomNavigationView menu;
    HomeFragment home = new HomeFragment();
    RecipeFragment recipe = new RecipeFragment();
    CardView btn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        menu=findViewById(R.id.navbar);


        getSupportFragmentManager().beginTransaction().replace(R.id.containerLayoutHome,home).commit();
        menu.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch(item.getItemId()){
                    case R.id.home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.containerLayoutHome,home).commit();
                        return true;
                    case R.id.recipes:
                        getSupportFragmentManager().beginTransaction().replace(R.id.containerLayoutHome,recipe).commit();
                        return true;
                }
                return false;
            }
        });

    }
}