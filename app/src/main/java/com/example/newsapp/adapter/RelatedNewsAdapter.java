package com.example.newsapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsapp.R;
import com.example.newsapp.model.NewsItem;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RelatedNewsAdapter extends RecyclerView.Adapter<RelatedNewsAdapter.RelatedNewsViewHolder> {

    private List<NewsItem> relatedNewsList;

    private NewsAdapter.OnItemClickListener listener;

    public RelatedNewsAdapter(List<NewsItem> relatedNewsList, NewsAdapter.OnItemClickListener listener) {
        this.relatedNewsList = relatedNewsList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RelatedNewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_related_news, parent, false);
        return new RelatedNewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RelatedNewsViewHolder holder, int position) {
        NewsItem item = relatedNewsList.get(position);
        holder.bind(item, listener);
    }

    @Override
    public int getItemCount() {
        return relatedNewsList.size();
    }

    class RelatedNewsViewHolder extends RecyclerView.ViewHolder {
        ImageView imageNews;
        TextView titleNews;
        TextView descriptionNews;

        public RelatedNewsViewHolder(@NonNull View itemView) {
            super(itemView);
            imageNews = itemView.findViewById(R.id.image_news);
            titleNews = itemView.findViewById(R.id.title_news);
            descriptionNews = itemView.findViewById(R.id.description_news);
        }

        public void bind(final NewsItem item, NewsAdapter.OnItemClickListener listener) {
            titleNews.setText(item.getTitle());
            descriptionNews.setText(item.getDescription());

            Picasso.get()
                    .load(item.getImageUrl())
                    .placeholder(R.drawable.placeholder)
                    .error(R.drawable.image_error)
                    .into(imageNews);


            itemView.setOnClickListener(v -> RelatedNewsAdapter.this.listener.onItemClick(item));
        }
    }
}

