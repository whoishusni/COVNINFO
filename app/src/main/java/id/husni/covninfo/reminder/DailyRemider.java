package id.husni.covninfo.reminder;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.SwitchPreference;
import androidx.preference.SwitchPreferenceCompat;

import id.husni.covninfo.R;

public class DailyRemider extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener {
    private String KEYDAILY;
    private SwitchPreference dailySwitchPreference;

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.reminder_preference);
        init();
        summary();
    }

    private void init() {
        KEYDAILY = getResources().getString(R.string.key_daily_reminder);
        dailySwitchPreference = findPreference(KEYDAILY);
    }

    private void summary() {
        SharedPreferences sharedPreferences = getPreferenceManager().getSharedPreferences();
        dailySwitchPreference.setChecked(sharedPreferences.getBoolean(KEYDAILY,false));
    }

    @Override
    public void onResume() {
        super.onResume();
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }

    //TODO : [FUNCTION] BUAT BROADCAST RECEIVER DI METHOD BAWAH
    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (key.equals(KEYDAILY)) {
            if (dailySwitchPreference.isChecked()) {
                //Jika Switch ON
                Toast.makeText(getContext(), "CHECKED", Toast.LENGTH_SHORT).show();
            } else {
                //Jika Switch OFF
                Toast.makeText(getContext(), "UNCHECKED", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
