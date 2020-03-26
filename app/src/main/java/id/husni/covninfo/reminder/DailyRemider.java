/*
 * Made With Love
 * Author @Moh Husni Mubaraq
 * Not for Commercial Purpose
 */

package id.husni.covninfo.reminder;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.SwitchPreference;

import id.husni.covninfo.R;
import id.husni.covninfo.receiver.DailyReceiver;

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

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        DailyReceiver dailyReceiver = new DailyReceiver();
        if (key.equals(KEYDAILY)) {
            if (dailySwitchPreference.isChecked()) {
                //Jika Switch ON
                dailyReceiver.setDailyNotif(getContext());
            } else {
                //Jika Switch OFF
                dailyReceiver.setCancelDailyNotif(getContext());
            }
        }
    }
}
