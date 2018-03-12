package com.example.bpeeten.auto_dashboard_2.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toolbar;

import com.example.bpeeten.auto_dashboard_2.R;

public class SettingsActivity extends AppCompatActivity {
    android.support.v7.widget.Toolbar toolbar;
    Button  redButton;
    Button  greenButton;
    Button  blueButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        toolbar     = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        redButton   = (Button) findViewById(R.id.red_btn);
        greenButton = (Button) findViewById(R.id.green_btn);
        blueButton  = (Button) findViewById(R.id.blue_btn);

        if (getColor() != getResources().getColor(R.color.colorPrimary)){
            toolbar.setBackgroundColor(getColor());
            getWindow().setStatusBarColor(getColor());
        }

        redButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toolbar.setBackgroundColor(getResources().getColor(R.color.colorRed));
                getWindow().setStatusBarColor(getResources().getColor(R.color.colorRed));

                storeColor(getResources().getColor(R.color.colorRed));
                goToHome();
            }
        });

        greenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toolbar.setBackgroundColor(getResources().getColor(R.color.colorGreen));
                getWindow().setStatusBarColor(getResources().getColor(R.color.colorGreen));

                storeColor(getResources().getColor(R.color.colorGreen));
                goToHome();
            }
        });

        blueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toolbar.setBackgroundColor(getResources().getColor(R.color.colorBlue));
                getWindow().setStatusBarColor(getResources().getColor(R.color.colorBlue));

                storeColor(getResources().getColor(R.color.colorBlue));
                goToHome();
            }
        });
    }

    private void storeColor(int color){
        SharedPreferences preferences = getSharedPreferences("ColorPreferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("color", color);
        editor.apply();
    }

    private int getColor(){
        SharedPreferences preferences = getSharedPreferences("ColorPreferences", MODE_PRIVATE);
        int selectedColor = preferences.getInt("color", getResources().getColor(R.color.colorPrimary));

        return selectedColor;
    }

    private void goToHome(){
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }
}
