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
import com.example.bpeeten.auto_dashboard_2.models.User;

public class HomeActivity extends AppCompatActivity {

    private Button  sms_button;
    private Button  spotify_button;
    private Toolbar toolbar;
    private User    logedinUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        sms_button     = (Button) findViewById(R.id.sms_button);
        spotify_button = (Button) findViewById(R.id.spotify_button);
        toolbar        = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        logedinUser = (User) getIntent().getSerializableExtra("User");

        if (getColor() != getResources().getColor(R.color.colorPrimary)){
            toolbar.setBackgroundColor(getColor());
            getWindow().setStatusBarColor(getColor());
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (getColor() != getResources().getColor(R.color.colorPrimary)){
            toolbar.setBackgroundColor(getColor());
            getWindow().setStatusBarColor(getColor());
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.settings:
                Toast.makeText(this, "Settings is geselecteerd", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);

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

    public int getColor(){
        SharedPreferences preferences = getSharedPreferences("ColorPreferences", MODE_PRIVATE);
        int selectedColor = preferences.getInt("color", getResources().getColor(R.color.colorPrimary));

        return selectedColor;
    }
}
