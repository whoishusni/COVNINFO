/*
 * Made With Love
 * Author @Moh Husni Mubaraq
 * Not for Commercial Purpose
 */

package id.husni.covninfo.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import id.husni.covninfo.model.HistoryModel;
import id.husni.covninfo.service.ApiEndpoint;
import id.husni.covninfo.service.RetrofitServiceApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class HistoryViewModel extends ViewModel {
    private final MutableLiveData<ArrayList<HistoryModel>> mutableLiveData = new MutableLiveData<>();

    public void setTodayData() {
        Retrofit retrofit = RetrofitServiceApi.getRetrofitService();
        ApiEndpoint apiEndpoint = retrofit.create(ApiEndpoint.class);

        Call<List<HistoryModel>> call = apiEndpoint.getHistoryList(getFormattedDate());
        call.enqueue(new Callback<List<HistoryModel>>() {
            @Override
            public void onResponse(Call<List<HistoryModel>> call, Response<List<HistoryModel>> response) {
                mutableLiveData.setValue((ArrayList<HistoryModel>) response.body());
            }

            @Override
            public void onFailure(Call<List<HistoryModel>> call, Throwable t) {

            }
        });
    }

    private String getFormattedDate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd-yyyy", Locale.getDefault());
        return simpleDateFormat.format(yesterday());
    }

    // fetch hari kemarin
    private Date yesterday() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE,-1);
        return calendar.getTime();
    }


    public LiveData<ArrayList<HistoryModel>> getTodayListData() {
        return mutableLiveData;
    }
}
