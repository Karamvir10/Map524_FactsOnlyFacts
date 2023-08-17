package com.example.newsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.newsapp.Models.NewsHeadlines;
import com.squareup.picasso.Picasso;

public class DetailsActivity extends AppCompatActivity {
    NewsHeadlines headlines;
    TextView textTitle,textAuthor,textTime,textDetail,textContent;
    ImageView news;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_details);

        textTitle = findViewById(R.id.text_detail_title);

        textAuthor = findViewById(R.id.text_detail_author);

        textTime = findViewById(R.id.text_detail_time);

        textDetail = findViewById(R.id.text_detail_detail);

        textContent = findViewById(R.id.text_detail_content);

        news = findViewById(R.id.img_detail_news);

        headlines= (NewsHeadlines) getIntent().getSerializableExtra("data");

        textTitle.setText(headlines.getTitle());

        textAuthor.setText(headlines.getAuthor());

        textTime.setText(headlines.getPublishedAt());

        textDetail.setText(headlines.getDescription());

        textContent.setText(headlines.getContent());

        Picasso.get().load(headlines.getUrlToImage()).into(news);


    }
}