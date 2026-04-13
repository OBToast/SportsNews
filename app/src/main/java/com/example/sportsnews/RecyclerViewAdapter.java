package com.example.sportsnews;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    private List<NewsItem> newsList;
    private Context context;

    public RecyclerViewAdapter(List<NewsItem> newsList, Context context)
    {
        this.newsList = newsList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.news_item, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
        holder.titleTextView.setText(newsList.get(position).GetTitle());
        holder.imageView.setImageResource(newsList.get(position).GetImage());
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;
        ImageView imageView;
        TextView descriptionTextView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            imageView = itemView.findViewById(R.id.ImageView);

            if (itemView.findViewById(R.id.descriptionTextView) != null) {
                descriptionTextView = itemView.findViewById(R.id.descriptionTextView);
            }
        }
    }
}
