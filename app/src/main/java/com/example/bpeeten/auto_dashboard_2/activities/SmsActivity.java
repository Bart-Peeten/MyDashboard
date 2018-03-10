package com.example.bpeeten.auto_dashboard_2.activities;

import android.content.Intent;
import android.provider.Telephony;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.bpeeten.auto_dashboard_2.R;

public class SmsActivity extends AppCompatActivity {

    android.support.v7.widget.Toolbar toolbar;
    private static SmsActivity inst;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);

        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        inst = this;
    }

    public static SmsActivity instance(){
        return inst;
    }

    public void updateInbox(String smsMessage){

    }
}


