package com.example.newsway;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class categoryRVAdaptor extends RecyclerView.Adapter<categoryRVAdaptor.ViewHolder> {
    private ArrayList<CatagoryRV> catagoryRVS;
    private CategoryClickinterface categoryClickinterface;
    private Context context;

    public categoryRVAdaptor(ArrayList<CatagoryRV> catagoryRVS, CategoryClickinterface categoryClickinterface, Context context) {
        this.catagoryRVS = catagoryRVS;
        this.categoryClickinterface = categoryClickinterface;
        this.context = context;
    }
    @NonNull
    @Override
    public categoryRVAdaptor.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.topic,parent,false);
        return new categoryRVAdaptor.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull categoryRVAdaptor.ViewHolder holder, int position) {
        CatagoryRV cv = catagoryRVS.get(position);
        if(cv.getCategory()!=null) {
            holder.tv.setText(cv.getCategory());
        }
        if(holder.iv==null){
            Picasso.get().load("https://th.bing.com/th/id/OIP.o2zHZJHUpP5nqn1GmyuPzgHaDY?w=274&h=160&c=7&r=0&o=5&dpr=1.5&pid=1.7").into((holder.iv));
        }
        Picasso.get().load(cv.getCategotyimg()).into(holder.iv);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                categoryClickinterface.onCategoryClick(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return catagoryRVS.size();
    }
    public interface CategoryClickinterface{
        void onCategoryClick(int pos);
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iv;
        TextView tv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.info);
            iv = itemView.findViewById(R.id.relimg);
        }
    }
}
