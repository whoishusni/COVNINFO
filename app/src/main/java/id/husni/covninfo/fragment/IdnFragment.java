package id.husni.covninfo.fragment;


import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
import id.husni.covninfo.model.IdnSummaryModel;
import id.husni.covninfo.viewmodel.IdnSummaryViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class IdnFragment extends Fragment {


    public IdnFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_idn, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        PieChart pieChart = view.findViewById(R.id.idnSummaryPie);
        IdnSummaryViewModel viewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(IdnSummaryViewModel.class);
        viewModel.setSummaryIdnData();
        viewModel.getSummaryIdnData().observe(this, new Observer<IdnSummaryModel>() {
            @Override
            public void onChanged(IdnSummaryModel idnSummaryModel) {
                List<PieEntry> pieEntries = new ArrayList<>();
                pieEntries.add(new PieEntry(idnSummaryModel.getIdnConfirmed().getValue(),getResources().getString(R.string.confirmed)));
                pieEntries.add(new PieEntry(idnSummaryModel.getIdnRecovered().getValue(),getResources().getString(R.string.recovered)));
                pieEntries.add(new PieEntry(idnSummaryModel.getIdnDeaths().getValue(), getResources().getString(R.string.deaths)));

                PieDataSet pieDataSet = new PieDataSet(pieEntries, getResources().getString(R.string.from_corona));
                pieDataSet.setColors(ColorTemplate.PASTEL_COLORS);
                pieDataSet.setValueTextColor(Color.WHITE);
                pieDataSet.setValueTextSize(20);

                Legend legend = pieChart.getLegend();
                legend.setTextColor(Color.WHITE);
                legend.setTextSize(13);
                legend.setForm(Legend.LegendForm.CIRCLE);

                Description description = new Description();
                description.setText(getResources().getString(R.string.last_update)+" : "+idnSummaryModel.getLastUpdate());
                description.setTextColor(Color.WHITE);
                description.setTextSize(14);

                PieData pieData = new PieData(pieDataSet);
                pieChart.setVisibility(View.VISIBLE);
                pieChart.animateXY(2000,2000);
                pieChart.setDescription(description);
                pieChart.setHoleRadius(60);
                pieChart.setHoleColor(getResources().getColor(R.color.colorPrimaryDark));
                pieChart.setData(pieData);
            }
        });
    }
}
