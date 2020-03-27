/*
 * Made With Love
 * Author @Moh Husni Mubaraq
 * Not for Commercial Purpose
 */

package id.husni.covninfo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import id.husni.covninfo.R;
import id.husni.covninfo.fragment.IdnFragment;
import id.husni.covninfo.fragment.NewsFragment;
import id.husni.covninfo.fragment.SummaryFragment;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Menampilkan Fragment Summary Ketika App Dibuka
        SummaryFragment summaryFragment = new SummaryFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.main_frame,summaryFragment)
                .commit();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNav);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);


    }

    //Menu Navigasi Bawah
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            //Ke Fragment Summary
            case R.id.summaryMenu:
                SummaryFragment summaryFragment = new SummaryFragment();
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_frame,summaryFragment)
                        .commit();
                return true;

            //Ke Fragment Idn
            case R.id.summaryIdnMenu:
                IdnFragment idnFragment = new IdnFragment();
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_frame,idnFragment)
                        .commit();
                return true;

            //ke Fragment News
            case R.id.newsMenu:
                NewsFragment newsFragment = new NewsFragment();
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_frame,newsFragment)
                        .commit();
                return true;
        }
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //menampilkan menu utama
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //Memilih Masing-Masing Menu pada main menu
        switch (item.getItemId()) {
            //Ke activity Info
            case R.id.infoMenu:
                Intent intentInfo = new Intent(this, InfoActivity.class);
                startActivity(intentInfo);
                break;
            //Ke activity Setting
            case R.id.settingMenu:
                Intent intentSetting = new Intent(this, SettingActivity.class);
                startActivity(intentSetting);
                break;
           //Ke activity About
           case R.id.aboutMenu:
                Intent intentAbout = new Intent(this, AboutActivity.class);
                startActivity(intentAbout);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
