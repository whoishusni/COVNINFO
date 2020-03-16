package id.husni.covninfo.service;

import java.lang.annotation.Target;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import id.husni.covninfo.model.TodayModel;
import id.husni.covninfo.utilities.AppUtils;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface ApiEndpoint {
    @GET(AppUtils.END_POINT_WORLD_DAILY)
    Call<List<TodayModel>> getTodayList(@Path("date")String date);
}
