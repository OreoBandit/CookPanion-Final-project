package com.example.recipeapplec.adapter;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.recipeapplec.R;
import com.example.recipeapplec.recipe;
import com.example.recipeapplec.detailrecipe;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class recipeAdapter extends RecyclerView.Adapter<recipeAdapter.DataHolder> {

    LayoutInflater inflater;
    Context ctx;
    ArrayList<recipe> recipes;

    public recipeAdapter(Context context, ArrayList<recipe> recipeList){
        this.ctx = context;
        this.inflater = LayoutInflater.from(context);
        this.recipes = recipeList;
    }

    @NonNull
    @Override
    public DataHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(ctx).inflate(R.layout.item_recipe,parent,false);
        return new DataHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull DataHolder holder, int position) {
        holder.foodname.setText(recipes.get(position).getFoodname());
        holder.calorie.setText(recipes.get(position).getCalorie());
        Picasso.get()
                .load(recipes.get(position).getImage())
                .into(holder.foodImg);

        holder.btnDetail.setOnClickListener(e->{
            Intent movetoDetails=new Intent(ctx,detailrecipe.class);
            movetoDetails.putExtra("foodname",recipes.get(position).getFoodname());
            movetoDetails.putExtra("fooddesc",recipes.get(position).getFoodDesc());
            movetoDetails.putExtra("image",recipes.get(position).getImage());
            ctx.startActivity(movetoDetails);
        });
    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }
    public void searchDataList(ArrayList<recipe> searchList){
        recipes = searchList;
        notifyDataSetChanged();

    }
    public class DataHolder extends RecyclerView.ViewHolder{
        TextView foodname, calorie;
        ImageView foodImg;
        Button btnDetail;

        public DataHolder(@NonNull View itemView) {
            super(itemView);
            foodname = itemView.findViewById(R.id.foodname);
            calorie = itemView.findViewById(R.id.foodcal);
            foodImg = itemView.findViewById(R.id.foodimg);
            btnDetail = itemView.findViewById(R.id.btnDetail);
        }
    }

}