package com.example.bpeeten.auto_dashboard_2.activities;

import android.app.LauncherActivity;
import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.bpeeten.auto_dashboard_2.R;
import com.example.bpeeten.auto_dashboard_2.adapters.AlbumAdapter;
import com.example.bpeeten.auto_dashboard_2.models.ListItemRecyclerViewModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MusicAlbumsActivity extends AppCompatActivity {

    private static final String       API_KEY       = "9c67e618f41d1fa9d1e563f18823124b";
    private              String       urlFirstPart  = "http://ws.audioscrobbler.com/2.0/?method=artist.gettopalbums&artist=";
    private              String       urlSecondPart = "&api_key=";
    private              String       urlThirdPart  = "&format=json";
    private              String       url;
    private              RecyclerView recyclerView;
    private              RecyclerView.Adapter adapter;
    private              List<ListItemRecyclerViewModel> listItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_albums);
        String artistName = getIntent().getStringExtra("artistName");
        Log.e("TAG: artist name = ", artistName);
        url = urlFirstPart +
                artistName +
                urlSecondPart +
                API_KEY +
                urlThirdPart;

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listItems = new ArrayList<>();
        loadRecyclerViewData();
    }

    private void loadRecyclerViewData() {

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading Data...");
        progressDialog.show();

        Log.e("TAG", url);

        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray array = jsonObject.getJSONObject("topalbums").getJSONArray("album");

                            Log.e("TAG", array.toString());

                            for (int i = 0; i < array.length(); i++){
                                JSONObject o = array.getJSONObject(i);
                                ListItemRecyclerViewModel item = new ListItemRecyclerViewModel(
                                  o.getString("name"),
                                        o.getJSONObject("artist").getString("name"));
                                Log.e("TAG", o.getString("name"));
                                listItems.add(item);
                            }
                            adapter = new AlbumAdapter(listItems, getApplicationContext());
                            recyclerView.setAdapter(adapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_LONG).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
