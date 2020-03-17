package id.husni.covninfo.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

import id.husni.covninfo.R;
import id.husni.covninfo.model.WorldSummaryModel;
import id.husni.covninfo.viewmodel.WorldSummaryViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class SummaryFragment extends Fragment {


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
        PieChart pieChart = view.findViewById(R.id.worldSummaryPie);
        WorldSummaryViewModel viewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(WorldSummaryViewModel.class);
        viewModel.setSummaryWorldData();
        viewModel.getSummaryWorldData().observe(this, new Observer<WorldSummaryModel>() {
            @Override
            public void onChanged(WorldSummaryModel worldSummaryModel) {
               //Set data to Chart
                List<PieEntry> entries = new ArrayList<>();
                entries.add(new PieEntry(worldSummaryModel.getConfirmed().getValue(),getResources().getString(R.string.confirmed)));
                entries.add(new PieEntry(worldSummaryModel.getRecovered().getValue(),getResources().getString(R.string.recovered)));
                entries.add(new PieEntry(worldSummaryModel.getDeaths().getValue(),getResources().getString(R.string.deaths)));

                PieDataSet pieDataSet = new PieDataSet(entries, getResources().getString(R.string.from_corona));
                pieDataSet.setColors(ColorTemplate.PASTEL_COLORS);
                pieDataSet.setValueTextColor(Color.WHITE);
                pieDataSet.setValueTextSize(20);

                PieData pieData = new PieData(pieDataSet);

                Description description = new Description();
                description.setText(getResources().getString(R.string.last_update) +" : "+ worldSummaryModel.getLastUpdate());
                description.setTextColor(Color.WHITE);
                description.setTextSize(14);

                Legend legend = pieChart.getLegend();
                legend.setTextColor(Color.WHITE);
                legend.setTextSize(13);
                legend.setForm(Legend.LegendForm.CIRCLE);

                pieChart.setVisibility(View.VISIBLE);
                pieChart.animateXY(2000,2000);
                pieChart.setDescription(description);
                pieChart.setHoleColor(getResources().getColor(R.color.colorPrimaryDark));
                pieChart.setHoleRadius(60);
                pieChart.setData(pieData);
            }
        });
    }
}
