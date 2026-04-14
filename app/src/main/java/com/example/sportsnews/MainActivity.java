package com.example.sportsnews;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

public class MainActivity extends AppCompatActivity {

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

        //Create a viewmodel to store the news articles across the fragments and everything
        NewsViewModel viewModel = new ViewModelProvider(this).get(NewsViewModel.class);

        //Add the stories to the view model
        viewModel.getTopStories().add(new NewsItem(0,"New Soccer Game",R.drawable.soccerball,"A new type of soccer game is taking the world by storm!","soccer",false));
        viewModel.getTopStories().add(new NewsItem(0,"Football trending",R.drawable.football,"Football is trending with the youths these days!","football",false));
        viewModel.getTopStories().add(new NewsItem(0,"Cricket is boring!",R.drawable.cricket,"Cricket... Is still boring!","cricket",false));
        viewModel.getRegularStories().add(new NewsItem(0,"Soccer to replace football",R.drawable.soccerball,"Soccer is set to replace football in some languages","soccer",false));
        viewModel.getRegularStories().add(new NewsItem(0,"New soccer balls",R.drawable.soccerball,"A local soccer team is going to get new soccerballs made out of pentagons and hexagons.","soccer",false));
        viewModel.getRegularStories().add(new NewsItem(0,"Cricket second to watching paint dry",R.drawable.cricket,"Yep. Cricket is more boring than watching paint dry.","cricket",false));
        viewModel.getRegularStories().add(new NewsItem(0,"New football rules",R.drawable.football,"New football rules make the game more like soccer.","football",false));
        viewModel.getRegularStories().add(new NewsItem(0,"200 year cricket game",R.drawable.cricket,"The most exciting thing that happened was the grass grew longer!","cricket",false));

        //Set the first fragment to be the view articles fragment by default
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, new ViewArticlesFragment()).commit();
    }
}