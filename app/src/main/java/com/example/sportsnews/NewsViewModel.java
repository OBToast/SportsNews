package com.example.sportsnews;

import androidx.lifecycle.ViewModel;

import com.example.sportsnews.NewsItem;

import java.util.ArrayList;
import java.util.List;

public class NewsViewModel extends ViewModel {

    private final List<NewsItem> topStories = new ArrayList<>();
    private final List<NewsItem> regularStories = new ArrayList<>();
    private NewsItem selectedArticle;

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
        if (selectedArticle != null) {
            selectedArticle.ToggleBookMarked();
        }
    }
}