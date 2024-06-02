package com.example.newsway;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.net.URI;

public class news extends AppCompatActivity {
    String title,desc,url,image,content;
    private TextView titlen,contentn;
    private ImageView iconimg;
    private Button bt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        getSupportActionBar().hide();
        title = getIntent().getStringExtra("title");
        url = getIntent().getStringExtra("url");
        image = getIntent().getStringExtra("image");
        desc = getIntent().getStringExtra("desc");
        content = getIntent().getStringExtra("content");
        titlen = (TextView) findViewById(R.id.titlen);
        contentn =(TextView) findViewById(R.id.contentn);
        iconimg = (ImageView) findViewById(R.id.sourceimg);
        bt = (Button) findViewById(R.id.button);
        titlen.setText(title);
        Picasso.get().load(image).into(iconimg);
        contentn.setText(content);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.swipe_in_left,
                R.anim.swipe_out_right);
    }
}