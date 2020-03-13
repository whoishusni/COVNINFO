package id.husni.covninfo;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.interfaces.datasets.IPieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

import id.husni.covninfo.model.ModelData;
import id.husni.covninfo.service.ApiEndpoint;
import id.husni.covninfo.service.RetrofitServiceApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    PieChart summaryChart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        summaryChart = findViewById(R.id.summaryChart);

        Retrofit retrofit = RetrofitServiceApi.getRetrofitService();
        ApiEndpoint endpoint = retrofit.create(ApiEndpoint.class);
        Call<ModelData> call = endpoint.getModelData();
        call.enqueue(new Callback<ModelData>() {
            @Override
            public void onResponse(Call<ModelData> call, Response<ModelData> response) {
                ModelData modelData = response.body();
                List<PieEntry> pieEntries = new ArrayList<>();
                pieEntries.add(new PieEntry(modelData.getConfirmed(),"Confirmed"));
                pieEntries.add(new PieEntry(modelData.getDeath(), "Deaths"));
                pieEntries.add(new PieEntry(modelData.getRecovered(), "Recovered"));

                PieDataSet pieDataSet = new PieDataSet(pieEntries, "From Corona Virus");
                pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
                pieDataSet.setValueTextSize(17);
                pieDataSet.setValueTextColor(Color.WHITE);
                pieDataSet.setValueLineColor(Color.WHITE);

                PieData pieData = new PieData(pieDataSet);

                Description description = new Description();
                description.setText("Real Time Update");
                description.setTextColor(Color.WHITE);
                description.setTextSize(13);

                summaryChart.setVisibility(View.VISIBLE);
                summaryChart.animateXY(2000,2000);
                summaryChart.setDescription(description);
                summaryChart.setData(pieData);

            }

            @Override
            public void onFailure(Call<ModelData> call, Throwable t) {

            }
        });
    }
}
