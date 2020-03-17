package id.husni.covninfo.service;

import java.util.List;

import id.husni.covninfo.model.HistoryModel;
import id.husni.covninfo.model.WorldSummaryModel;
import id.husni.covninfo.utilities.AppUtils;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiEndpoint {
    @GET(AppUtils.END_POINT_WORLD_HISTORY)
    Call<List<HistoryModel>> getHistoryList(@Path("date")String date);

    @GET(AppUtils.END_POINT_SUMMARY_WORLD)
    Call<WorldSummaryModel> getSummaryWorld();
}
