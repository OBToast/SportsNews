package com.example.sportsnews;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DetailFragment extends Fragment {

    private NewsViewModel viewModel;

    public DetailFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //Inflate the view
        View view = inflater.inflate(R.layout.fragment_detail, container, false);

        //Get the view model storing the news data
        viewModel = new ViewModelProvider(requireActivity()).get(NewsViewModel.class);

        //Get the selected article
        NewsItem selected = viewModel.getSelectedArticle();

        //Get the UI elements
        TextView title = view.findViewById(R.id.titleTextView);
        ImageView image = view.findViewById(R.id.imageView);
        TextView description = view.findViewById(R.id.descriptionTextView);
        Button bookmark = view.findViewById(R.id.bookmarkButton);
        Button back = view.findViewById(R.id.backButton);
        RecyclerView relatedRecycler = view.findViewById(R.id.recyclerView);

        //Set the back button function
        back.setOnClickListener(v -> {
            requireActivity().getSupportFragmentManager().popBackStack();
        });

        //Set the bookmark function, with toast messages to confirm
        bookmark.setOnClickListener(v -> {
            viewModel.toggleBookmarkSelected();
            if (selected.bookmarked == true)
            { Toast.makeText(requireContext(), "Bookmarked!", Toast.LENGTH_SHORT).show(); }
            else
            { Toast.makeText(requireContext(), "Removed bookmark", Toast.LENGTH_SHORT).show(); }
        });

        //Set the value of UI fields based on the selected articles properties
        title.setText(selected.GetTitle());
        image.setImageResource(selected.GetImage());
        description.setText(selected.GetDescription());
        if (selected.bookmarked == true)
        { bookmark.setText("Bookmarked"); }
        else
        { bookmark.setText("Bookmark?"); }

        //Get the list of related stories, at the moment this is just regular stories, but in a real world example
        //it could be filtered to other stories in the samecategory, just like how the radio buttons do it
        List<NewsItem> relatedStories = viewModel.getRegularStories();

        //Get and set the adapter, then set the linear layout manager
        TopRecyclerViewAdapter adapter = new TopRecyclerViewAdapter(relatedStories, requireContext());
        relatedRecycler.setAdapter(adapter);
        relatedRecycler.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));
        return view;
    }
}