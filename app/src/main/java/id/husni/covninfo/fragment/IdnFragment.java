package id.husni.covninfo.fragment;


import android.content.Intent;
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
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import id.husni.covninfo.R;
import id.husni.covninfo.activity.IndonesiaProvinceActivity;
import id.husni.covninfo.model.IndonesiaSummaryModel;
import id.husni.covninfo.viewmodel.IndonesiaSummaryViewModel;

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
        FloatingActionButton floatingProvince = view.findViewById(R.id.floatingProvince);
        floatingProvince.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent provinceIntent = new Intent(getContext(), IndonesiaProvinceActivity.class);
                startActivity(provinceIntent);
            }
        });
        PieChart pieChart = view.findViewById(R.id.idnSummaryPie);
        IndonesiaSummaryViewModel viewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(IndonesiaSummaryViewModel.class);
        viewModel.setSummaryData();
        viewModel.getSummaryData().observe(this, new Observer<ArrayList<IndonesiaSummaryModel>>() {
            @Override
            public void onChanged(ArrayList<IndonesiaSummaryModel> indonesiaSummaryModels) {
                IndonesiaSummaryModel model = indonesiaSummaryModels.get(0);
                List<PieEntry> entries = new ArrayList<>();
                entries.add(new PieEntry(Integer.parseInt(model.getPositifIdn()),getResources().getString(R.string.confirmed)));
                entries.add(new PieEntry(Integer.parseInt(model.getSembuhIdn()),getResources().getString(R.string.recovered)));
                entries.add(new PieEntry(Integer.parseInt(model.getMeninggalIdn()),getResources().getString(R.string.deaths)));

                PieDataSet pieDataSet = new PieDataSet(entries,getResources().getString(R.string.from_corona));

                PieData pieData = new PieData(pieDataSet);
                pieDataSet.setColors(ColorTemplate.PASTEL_COLORS);
                pieDataSet.setValueTextColor(Color.WHITE);
                pieDataSet.setValueTextSize(20);

                Legend legend = pieChart.getLegend();
                legend.setTextColor(Color.WHITE);
                legend.setTextSize(13);
                legend.setForm(Legend.LegendForm.CIRCLE);

                Description description = new Description();
                description.setText(getResources().getString(R.string.source_kemenkes));
                description.setTextColor(Color.WHITE);
                description.setTextSize(14);
                description.setPosition(400,1230);

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
