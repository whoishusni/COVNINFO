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
import android.widget.Toast;

import id.husni.covninfo.R;

public class InfoActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(R.string.info);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        Button btnHospital = findViewById(R.id.btnInfoHospital);
        Button btnSymptom = findViewById(R.id.btnInfoSymptom);
        Button btnPrevention = findViewById(R.id.btnInfoPrevention);
        Button btnAdviceWho = findViewById(R.id.btnInfoAdviceWho);

        btnHospital.setOnClickListener(this);
        btnSymptom.setOnClickListener(this);
        btnPrevention.setOnClickListener(this);
        btnAdviceWho.setOnClickListener(this);
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
        switch (v.getId()) {
            case R.id.btnInfoHospital:
                String hospitalUrl = "https://news.detik.com/berita/d-4942353/daftar-rumah-sakit-rujukan-covid-19-seluruh-indonesia?single=1";
                Uri hospitalUri = Uri.parse(hospitalUrl);
                Intent intentInfoHospital = new Intent(Intent.ACTION_VIEW, hospitalUri);
                Toast.makeText(this, getResources().getString(R.string.redirect), Toast.LENGTH_SHORT).show();
                startActivity(intentInfoHospital);
                break;

            case R.id.btnInfoSymptom:
                String symptomUrl = "https://www.cnnindonesia.com/gaya-hidup/20200128205625-258-469589/infografis-bedanya-demam-selesma-dan-virus-corona-wuhan";
                Uri symptomUri = Uri.parse(symptomUrl);
                Intent intentInfoSymptom = new Intent(Intent.ACTION_VIEW, symptomUri);
                Toast.makeText(this, getResources().getString(R.string.redirect), Toast.LENGTH_SHORT).show();
                startActivity(intentInfoSymptom);
                break;

            case R.id.btnInfoPrevention:
                String urlPrevention = "https://www.kompas.com/sains/read/2020/03/15/190200123/panduan-mencegah-virus-corona-pesan-who-untuk-kita-semua?page=all#page4";
                Uri preventionUri = Uri.parse(urlPrevention);
                Intent intentPrevention = new Intent(Intent.ACTION_VIEW, preventionUri);
                Toast.makeText(this, getResources().getString(R.string.redirect), Toast.LENGTH_SHORT).show();
                startActivity(intentPrevention);
                break;

            case R.id.btnInfoAdviceWho:
                String urlWho = "https://www.youtube.com/watch?v=bPITHEiFWLc&feature=emb_title";
                Uri whoUri = Uri.parse(urlWho);
                Intent intentWho = new Intent(Intent.ACTION_VIEW, whoUri);
                Toast.makeText(this, getResources().getString(R.string.redirect), Toast.LENGTH_SHORT).show();
                startActivity(intentWho);
                break;
        }
    }
}
