package com.example.sportsnews;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RegularRecyclerViewAdapter extends RecyclerView.Adapter<RegularRecyclerViewAdapter.ViewHolder>{

    //This recycler view adapater is for the regular stories, not the top stories, as such it includes the description
    private List<NewsItem> newsList;
    private Context context;
    public RegularRecyclerViewAdapter(List<NewsItem> newsList, Context context)
    {
        this.newsList = newsList;
        this.context = context;
    }

    @NonNull
    @Override
    public RegularRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Inflate the view
        View itemView = LayoutInflater.from(context).inflate(R.layout.news_item_details, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RegularRecyclerViewAdapter.ViewHolder holder, int position) {
        //Get the fields for the title, image, and description
        holder.titleTextView.setText(newsList.get(position).GetTitle());
        holder.imageView.setImageResource(newsList.get(position).GetImage());
        holder.descriptionTextView.setText(newsList.get(position).GetDescription());

        //Set the images to have an onclick listener to open the detail fragment
        holder.imageView.setOnClickListener(v -> {
            NewsViewModel viewModel = new ViewModelProvider((AppCompatActivity) context).get(NewsViewModel.class);

            //Get the article they selected
            viewModel.setSelectedArticle(newsList.get(position));

            //Transition the fragment container to the detailed fragment view for that article. Add to back stack so the back button works
            ((AppCompatActivity) context).getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragmentContainerView, new DetailFragment())
                    .addToBackStack(null)
                    .commit();
        });
    }

    //Function for updating the list so the radio buttons can work for filtering articles
    public void updateList(List<NewsItem> newList) {
        this.newsList = newList;
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return newsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        //Create the fields for the UI elements
        TextView titleTextView;
        ImageView imageView;
        TextView descriptionTextView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //Set the fields to the UI elements
            titleTextView = itemView.findViewById(R.id.titleTextView);
            imageView = itemView.findViewById(R.id.ImageView);
            descriptionTextView = itemView.findViewById(R.id.descriptionTextView);
        }
    }
}
