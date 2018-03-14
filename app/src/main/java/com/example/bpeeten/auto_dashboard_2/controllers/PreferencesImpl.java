package com.example.bpeeten.auto_dashboard_2.controllers;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;

import com.example.bpeeten.auto_dashboard_2.R;
import com.example.bpeeten.auto_dashboard_2.activities.RegisterActivity;
import com.example.bpeeten.auto_dashboard_2.interfaces.Preferences;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by bpeeten on 14/03/18.
 */

public class PreferencesImpl implements Preferences {
    private Context context;

    public PreferencesImpl(Context context) {
        this.context = context;
    }

    @Override
    public int getColor(){
        SharedPreferences preferences = context.getSharedPreferences("ColorPreferences", MODE_PRIVATE);
        int selectedColor = preferences.getInt("color", context.getResources().getColor(R.color.colorPrimary));

        return selectedColor;
    }

    public void saveInt(String key, int value) {
        SharedPreferences sharedPref = context.getSharedPreferences("Test", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(key, value);
        editor.commit();
    }
}
