package com.example.bpeeten.auto_dashboard_2.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bpeeten.auto_dashboard_2.R;
import com.example.bpeeten.auto_dashboard_2.controllers.PreferencesImpl;
import com.example.bpeeten.auto_dashboard_2.interfaces.Preferences;

public class MusicActivity extends AppCompatActivity {
    /* This Activity will make use of the lastfm API */
    private Button      searchAlbumBtn;
    private TextView    searchAlbumTextView;
    private Toolbar     toolbar;
    private Preferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);

        searchAlbumBtn      = (Button)findViewById(R.id.search_albums_btn);
        searchAlbumTextView = (TextView)findViewById(R.id.albums_textview);
        toolbar             = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        preferences         = new PreferencesImpl(this);
        setSupportActionBar(toolbar);

        searchAlbumBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (searchAlbumTextView.getText() == null){
                    Toast.makeText(getApplicationContext(), "Vul eerst een atristen naam in...", Toast.LENGTH_LONG).show();

                } else {
                    Log.e("TAG", searchAlbumTextView.getText().toString());
                    Intent intent = new Intent(MusicActivity.this, MusicAlbumsActivity.class);
                    intent.putExtra("artistName", searchAlbumTextView.getText().toString());
                    startActivity(intent);
                }
            }
        });
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
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.settings:
                Toast.makeText(this, "Settings is geselecteerd", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
            case R.id.logout:
                Intent intentLogout = new Intent(this, LoginActivity.class);
                intentLogout.putExtra("logout", "LOGOUT");
                startActivity(intentLogout);
                finish();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
