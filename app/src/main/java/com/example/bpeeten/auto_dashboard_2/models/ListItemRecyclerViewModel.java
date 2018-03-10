package com.example.bpeeten.auto_dashboard_2.models;

/**
 * Created by bpeeten on 15/02/18.
 */

public class ListItemRecyclerViewModel {
    private String artistName;
    private String albumName;

    public ListItemRecyclerViewModel(String albumName, String artistName) {
        this.albumName = albumName;
        this.artistName = artistName;
    }

    public String getArtistName() {
        return artistName;
    }

    public String getAlbumName() {
        return albumName;
    }
}
