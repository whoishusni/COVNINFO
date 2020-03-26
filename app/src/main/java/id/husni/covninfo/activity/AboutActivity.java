
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
import android.widget.TextView;

import id.husni.covninfo.BuildConfig;
import id.husni.covninfo.R;

public class AboutActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(R.string.about);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        String versionApp = BuildConfig.VERSION_NAME;
        TextView tvVersion = findViewById(R.id.tvVersion);
        tvVersion.setText(versionApp);
        Button btnGithub = findViewById(R.id.githubBtn);
        btnGithub.setOnClickListener(this);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.githubBtn) {
            String url = "https://www.github.com/whoishusni";
            Uri parseUrl = Uri.parse(url);
            Intent intent = new Intent(Intent.ACTION_VIEW, parseUrl);
            startActivity(intent);
        }
    }
}
