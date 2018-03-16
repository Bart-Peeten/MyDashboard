package com.example.bpeeten.auto_dashboard_2.models;

import android.widget.ImageView;

/**
 * Created by bpeeten on 15/02/18.
 */

public class ListItemRecyclerViewModel {
    private String artistName;
    private String albumName;
    private String imageUrl;

    public ListItemRecyclerViewModel(String albumName, String artistName, String imageUrl) {
        this.albumName  = albumName;
        this.artistName = artistName;
        this.imageUrl   = imageUrl;
    }

    public String getArtistName() {
        return artistName;
    }

    public String getImageUrl() { return imageUrl; }

    public String getAlbumName() {
        return albumName;
    }
}
