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
import android.widget.Toast;

import java.util.ArrayList;

import id.husni.covninfo.R;
import id.husni.covninfo.adapter.TodayListAdapter;
import id.husni.covninfo.model.TodayModel;
import id.husni.covninfo.viewmodel.TodayViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class TodayFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private TodayListAdapter adapter;
    private TextView tvEmptyList;
    private SwipeRefreshLayout swipeRefreshLayout;
    public TodayFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_today, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvEmptyList = view.findViewById(R.id.tvEmptyList);

        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshList);
        RecyclerView recyclerView = view.findViewById(R.id.listRecycler);

        adapter = new TodayListAdapter(getContext());
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
        TodayViewModel viewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(TodayViewModel.class);
        viewModel.setTodayData();
        swipeRefreshLayout.setRefreshing(true);
        viewModel.getTodayListData().observe(this, new Observer<ArrayList<TodayModel>>() {
            @Override
            public void onChanged(ArrayList<TodayModel> todayModels) {
                if (todayModels == null) {
                    tvEmptyList.setVisibility(View.VISIBLE);
                    swipeRefreshLayout.setRefreshing(false);
                } else {
                    adapter.setTodayModels(todayModels);
                    swipeRefreshLayout.setRefreshing(false);
                }
            }
        });
    }
}
