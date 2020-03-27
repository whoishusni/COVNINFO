/*
 * Made With Love
 * Author @Moh Husni Mubaraq
 * Not for Commercial Purpose
 */

package id.husni.covninfo.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import id.husni.covninfo.R;
import id.husni.covninfo.model.news.NewsModel;

public class NewsDetail extends AppCompatActivity {

    public static final String PARCELABLE_PARSING_DATA = "parcelable_parsing_data" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(R.string.news);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        TextView tvTitle = findViewById(R.id.tvNewsTitleDetail);
        TextView tvSource = findViewById(R.id.tvNewsSourceDetail);
        TextView tvAuthor = findViewById(R.id.tvNewsAuthorDetail);
        TextView tvPublished = findViewById(R.id.tvNewsPublishedDetail);
        TextView tvDesc = findViewById(R.id.tvNewsDescDetail);
        TextView tvContent = findViewById(R.id.tvNewsContentDetail);

        ImageView imageView = findViewById(R.id.imageDetailNews);

        Button btnMore = findViewById(R.id.btnMore);

        NewsModel model = getIntent().getParcelableExtra(PARCELABLE_PARSING_DATA);

        tvTitle.setText(model.getTitle());
        tvSource.setText(String.format("%s %s", getResources().getString(R.string.sourceNews), model.getSource().getName()));
        if (model.getAuthor() == null) {
            tvAuthor.setText(R.string.editor);
        } else {
            tvAuthor.setText(String.format("%s %s", getResources().getString(R.string.authorNews), model.getAuthor()));
        }
        tvPublished.setText(String.format("%s %s", getResources().getString(R.string.dateNews), model.getPublishedAt()));
        tvDesc.setText(model.getDescription());

        if (model.getContent() == null) {
            tvContent.setText(getResources().getString(R.string.description_unavailable));
        } else {
            tvContent.setText(model.getContent());
        }
        Glide.with(this)
                .load(model.getUrlToImage())
                .into(imageView);

        btnMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String URL = model.getUrl();
                Uri urlUri = Uri.parse(URL);
                Intent toUrl = new Intent(Intent.ACTION_VIEW, urlUri);
                startActivity(toUrl);
                Toast.makeText(NewsDetail.this, getResources().getString(R.string.redirect), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
