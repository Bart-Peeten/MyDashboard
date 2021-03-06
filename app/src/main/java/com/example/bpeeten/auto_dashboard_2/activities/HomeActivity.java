package com.example.bpeeten.auto_dashboard_2.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.bpeeten.auto_dashboard_2.R;
import com.example.bpeeten.auto_dashboard_2.controllers.PreferencesImpl;
import com.example.bpeeten.auto_dashboard_2.interfaces.Preferences;
import com.example.bpeeten.auto_dashboard_2.models.User;

public class HomeActivity extends AppCompatActivity {

    private Button      sms_button;
    private Button      spotify_button;
    private Toolbar     toolbar;
    private User        logedinUser;
    private Preferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        sms_button     = (Button) findViewById(R.id.sms_button);
        spotify_button = (Button) findViewById(R.id.spotify_button);
        toolbar        = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        preferences    = new PreferencesImpl(this);
        setSupportActionBar(toolbar);

        logedinUser = (User) getIntent().getSerializableExtra("User");

        checkBackgroundColor();

    }

    private void checkBackgroundColor() {
        if (preferences.getColor() != getResources().getColor(R.color.colorPrimary)){
            toolbar.setBackgroundColor(preferences.getColor());
            getWindow().setStatusBarColor(preferences.getColor());
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        checkBackgroundColor();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.settings:
                Toast.makeText(this, "Settings is geselecteerd", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                return true;
            case R.id.logout:
                Intent intentLogout = new Intent(this, LoginActivity.class);
                intentLogout.putExtra("logout", "LOGOUT");
                startActivity(intentLogout);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }


    public void sms_OnClick(View view) {
        Toast.makeText(this, "SMS Button is pressed\n Under Construction", Toast.LENGTH_LONG).show();
        //Intent intent = new Intent(this, SmsActivity.class);
        //startActivity(intent);
    }

    public void spotify_OnClick(View view) {
        Toast.makeText(this, "Spotify page will open", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, MusicActivity.class);
        startActivity(intent);
    }

    public void weer_Onclick(View view) {
        Toast.makeText(this, "Wheater page will open", Toast.LENGTH_LONG).show();
    }
}
