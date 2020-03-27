/*
 * Made With Love
 * Author @Moh Husni Mubaraq
 * Not for Commercial Purpose
 */

package id.husni.covninfo.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.NumberFormat;
import java.util.Locale;

import id.husni.covninfo.R;
import id.husni.covninfo.activity.WorldHistory;
import id.husni.covninfo.model.WorldSummaryModel;
import id.husni.covninfo.viewmodel.WorldSummaryViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class SummaryFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, View.OnClickListener {

    private SwipeRefreshLayout swipe;
    private TextView tvPositive;
    private TextView tvRecovered;
    private TextView tvDeaths;
    public SummaryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_summary, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        swipe = view.findViewById(R.id.swipeRefreshWorld);
        swipe.setOnRefreshListener(this);
        FloatingActionButton floating = view.findViewById(R.id.floatingWorld);
        floating.setOnClickListener(this);
        tvPositive = view.findViewById(R.id.tvValuePositifWorld);
        tvRecovered = view.findViewById(R.id.tvValueRecoveredWorld);
        tvDeaths = view.findViewById(R.id.tvValueDeathsWorld);
        loadWorldData();
    }

    private void loadWorldData() {
        WorldSummaryViewModel viewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(WorldSummaryViewModel.class);
        viewModel.setSummaryWorldData();
        refreshData(true);
        viewModel.getSummaryWorldData().observe(this, new Observer<WorldSummaryModel>() {
            @Override
            public void onChanged(WorldSummaryModel worldSummaryModel) {
                if (worldSummaryModel != null) {
                    refreshData(false);
                    Locale localeID = new Locale("id","ID");
                    NumberFormat numberFormat = NumberFormat.getInstance(localeID);
                    tvPositive.setText(String.valueOf(numberFormat.format(worldSummaryModel.getConfirmed().getValue())));
                    tvRecovered.setText(String.valueOf(numberFormat.format(worldSummaryModel.getRecovered().getValue())));
                    tvDeaths.setText(String.valueOf(numberFormat.format(worldSummaryModel.getDeaths().getValue())));
                }
            }
        });
    }

    private void refreshData(boolean isRefresh) {
        if (isRefresh) {
            swipe.setRefreshing(true);
        } else {
            swipe.setRefreshing(false);
        }
    }

    @Override
    public void onRefresh() {
        loadWorldData();
    }

    @Override
    public void onClick(View view) {
        Intent intentWorldHistory = new Intent(getContext(), WorldHistory.class);
        startActivity(intentWorldHistory);
    }
}
