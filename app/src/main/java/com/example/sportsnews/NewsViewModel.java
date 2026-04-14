package com.example.sportsnews;

import androidx.lifecycle.ViewModel;
import java.util.ArrayList;
import java.util.List;

public class NewsViewModel extends ViewModel {
    //Create a view model to store the stories across fragments
    //Stores the top stories, regular stories, and currently selected article
    private List<NewsItem> topStories = new ArrayList<>();
    private List<NewsItem> regularStories = new ArrayList<>();
    private NewsItem selectedArticle;
    //Create methods for accessing news articles and also updating the bookmark
    public List<NewsItem> getTopStories() {
        return topStories;
    }

    public List<NewsItem> getRegularStories() {
        return regularStories;
    }

    public void setSelectedArticle(NewsItem item) {
        selectedArticle = item;
    }

    public NewsItem getSelectedArticle() {
        return selectedArticle;
    }

    public void toggleBookmarkSelected() {
        selectedArticle.ToggleBookMarked();
    }
}