/*
 * Made With Love
 * Author @Moh Husni Mubaraq
 * Not for Commercial Purpose
 */

package id.husni.covninfo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import id.husni.covninfo.R;
import id.husni.covninfo.model.HistoryModel;

public class HistoryListAdapter extends RecyclerView.Adapter<HistoryListAdapter.ViewHolder> {
    private final ArrayList<HistoryModel> historyModels = new ArrayList<>();
    private final Context context;

    public HistoryListAdapter(Context context) {
        this.context = context;
    }

    public ArrayList<HistoryModel> getHistoryModels() {
        return historyModels;
    }

    public void setHistoryModels(ArrayList<HistoryModel> items) {
        if (historyModels != null) {
            if (historyModels.size() > 0) {
                historyModels.clear();
            }
            historyModels.addAll(items);
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public HistoryListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.history_item_holder,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryListAdapter.ViewHolder holder, int position) {
        holder.lastUpdateDate.setText(historyModels.get(position).getLastUpdate());
        holder.tvConfirmed.setText(historyModels.get(position).getConfirmed());
        holder.tvRecovered.setText(historyModels.get(position).getRecovered());
        holder.tvDeath.setText(historyModels.get(position).getDeaths());
        holder.tvListCountry.setText(historyModels.get(position).getCountryRegion());
    }

    @Override
    public int getItemCount() {
        return historyModels.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView lastUpdateDate;
        final TextView tvConfirmed;
        final TextView tvRecovered;
        final TextView tvDeath;
        final TextView tvListCountry;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            lastUpdateDate = itemView.findViewById(R.id.tvListLastUpdate);
            tvConfirmed = itemView.findViewById(R.id.tvListConfirmed);
            tvRecovered = itemView.findViewById(R.id.tvListRecovered);
            tvDeath = itemView.findViewById(R.id.tvListDeath);
            tvListCountry = itemView.findViewById(R.id.tvListCountry);
        }
    }
}
