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
import id.husni.covninfo.model.IndonesiaProvinsiModel;

public class IndonProvinceListAdapter extends RecyclerView.Adapter<IndonProvinceListAdapter.ViewHolder> {
    private final ArrayList<IndonesiaProvinsiModel> indoList = new ArrayList<>();
    private final Context context;

    public IndonProvinceListAdapter(Context context) {
        this.context = context;
    }

    public ArrayList<IndonesiaProvinsiModel> getIndoList() {
        return indoList;
    }

    public void setIndoList(ArrayList<IndonesiaProvinsiModel> indoItem) {
        if (indoList != null) {
            if (indoList.size() > 0) {
                indoList.clear();
            }
            indoList.addAll(indoItem);
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public IndonProvinceListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.indonesia_province_item_holder,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IndonProvinceListAdapter.ViewHolder holder, int position) {
        holder.tvConfirmed.setText(String.valueOf(indoList.get(position).getAttributesProv().getConfirmed()));
        holder.tvRecovered.setText(String.valueOf(indoList.get(position).getAttributesProv().getRecovered()));
        holder.tvDeath.setText(String.valueOf(indoList.get(position).getAttributesProv().getDead()));
        holder.tvProvince.setText(indoList.get(position).getAttributesProv().getProvinsi());
    }

    @Override
    public int getItemCount() {
        return indoList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView tvConfirmed;
        final TextView tvRecovered;
        final TextView tvDeath;
        final TextView tvProvince;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvConfirmed = itemView.findViewById(R.id.tvProvConfirmed);
            tvRecovered = itemView.findViewById(R.id.tvProvRecovered);
            tvDeath = itemView.findViewById(R.id.tvProvDeath);
            tvProvince = itemView.findViewById(R.id.tvProvProvince);
        }
    }
}
