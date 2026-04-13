package com.example.sportsnews;

import static androidx.core.content.ContentProviderCompat.requireContext;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<NewsItem> topStoriesList = new ArrayList<>();
    List<NewsItem> regularStoriesList = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        topStoriesList.add(new NewsItem(0,"Sport game",R.drawable.soccerball,"Yes","Basketball",false));
        topStoriesList.add(new NewsItem(0,"Sport game",R.drawable.soccerball,"Yes","Basketball",false));
        topStoriesList.add(new NewsItem(0,"Sport game",R.drawable.soccerball,"Yes","Basketball",false));

        regularStoriesList.add(new NewsItem(0,"Sport game",R.drawable.soccerball,"Yes","Basketball",false));
        regularStoriesList.add(new NewsItem(0,"Sport game",R.drawable.soccerball,"Yes","Basketball",false));
        regularStoriesList.add(new NewsItem(0,"Sport game",R.drawable.soccerball,"Yes","Basketball",false));
        regularStoriesList.add(new NewsItem(0,"Sport game",R.drawable.soccerball,"Yes","Basketball",false));
        regularStoriesList.add(new NewsItem(0,"Sport game",R.drawable.soccerball,"Yes","Basketball",false));
        regularStoriesList.add(new NewsItem(0,"Sport game",R.drawable.soccerball,"Yes","Basketball",false));
        //String[] titleList = {"Sport game", "Sporty game","Unsporty game"};
        //int[] imageList = {R.drawable.soccerball,R.drawable.soccerball,R.drawable.soccerball};

        RecyclerViewAdapter topStoriesRecyclerViewAdatper;
        RecyclerView topStoriesRecyclerView;
        topStoriesRecyclerView = findViewById(R.id.topStoriesRecyclerView);
        topStoriesRecyclerViewAdatper = new RecyclerViewAdapter(topStoriesList, this);
        topStoriesRecyclerView.setAdapter(topStoriesRecyclerViewAdatper);
        topStoriesRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));


        RecyclerViewAdapter regularStoriesRecyclerViewAdatper;
        RecyclerView regularStoriesRecyclerView;
        regularStoriesRecyclerView = findViewById(R.id.regularStoriesRecyclerView);
        regularStoriesRecyclerViewAdatper = new RecyclerViewAdapter(regularStoriesList, this);
        regularStoriesRecyclerView.setAdapter(regularStoriesRecyclerViewAdatper);
        regularStoriesRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));



        /*
        for (int i=0; i<titleList.length;i++)
        {
            NewsItem newsItem = new NewsItem(i,titleList[i],imageList[i]);
            newsList.add(newsItem);
        }*/
    }
    public void selectFragment(View view) {
        Fragment fragment;
        if (view.getId() == R.id.button5)
                fragment = new DetailFragment();
        else
                fragment = new TestFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainerView, fragment).addToBackStack(null).commit();
    }


}