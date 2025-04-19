package com.example.newsapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsapp.R;
import com.example.newsapp.model.NewsItem;
import com.squareup.picasso.Picasso;

import java.util.List;

public class TopStoriesAdapter extends RecyclerView.Adapter<TopStoriesAdapter.TopStoryViewHolder> {

    private List<NewsItem> topStories;
    private NewsAdapter.OnItemClickListener listener;

    public TopStoriesAdapter(List<NewsItem> topStories, NewsAdapter.OnItemClickListener listener) {
        this.topStories = topStories;
        this.listener = listener;
    }

    @NonNull
    @Override
    public TopStoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_top_story, parent, false);
        return new TopStoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TopStoryViewHolder holder, int position) {
        NewsItem item = topStories.get(position);
        Picasso.get().load(item.getImageUrl()).into(holder.imageView);

        holder.itemView.setOnClickListener(v -> listener.onItemClick(item));
    }

    @Override
    public int getItemCount() {
        return topStories.size();
    }

    static class TopStoryViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public TopStoryViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_top_story);
        }
    }
}
