package com.example.sportsnews;

import static androidx.core.content.ContentProviderCompat.requireContext;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sportsnews.NewsItem;
import com.example.sportsnews.NewsViewModel;
import com.example.sportsnews.R;
import com.example.sportsnews.RecyclerViewAdapter;

import java.util.List;

public class ViewArticles extends Fragment {

    private List<NewsItem> topStoriesList;
    private List<NewsItem> regularStoriesList;

    public ViewArticles() {
        // EMPTY constructor only
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_view_articles, container, false);

        NewsViewModel viewModel = new ViewModelProvider(requireActivity()).get(NewsViewModel.class);

        topStoriesList = viewModel.getTopStories();
        regularStoriesList = viewModel.getRegularStories();

        RecyclerView topRecyclerView = view.findViewById(R.id.topStoriesRecyclerView);
        RecyclerViewAdapter topAdapter = new RecyclerViewAdapter(topStoriesList, requireContext());

        topRecyclerView.setAdapter(topAdapter);
        topRecyclerView.setLayoutManager(
                new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        );

        RecyclerView regularRecyclerView = view.findViewById(R.id.regularStoriesRecyclerView);
        RecyclerViewAdapter regularAdapter = new RecyclerViewAdapter(regularStoriesList, requireContext());

        regularRecyclerView.setAdapter(regularAdapter);
        regularRecyclerView.setLayoutManager(
                new GridLayoutManager(requireContext(), 2)
        );

        return view;
    }
}