package com.example.newsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.newsapp.Models.NewsApiResponse;
import com.example.newsapp.Models.NewsHeadlines;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SelectListener,View.OnClickListener{
    RecyclerView recyclerView;
    CustomAdapter adapter;
    ProgressDialog dialog;
    Button but1,but2,but3,but4,but5,but6,but7;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchView = findViewById(R.id.search_view);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                dialog.setTitle("Getting the news articles of "+ query);

                dialog.show();

                RequestManager manager = new RequestManager(MainActivity.this);

                manager.getNewsHeadlines(listener, "general",query);

                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        dialog = new ProgressDialog(this);
        dialog.setTitle("Getting the news articles..");
        dialog.show();

        but1 = findViewById(R.id.btn_1);

        but1.setOnClickListener(this);

        but2 = findViewById(R.id.btn_2);

        but2.setOnClickListener(this);

        but3 = findViewById(R.id.btn_3);

        but3.setOnClickListener(this);

        but4 = findViewById(R.id.btn_4);

        but4.setOnClickListener(this);

        but5 = findViewById(R.id.btn_5);

        but5.setOnClickListener(this);

        but6 = findViewById(R.id.btn_6);

        but6.setOnClickListener(this);

        but7 = findViewById(R.id.btn_7);

        but7.setOnClickListener(this);

        RequestManager manager = new RequestManager(this);
        manager.getNewsHeadlines(listener, "general",null);
    }

    private final OnFetchDataListener<NewsApiResponse> listener = new OnFetchDataListener<NewsApiResponse>() {
        @Override
        public void onFetchData(List<NewsHeadlines> list, String message) {
            if(list.isEmpty()){
                Toast.makeText(MainActivity.this, "No data found!!!", Toast.LENGTH_SHORT).show();
            }
            else{
                showNews(list);
                dialog.dismiss();
            }
        }

        @Override
        public void onError(String message) {
            Toast.makeText(MainActivity.this, "An Error Occured!!! Try again", Toast.LENGTH_SHORT).show();

        }
    };

    private void showNews(List<NewsHeadlines> list) {

        recyclerView = findViewById(R.id.recycler_main);

        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new GridLayoutManager(this,1));

        adapter = new CustomAdapter(this,list,this  );

        recyclerView.setAdapter(adapter);
    }

    @Override
    public void OnNewsClicked(NewsHeadlines headlines) {

        startActivity(new Intent(MainActivity.this, DetailsActivity.class)
        .putExtra("data", headlines));

    }

    @Override
    public void onClick(View view) {

        Button button = (Button) view;

        String category = button.getText().toString();

        dialog.setTitle("Getting the news articles of "+ category);

        dialog.show();

        RequestManager manager = new RequestManager(this);

        manager.getNewsHeadlines(listener, category,null);

    }
}