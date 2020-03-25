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
import id.husni.covninfo.adapter.HistoryListAdapter;
import id.husni.covninfo.model.HistoryModel;
import id.husni.covninfo.viewmodel.HistoryViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class HistoryFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private HistoryListAdapter adapter;
    private TextView tvEmptyList;
    private SwipeRefreshLayout swipeRefreshLayout;
    public HistoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_history, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvEmptyList = view.findViewById(R.id.tvEmptyList);

        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshList);
        RecyclerView recyclerView = view.findViewById(R.id.listRecycler);

        adapter = new HistoryListAdapter(getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        loadListData();

        swipeRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    public void onRefresh() {
        loadListData();
    }

    private void loadListData() {
        HistoryViewModel viewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(HistoryViewModel.class);
        viewModel.setTodayData();
        refreshingData(true);
        viewModel.getTodayListData().observe(this, new Observer<ArrayList<HistoryModel>>() {
            @Override
            public void onChanged(ArrayList<HistoryModel> historyModels) {
                if (historyModels == null) {
                    tvEmptyList.setVisibility(View.VISIBLE);
                    refreshingData(false);
                } else {
                    adapter.setHistoryModels(historyModels);
                    refreshingData(false);
                }
            }
        });
    }

    private void refreshingData(boolean b) {
        if (b) {
            swipeRefreshLayout.setRefreshing(true);
        } else {
            swipeRefreshLayout.setRefreshing(false);
        }
    }
}
