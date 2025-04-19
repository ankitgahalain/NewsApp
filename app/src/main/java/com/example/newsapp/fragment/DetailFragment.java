package com.example.newsapp.fragment;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.newsapp.R;
import com.example.newsapp.adapter.NewsAdapter;
import com.example.newsapp.model.NewsItem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class DetailFragment extends Fragment {

    private static final String ARG_TITLE = "arg_title";
    private static final String ARG_DESC = "arg_desc";
    private static final String ARG_IMAGE = "arg_image";

    private String title, description, imageUrl;

    public DetailFragment() {}

    public static DetailFragment newInstance(NewsItem item) {
        DetailFragment fragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TITLE, item.getTitle());
        args.putString(ARG_DESC, item.getDescription());
        args.putString(ARG_IMAGE, item.getImageUrl());
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            title = getArguments().getString(ARG_TITLE);
            description = getArguments().getString(ARG_DESC);
            imageUrl = getArguments().getString(ARG_IMAGE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageView imageView = view.findViewById(R.id.image_detail);
        TextView descriptionView = view.findViewById(R.id.text_description);
        RecyclerView relatedRecyclerView = view.findViewById(R.id.rv_related);

        Picasso.get().load(imageUrl).into(imageView);
        descriptionView.setText(description);

        List<NewsItem> relatedNews = getDummyRelatedNews(title, 4);

        relatedRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        relatedRecyclerView.setAdapter(new NewsAdapter(relatedNews, item -> {
            // Navigate to another DetailFragment if needed
            Fragment newFragment = DetailFragment.newInstance(item);
            requireActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, newFragment)
                    .addToBackStack(null)
                    .commit();
        }));
    }

    private List<NewsItem> getDummyRelatedNews(String baseTitle, int count) {
        List<NewsItem> list = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            list.add(new NewsItem(
                    baseTitle + " - Related " + i,
                    "https://yavuzceliker.github.io/sample-images/image-"+(i+20)+".jpg",
                    "This is related news item " + i
            ));
        }
        return list;
    }
}
