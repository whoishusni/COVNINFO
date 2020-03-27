/*
 * Made With Love
 * Author @Moh Husni Mubaraq
 * Not for Commercial Purpose
 */

package id.husni.covninfo.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import id.husni.covninfo.R;
import id.husni.covninfo.activity.NewsDetail;
import id.husni.covninfo.model.news.NewsModel;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    private final ArrayList<NewsModel> newsModels = new ArrayList<>();
    private final Context context;

    public NewsAdapter(Context context) {
        this.context = context;
    }

    public ArrayList<NewsModel> getNewsModels() {
        return newsModels;
    }

    public void setNewsModels(ArrayList<NewsModel> itemModels) {
        if (newsModels != null) {
            if (newsModels.size() > 0) {
                newsModels.clear();
            }
            newsModels.addAll(itemModels);
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NewsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.news_item_holder,parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsAdapter.ViewHolder holder, int position) {
    holder.tvTitle.setText(newsModels.get(position).getTitle());
    holder.tvSource.setText(newsModels.get(position).getSource().getName());
        Glide.with(context)
                .load(newsModels.get(position).getUrlToImage())
                .into(holder.imageNews);

    holder.cvNews.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intentDetail = new Intent(context, NewsDetail.class);
            intentDetail.putExtra(NewsDetail.PARCELABLE_PARSING_DATA,newsModels.get(position));
            context.startActivity(intentDetail);
        }
    });
    }

    @Override
    public int getItemCount() {
        return newsModels.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView tvTitle;
        final TextView tvSource;
        final ImageView imageNews;
        final CardView cvNews;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitleNews);
            tvSource = itemView.findViewById(R.id.tvSourceNews);
            imageNews = itemView.findViewById(R.id.imageNews);
            cvNews = itemView.findViewById(R.id.newsCardView);
        }
    }
}
