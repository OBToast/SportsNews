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
import androidx.annotation.Nullable;
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

        View view = inflater.inflate(R.layout.fragment_detail, container, false);


        viewModel = new ViewModelProvider(requireActivity()).get(NewsViewModel.class);


        NewsItem selected = viewModel.getSelectedArticle();

        TextView title = view.findViewById(R.id.titleTextView);
        ImageView image = view.findViewById(R.id.imageView);
        TextView description = view.findViewById(R.id.descriptionTextView);
        Button back = view.findViewById(R.id.backButton);
        back.setOnClickListener(v -> {
            requireActivity().getSupportFragmentManager().popBackStack();
        });
        Button bookmark = view.findViewById(R.id.bookmarkButton);
        bookmark.setOnClickListener(v -> {
            viewModel.toggleBookmarkSelected();
            if (selected.bookmarked == true)
            {Toast.makeText(requireContext(), "Bookmarked!", Toast.LENGTH_SHORT).show();}
            else
            {Toast.makeText(requireContext(), "Removed bookmark", Toast.LENGTH_SHORT).show();}
        });


        if (selected != null) {
            title.setText(selected.GetTitle());
            image.setImageResource(selected.GetImage());
            description.setText(selected.GetDescription());
            if (selected.bookmarked == true) {
                bookmark.setText("Bookmarked");
            }
            else
            {bookmark.setText("Bookmark?");}
        }

        RecyclerView relatedRecycler = view.findViewById(R.id.recyclerView);

        if (relatedRecycler != null) {
            List<NewsItem> relatedStories = viewModel.getRegularStories();

            RecyclerViewAdapter adapter =
                    new RecyclerViewAdapter(relatedStories, requireContext());

            relatedRecycler.setAdapter(adapter);
            relatedRecycler.setLayoutManager(
                    new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            );
        }

        return view;
    }
}