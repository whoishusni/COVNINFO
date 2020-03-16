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
import id.husni.covninfo.model.TodayModel;

public class TodayListAdapter extends RecyclerView.Adapter<TodayListAdapter.ViewHolder> {
    ArrayList<TodayModel> todayModels = new ArrayList<>();
    Context context;

    public TodayListAdapter(Context context) {
        this.context = context;
    }

    public ArrayList<TodayModel> getTodayModels() {
        return todayModels;
    }

    public void setTodayModels(ArrayList<TodayModel> items) {
        if (todayModels != null) {
            if (todayModels.size() > 0) {
                todayModels.clear();
            }
            todayModels.addAll(items);
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TodayListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.today_item_holder,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TodayListAdapter.ViewHolder holder, int position) {
        holder.lastUpdateDate.setText(todayModels.get(position).getLastUpdate());
        holder.tvConfirmed.setText(todayModels.get(position).getConfirmed());
        holder.tvRecovered.setText(todayModels.get(position).getRecovered());
        holder.tvDeath.setText(todayModels.get(position).getDeaths());
        holder.tvListCountry.setText(todayModels.get(position).getCountryRegion());
    }

    @Override
    public int getItemCount() {
        return todayModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView lastUpdateDate;
        public TextView tvConfirmed;
        public TextView tvRecovered;
        public TextView tvDeath;
        public TextView tvListCountry;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            lastUpdateDate = itemView.findViewById(R.id.tvListLastUpdate);
            tvConfirmed = itemView.findViewById(R.id.tvListConfirmed);
            tvRecovered = itemView.findViewById(R.id.tvListRecovered);
            tvDeath = itemView.findViewById(R.id.tvListDeath);
            tvListCountry = itemView.findViewById(R.id.tvListCountry);
        }
    }
}
