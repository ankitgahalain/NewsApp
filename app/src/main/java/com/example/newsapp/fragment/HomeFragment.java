package com.example.newsapp.fragment;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.newsapp.R;
import com.example.newsapp.adapter.NewsAdapter;
import com.example.newsapp.adapter.TopStoriesAdapter;
import com.example.newsapp.model.NewsItem;
import java.util.Random;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    RecyclerView rvTopStories, rvNews;
    ImageButton btnScrollLeft, btnScrollRight;

    public HomeFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        btnScrollLeft = view.findViewById(R.id.btn_scroll_left);
        btnScrollRight = view.findViewById(R.id.btn_scroll_right);

        rvTopStories = view.findViewById(R.id.rv_top_stories);
        rvNews = view.findViewById(R.id.rv_news);

        List<NewsItem> topStories = getDummyNews("Top Story",5);
        List<NewsItem> news = getDummyNews("News",10);

        rvTopStories.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        rvNews.setLayoutManager(new GridLayoutManager(getContext(), 2, RecyclerView.HORIZONTAL, false));

        NewsAdapter.OnItemClickListener listener = item -> {
            Fragment detailFragment = DetailFragment.newInstance(item);
            requireActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, detailFragment)
                    .addToBackStack(null)
                    .commit();
        };

        rvTopStories.setAdapter(new TopStoriesAdapter(topStories, listener));
        rvNews.setAdapter(new NewsAdapter(news, listener));

        btnScrollLeft.setOnClickListener(v -> {
            LinearLayoutManager layoutManager = (LinearLayoutManager) rvTopStories.getLayoutManager();
            layoutManager.smoothScrollToPosition(rvTopStories, new RecyclerView.State(), Math.max(0, layoutManager.findFirstVisibleItemPosition() - 1));
        });

        btnScrollRight.setOnClickListener(v -> {
            LinearLayoutManager layoutManager = (LinearLayoutManager) rvTopStories.getLayoutManager();
            layoutManager.smoothScrollToPosition(rvTopStories, new RecyclerView.State(), layoutManager.findLastVisibleItemPosition() + 1);
        });

        return view;
    }

    private List<NewsItem> getDummyNews(String prefix, int count) {
        List<NewsItem> list = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            list.add(new NewsItem(
                    prefix + " " + i,
                    "https://yavuzceliker.github.io/sample-images/image-" + new Random().nextInt(500) + ".jpg",
                    "Description for " + prefix + " " + i
            ));
            Log.d("getDummyNews", "getDummyNews: "+prefix+" "+i);
        }
        return list;
    }
}
