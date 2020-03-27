
/*
 * Made With Love
 * Author @Moh Husni Mubaraq
 * Not for Commercial Purpose
 */

package id.husni.covninfo.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import id.husni.covninfo.R;
import id.husni.covninfo.adapter.IndonProvinceListAdapter;
import id.husni.covninfo.model.IndonesiaProvinsiModel;
import id.husni.covninfo.viewmodel.IndonesiaProvinceViewModel;

public class IndonesiaProvinceActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    private IndonProvinceListAdapter adapter;
    private SwipeRefreshLayout swipeRefreshProvince;
    private TextView tvEmpty;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indonesia_province);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(R.string.province);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        tvEmpty = findViewById(R.id.tvEmptyListProvince);
        RecyclerView recyclerView = findViewById(R.id.provinceRecycler);
        adapter = new IndonProvinceListAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        swipeRefreshProvince = findViewById(R.id.swipeRefreshProvince);
        swipeRefreshProvince.setOnRefreshListener(this);

        loadProvinceData();

    }
    private void loadProvinceData() {
        IndonesiaProvinceViewModel viewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(IndonesiaProvinceViewModel.class);
        viewModel.setProvinceData();
        refreshing(true);
        viewModel.getProvinceData().observe(this, new Observer<ArrayList<IndonesiaProvinsiModel>>() {
            @Override
            public void onChanged(ArrayList<IndonesiaProvinsiModel> indonesiaProvinsiModels) {
                if (indonesiaProvinsiModels == null) {
                    tvEmpty.setVisibility(View.VISIBLE);
                    refreshing(false);
                } else {
                    adapter.setIndoList(indonesiaProvinsiModels);
                    refreshing(false);
                }

            }
        });
    }

    private void refreshing(boolean isRefreshing) {
        if (isRefreshing) {
            swipeRefreshProvince.setRefreshing(true);
        } else {
            swipeRefreshProvince.setRefreshing(false);
        }
    }

    @Override
    public void onRefresh() {
        loadProvinceData();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
