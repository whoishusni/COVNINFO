package id.husni.covninfo.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import id.husni.covninfo.model.IdnSummaryModel;
import id.husni.covninfo.service.ApiEndpoint;
import id.husni.covninfo.service.RetrofitServiceApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class IdnSummaryViewModel extends ViewModel {
    private MutableLiveData<IdnSummaryModel> mutableLiveData = new MutableLiveData<>();

    public void setSummaryIdnData() {
        Retrofit retrofit = RetrofitServiceApi.getRetrofitService();
        ApiEndpoint apiEndpoint = retrofit.create(ApiEndpoint.class);
        Call<IdnSummaryModel> call = apiEndpoint.getSummaryIdn();
        call.enqueue(new Callback<IdnSummaryModel>() {
            @Override
            public void onResponse(Call<IdnSummaryModel> call, Response<IdnSummaryModel> response) {
                mutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<IdnSummaryModel> call, Throwable t) {

            }
        });
    }

    public LiveData<IdnSummaryModel> getSummaryIdnData() {
        return mutableLiveData;
    }
}
