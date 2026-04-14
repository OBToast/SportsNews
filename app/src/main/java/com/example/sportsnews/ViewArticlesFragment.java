package com.example.sportsnews;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ViewArticlesFragment extends Fragment {

    //Create fields for the different lists of news articles
    //Top stories are the ones without a description, displayed at the top
    private List<NewsItem> topStoriesList;
    //Regular stories is the complete list of stories
    private List<NewsItem> regularStoriesList;
    //Sorted stories is the stories after they are filtered by the filter tags
    private List<NewsItem> sortedStoriesList;

    public ViewArticlesFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Inflate the view
        View view = inflater.inflate(R.layout.fragment_view_articles, container, false);

        //Get the view model containing the news articles
        NewsViewModel viewModel = new ViewModelProvider(requireActivity()).get(NewsViewModel.class);

        //Get the radio buttons for filtering by category
        RadioButton allButton = view.findViewById(R.id.allButton);
        RadioButton cricketButton = view.findViewById(R.id.cricketButton);
        RadioButton soccerButton = view.findViewById(R.id.soccerButton);
        RadioButton footballButton = view.findViewById(R.id.footballButton);
        RadioButton bookmarksButton = view.findViewById(R.id.bookmarksButton);

        //Set the stories
        topStoriesList = viewModel.getTopStories();
        regularStoriesList = viewModel.getRegularStories();
        sortedStoriesList = new ArrayList<>(regularStoriesList);

        //Get the top stories recycler and set its adapter, and its linear layout manager to be horizontal
        RecyclerView topRecyclerView = view.findViewById(R.id.topStoriesRecyclerView);
        TopRecyclerViewAdapter topAdapter = new TopRecyclerViewAdapter(topStoriesList, requireContext());
        topRecyclerView.setAdapter(topAdapter);
        topRecyclerView.setLayoutManager(
                new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        );

        //Get the regular stories recycler and set its adapter, as well as its grid manager to be a grid manager with a span of two
        RecyclerView regularRecyclerView = view.findViewById(R.id.regularStoriesRecyclerView);
        RegularRecyclerViewAdapter regularAdapter = new RegularRecyclerViewAdapter(sortedStoriesList, requireContext());
        regularRecyclerView.setAdapter(regularAdapter);
        regularRecyclerView.setLayoutManager(new GridLayoutManager(requireContext(), 2));

        //Add a listener to the radio buttons to create the filters.
        //All button sets the sorted stories to all of the regular stories
        allButton.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                sortedStoriesList.clear();
                sortedStoriesList.addAll(regularStoriesList);
                regularAdapter.updateList(sortedStoriesList);
            }
        });

        //The cricket button sets the sorted stories to only the stories with the cricket category
        cricketButton.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                sortedStoriesList.clear();
                for (NewsItem newsItem : regularStoriesList) {
                    if (newsItem.GetCategory().equals("cricket")) {
                        sortedStoriesList.add(newsItem);
                    }
                }
                regularAdapter.updateList(sortedStoriesList);
            }
        });
        //The soccer button sets the sorted stories to only the stories with the soccer category
        soccerButton.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                sortedStoriesList.clear();
                for (NewsItem newsItem : regularStoriesList) {
                    if (newsItem.GetCategory().equals("soccer")) {
                        sortedStoriesList.add(newsItem);
                    }
                }
                regularAdapter.updateList(sortedStoriesList);
            }
        });
        //The football button sets the sorted stories to only the stories with the football category
        footballButton.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                sortedStoriesList.clear();
                for (NewsItem newsItem : regularStoriesList) {
                    if (newsItem.GetCategory().equals("football")) {
                        sortedStoriesList.add(newsItem);
                    }
                }
                regularAdapter.updateList(sortedStoriesList);
            }
        });
        //The bookmark button sets the sorted stories to only the stories with the bookmark boolean set as true
        bookmarksButton.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                sortedStoriesList.clear();
                for (NewsItem newsItem : regularStoriesList) {
                    if (newsItem.GetBookmarked() == true) {
                        sortedStoriesList.add(newsItem);
                    }
                }
                regularAdapter.updateList(sortedStoriesList);
            }
        });
        return view;
    }
}