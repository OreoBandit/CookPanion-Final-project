package com.example.recipeapplec.fragments;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.recipeapplec.R;
import com.example.recipeapplec.UploudActivity;
import com.example.recipeapplec.adapter.recipeAdapter;
import com.example.recipeapplec.recipe;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Locale;
import java.util.zip.Inflater;

public class RecipeFragment extends Fragment {
    RecyclerView recyclerView;
    ArrayList<recipe> recipes;
    FloatingActionButton fab;
    Inflater inflater;
    View view;
    DatabaseReference databaseReference;
    ValueEventListener eventListener;
    recipeAdapter adapter;
    SearchView searchView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        view = inflater.inflate(R.layout.fragment_recipe, container, false);
        recyclerView=view.findViewById(R.id.reciperv);
        recipes=HomeFragment.recipes;
        adapter=new recipeAdapter(getContext(),recipes);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        fab = view.findViewById(R.id.fab);
        searchView = view.findViewById(R.id.search);
        searchView.clearFocus();
        databaseReference = FirebaseDatabase.getInstance().getReference("Android MCS");
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setCancelable(false);
        builder.setView(R.layout.progress_layout);
        AlertDialog dialog = builder.create();
        dialog.show();

        eventListener = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                recipes.clear();
                for(DataSnapshot itemSnapShot: snapshot.getChildren()){
                    recipe Recipe = itemSnapShot.getValue(recipe.class);
                    Recipe.setKey(itemSnapShot.getKey());
                    recipes.add(Recipe);
                }
                adapter.notifyDataSetChanged();
                dialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                dialog.dismiss();
            }
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchList(newText);
                return false;
            }
        });

        fab.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), UploudActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }
    public void searchList(String text){
        ArrayList<recipe> searchList = new ArrayList<>();
        for(recipe dataClass: recipes){
            if(dataClass.getFoodname().toLowerCase(Locale.ROOT).contains(text.toLowerCase())){
                searchList.add(dataClass);
            }
        }
        adapter.searchDataList(searchList);
    }
}