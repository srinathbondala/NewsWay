package com.example.newsway;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class newsrvadaptor extends RecyclerView.Adapter<newsrvadaptor.ViewHolder> {
    private ArrayList<Articles> articlesArrayList;
    private Context context;
    public newsrvadaptor(ArrayList<Articles> articlesArrayList, Context context) {
        this.articlesArrayList = articlesArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public newsrvadaptor.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull newsrvadaptor.ViewHolder holder, int position) {
        Articles a = articlesArrayList.get(position);
        holder.title.setText(String.valueOf(articlesArrayList.get(position).getTitle()));
        holder.describ.setText(String.valueOf(articlesArrayList.get(position).getDescription()));
        holder.loc.setText(articlesArrayList.get(position).getSource().getName());
        Picasso.get().load(articlesArrayList.get(position).getUrlToImage()).into(holder.newsicon);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(context,news.class);
                i.putExtra("title",a.getTitle());
                i.putExtra("content",a.getContent());
                i.putExtra("desc",a.getDescription());
                i.putExtra("image",a.getUrlToImage());
                i.putExtra("url",a.getUrl());
                context.startActivity(i, ActivityOptions.makeSceneTransitionAnimation((Activity) context).toBundle());
                //context.startActivity(i);
            }
        });
        holder.share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent shareIntent = new Intent(Intent.ACTION_SEND);
                    shareIntent.setType("text/plain");
                    shareIntent.putExtra(Intent.EXTRA_SUBJECT, a.getTitle());
                    String shareMessage= a.getUrl();
                    //shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID +"\n\n";
                    shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                    context.startActivity(Intent.createChooser(shareIntent, "select one"));
                } catch(Exception e) {
                    //e.toString();
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return articlesArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView title,describ,loc;
        private ImageView newsicon,share;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.heading);
            describ = itemView.findViewById(R.id.discline);
            newsicon = itemView.findViewById(R.id.photo);
            loc =itemView.findViewById(R.id.location);
            share = itemView.findViewById(R.id.share);
            describ.setEnabled(false);
        }
    }
}
