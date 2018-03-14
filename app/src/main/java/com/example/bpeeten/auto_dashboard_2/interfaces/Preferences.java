package com.example.bpeeten.auto_dashboard_2.interfaces;

import android.content.Context;
import android.view.View;

import com.example.bpeeten.auto_dashboard_2.activities.RegisterActivity;

/**
 * Created by bpeeten on 14/03/18.
 */

public interface Preferences {
    void saveInt(String key, int value);
    int  getColor();
}
