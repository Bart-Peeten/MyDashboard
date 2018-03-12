package com.example.bpeeten.auto_dashboard_2.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bpeeten.auto_dashboard_2.R;

public class MusicActivity extends AppCompatActivity {
    /* This Activity will make use of the lastfm API */
    private Button   searchAlbumBtn;
    private TextView searchAlbumTextView;
    private Toolbar  toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);

        searchAlbumBtn      = (Button)findViewById(R.id.search_albums_btn);
        searchAlbumTextView = (TextView)findViewById(R.id.albums_textview);

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
}
