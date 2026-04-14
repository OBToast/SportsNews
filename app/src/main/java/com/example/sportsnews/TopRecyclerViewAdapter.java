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

public class TopRecyclerViewAdapter extends RecyclerView.Adapter<TopRecyclerViewAdapter.ViewHolder>{

    //This recyclerviewadapter is for the top articles, which dont have a description and as such need to be handled slightly differnetly
    private List<NewsItem> newsList;
    private Context context;
    public TopRecyclerViewAdapter(List<NewsItem> newsList, Context context)
    {
        this.newsList = newsList;
        this.context = context;
    }

    @NonNull
    @Override
    public TopRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Inflate the view
        View itemView = LayoutInflater.from(context).inflate(R.layout.news_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TopRecyclerViewAdapter.ViewHolder holder, int position) {
        //Get the fields for the title, image, and description
        holder.titleTextView.setText(newsList.get(position).GetTitle());
        holder.imageView.setImageResource(newsList.get(position).GetImage());

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
    //Technically we never filter the top articles, but Im going to leave this here anyway just in case theres ever future expansion
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
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //Set the fields to the UI elements
            titleTextView = itemView.findViewById(R.id.titleTextView);
            imageView = itemView.findViewById(R.id.ImageView);

        }
    }
}
