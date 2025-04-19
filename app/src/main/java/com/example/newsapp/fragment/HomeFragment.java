package com.example.newsapp.fragment;

import static android.content.ContentValues.TAG;
import static java.lang.Math.log;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.newsapp.R;
import com.example.newsapp.adapter.NewsAdapter;
import com.example.newsapp.adapter.TopStoriesAdapter;
import com.example.newsapp.model.NewsItem;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    RecyclerView rvTopStories, rvNews;

    public HomeFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        rvTopStories = view.findViewById(R.id.rv_top_stories);
        rvNews = view.findViewById(R.id.rv_news);

        List<NewsItem> topStories = getDummyNews("Top Story",5);
        List<NewsItem> news = getDummyNews("News",10);

        rvTopStories.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        rvNews.setLayoutManager(new GridLayoutManager(getContext(), 2));

        NewsAdapter.OnItemClickListener listener = item -> {
            Fragment detailFragment = DetailFragment.newInstance(item);
            requireActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, detailFragment)
                    .addToBackStack(null)
                    .commit();
        };

        rvTopStories.setAdapter(new TopStoriesAdapter(topStories, listener));
        rvNews.setAdapter(new NewsAdapter(news, listener));

        return view;
    }

    private List<NewsItem> getDummyNews(String prefix, int count) {
        List<NewsItem> list = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            list.add(new NewsItem(
                    prefix + " " + i,
                    "https://yavuzceliker.github.io/sample-images/image-"+i+".jpg",
                    "Description for " + prefix + " " + i
            ));
            Log.d(TAG, "getDummyNews: "+prefix+" "+i);
        }
        return list;
    }
}
