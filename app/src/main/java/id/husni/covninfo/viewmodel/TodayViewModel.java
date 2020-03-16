package id.husni.covninfo.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import id.husni.covninfo.model.TodayModel;
import id.husni.covninfo.service.ApiEndpoint;
import id.husni.covninfo.service.RetrofitServiceApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class TodayViewModel extends ViewModel {
    MutableLiveData<ArrayList<TodayModel>> mutableLiveData = new MutableLiveData<>();

    public void setTodayData() {
        Retrofit retrofit = RetrofitServiceApi.getRetrofitService();
        ApiEndpoint apiEndpoint = retrofit.create(ApiEndpoint.class);

        Call<List<TodayModel>> call = apiEndpoint.getTodayList(getFormattedDate());
        call.enqueue(new Callback<List<TodayModel>>() {
            @Override
            public void onResponse(Call<List<TodayModel>> call, Response<List<TodayModel>> response) {
                mutableLiveData.setValue((ArrayList<TodayModel>) response.body());
            }

            @Override
            public void onFailure(Call<List<TodayModel>> call, Throwable t) {

            }
        });
    }

    private String getFormattedDate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd-yyyy", Locale.getDefault());
        Date date = new Date();
        return simpleDateFormat.format(date);
    }

    public LiveData<ArrayList<TodayModel>> getTodayListData() {
        return mutableLiveData;
    }
}
