package id.husni.covninfo.service;

import id.husni.covninfo.utilities.AppUtils;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitServiceApi {
    public static Retrofit getRetrofitService(){
        return new Retrofit.Builder()
                .baseUrl(AppUtils.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
