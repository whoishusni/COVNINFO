/*
 * Made With Love
 * Author @Moh Husni Mubaraq
 * Not for Commercial Purpose
 */

package id.husni.covninfo.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import id.husni.covninfo.R;
import id.husni.covninfo.adapter.NewsAdapter;
import id.husni.covninfo.model.news.NewsModel;
import id.husni.covninfo.viewmodel.NewsViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    private SwipeRefreshLayout swipe;
    private NewsAdapter adapter;
    private TextView tvEmpty;
    public NewsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvEmpty = view.findViewById(R.id.tvEmptyListNews);
        swipe = view.findViewById(R.id.swipeRefreshNews);
        RecyclerView rv = view.findViewById(R.id.newsRecycler);
        adapter = new NewsAdapter(getContext());
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv.setAdapter(adapter);

        loadNewsData();
    }

    private void loadNewsData() {
        NewsViewModel viewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(NewsViewModel.class);
        viewModel.setNewsData();
        refreshNews(true);
        viewModel.getNewsData().observe(this, new Observer<ArrayList<NewsModel>>() {
            @Override
            public void onChanged(ArrayList<NewsModel> newsModels) {
                if (newsModels == null) {
                    tvEmpty.setVisibility(View.VISIBLE);
                    refreshNews(false);
                } else {
                    adapter.setNewsModels(newsModels);
                    refreshNews(false);
                }
            }
        });
        swipe.setOnRefreshListener(this);
    }

    private void refreshNews(boolean isRefresh) {
        if (isRefresh) {
            swipe.setRefreshing(true);
        } else {
            swipe.setRefreshing(false);
        }
    }

    @Override
    public void onRefresh() {
        loadNewsData();
    }
}
