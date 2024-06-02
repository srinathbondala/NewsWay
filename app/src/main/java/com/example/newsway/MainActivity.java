package com.example.newsway;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetBehavior;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements categoryRVAdaptor.CategoryClickinterface {

    private RecyclerView newsRV,categoryRV;
    private ImageView profile;
    private SearchView search;
    private ProgressBar loadimgPB;
    private ArrayList<Articles> articlesArrayList,articleslist;
    private ArrayList<CatagoryRV> catagoryRVArrayList;
    private categoryRVAdaptor categoryRVAdaptor;
    private ImageView option;
    private ConstraintLayout cnt;
    private newsrvadaptor newsrvadaptor;
    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        newsRV = (RecyclerView) findViewById(R.id.newsroll);
        categoryRV = (RecyclerView) findViewById(R.id.topicrv);
        loadimgPB = (ProgressBar) findViewById(R.id.progressBar);
        profile = (ImageView) findViewById(R.id.profile);
        option = (ImageView) findViewById(R.id.listselect);
        search = (SearchView) findViewById(R.id.serach);
        cnt = (ConstraintLayout) findViewById(R.id.cnt);
        articlesArrayList = new ArrayList<>();
        catagoryRVArrayList = new ArrayList<>();
        articleslist = new ArrayList<>();
        newsRV.setHasFixedSize(true);
        newsrvadaptor = new newsrvadaptor(articlesArrayList,this);
        categoryRVAdaptor = new categoryRVAdaptor(catagoryRVArrayList,this,this);
        newsRV.setLayoutManager(new LinearLayoutManager(this));
        categoryRV.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,
                false));
        newsRV.setAdapter(newsrvadaptor);
        categoryRV.setAdapter(categoryRVAdaptor);
        loadimgPB.setVisibility(View.VISIBLE);
        cnt.setVisibility(View.INVISIBLE);
        getCategories();
        getnews("All");
        newsrvadaptor.notifyDataSetChanged();
        option.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Option selection clicked", Toast.LENGTH_SHORT).show();

            }
        });
    }
    private void getCategories(){
        catagoryRVArrayList.add(new CatagoryRV("All","https://th.bing.com/th/id/OIP.l57nWyMxq0VsfuHczAOKpQHaEP?w=296&h=180&c=7&r=0&o=5&dpr=1.5&pid=1.7"));
        catagoryRVArrayList.add(new CatagoryRV("sports","https://th.bing.com/th/id/OIP.l57nWyMxq0VsfuHczAOKpQHaEP?w=296&h=180&c=7&r=0&o=5&dpr=1.5&pid=1.7"));
        catagoryRVArrayList.add(new CatagoryRV("business","https://th.bing.com/th/id/OIP.l57nWyMxq0VsfuHczAOKpQHaEP?w=296&h=180&c=7&r=0&o=5&dpr=1.5&pid=1.7"));
        catagoryRVArrayList.add(new CatagoryRV("science","https://th.bing.com/th/id/OIP.l57nWyMxq0VsfuHczAOKpQHaEP?w=296&h=180&c=7&r=0&o=5&dpr=1.5&pid=1.7"));
        catagoryRVArrayList.add(new CatagoryRV("entertainment","https://th.bing.com/th/id/OIP.l57nWyMxq0VsfuHczAOKpQHaEP?w=296&h=180&c=7&r=0&o=5&dpr=1.5&pid=1.7"));
        catagoryRVArrayList.add(new CatagoryRV("general","https://th.bing.com/th/id/OIP.l57nWyMxq0VsfuHczAOKpQHaEP?w=296&h=180&c=7&r=0&o=5&dpr=1.5&pid=1.7"));
        catagoryRVArrayList.add(new CatagoryRV("health","https://th.bing.com/th/id/OIP.l57nWyMxq0VsfuHczAOKpQHaEP?w=296&h=180&c=7&r=0&o=5&dpr=1.5&pid=1.7"));
        catagoryRVArrayList.add(new CatagoryRV("technology","https://th.bing.com/th/id/OIP.l57nWyMxq0VsfuHczAOKpQHaEP?w=296&h=180&c=7&r=0&o=5&dpr=1.5&pid=1.7"));
    }
    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
    public void getnews(String category)
    {
        loadimgPB.setVisibility(View.VISIBLE);
        articlesArrayList.clear();
        articleslist.clear();
        String url_search = "https://newsapi.org/v2/everything?q="+category+"&sortBy=popularity&apikey=650df8ee033d4e78a591abca5764457e";
        String catURL="https://newsapi.org/v2/top-headlines?country=in&everything?q="+category+"&apiKey=650df8ee033d4e78a591abca5764457e";
        String url = "https://newsapi.org/v2/top-headlines?country=in&apiKey=650df8ee033d4e78a591abca5764457e";
        String base = "https://newsapi.org/";
        Retrofit retrofit = new Retrofit.Builder().baseUrl(base).addConverterFactory(GsonConverterFactory.create()).build();
        retrofitapi Retrofitapi = retrofit.create(retrofitapi.class);
        Call<newsdata> call=Retrofitapi.getALlNews(url);
        try {
            if(search.getQuery() != null) {
                call = Retrofitapi.getSearchNews(url_search);
        }else {
                if (!Objects.equals(category, "All"))
                    call = Retrofitapi.getNewsByCategory(catURL);
            }
        }catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        call.enqueue(new Callback<newsdata>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(Call<newsdata> call, Response<newsdata> response) {
                newsdata nd = response.body();
                if (nd != null) {
                    ArrayList<Articles> articlesArrayList1 = nd.getArticles();
                    for (Articles x : articlesArrayList1) {
                        articlesArrayList.add(new Articles(x.getTitle(), x.getDescription(), x.getUrlToImage(), x.getUrl(), x.getContent(),x.getSource()));
                    }
                    //articlesArrayList=articleslist;
                    if (articlesArrayList.isEmpty()) {
                        Toast.makeText(MainActivity.this, "Search a proper topic", Toast.LENGTH_SHORT).show();
                    }
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            loadimgPB.setVisibility(View.GONE);
                        }
                    },3000);
                    newsrvadaptor.notifyDataSetChanged();
                    cnt.setVisibility(View.VISIBLE);
                    //Toast.makeText(MainActivity.this, "done loading", Toast.LENGTH_SHORT).show();
                }
                else Toast.makeText(MainActivity.this, "Requests limit reached", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<newsdata> call, Throwable t) {
                Toast.makeText(MainActivity.this, "process failed to get news", Toast.LENGTH_SHORT).show();
            }
        });

    }
    @Override
    public void onCategoryClick(int pos) {
        String category = catagoryRVArrayList.get(pos).getCategory();
        newsRV.getRecycledViewPool().clear();
        getnews(category);
    }
    @Override
    protected void onResume() {
        super.onResume();
        loadimgPB.setVisibility(View.VISIBLE);
        cnt.setVisibility(View.INVISIBLE);
        newsRV.stopScroll();
        getnews("All");
        if (search != null) {
            search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String s) {
                    getnews(s);
                    Toast.makeText(MainActivity.this, "sending", Toast.LENGTH_SHORT).show();
                    return true;
                }

                @Override
                public boolean onQueryTextChange(String s) {
                    return false;
                }
            });
        }
        newsrvadaptor.notifyDataSetChanged();
    }

    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

        builder.setMessage("Do you want to exit ?");

        builder.setTitle("Alert !");

        builder.setCancelable(false);
        builder.setPositiveButton("Yes", (DialogInterface.OnClickListener) (dialog, which) -> {
            finish();
        });

        builder.setNegativeButton("No", (DialogInterface.OnClickListener) (dialog, which) -> {
            dialog.cancel();
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        articlesArrayList.clear();
        getnews("All");
        if (search != null) {
            search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String s) {
                    getnews(s);
                    Toast.makeText(MainActivity.this, "sending", Toast.LENGTH_SHORT).show();
                    return true;
                }
                @Override
                public boolean onQueryTextChange(String s) {
                    return false;
                }
            });
        }
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        newsRV.getRecycledViewPool().clear();
        getnews("All");
    }
}