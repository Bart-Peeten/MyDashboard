package com.example.bpeeten.auto_dashboard_2.adapters;

import android.app.LauncherActivity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bpeeten.auto_dashboard_2.R;
import com.example.bpeeten.auto_dashboard_2.models.ListItemRecyclerViewModel;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by bpeeten on 15/02/18.
 */

public class AlbumAdapter extends Adapter<AlbumAdapter.ViewHolder> {

    private List<ListItemRecyclerViewModel> listItems;
    private Context                         context;

    public AlbumAdapter(List<ListItemRecyclerViewModel> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ListItemRecyclerViewModel listItem = listItems.get(position);

        holder.textviewArtist.setText(listItem.getArtistName());
        holder.textviewAlbum.setText(listItem.getAlbumName());
        Picasso.with(context)
                .load(listItem.getImageUrl())
                .into(holder.imageId);

    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView  textviewArtist;
        public TextView  textviewAlbum;
        public ImageView imageId;

        public ViewHolder(View itemView) {
            super(itemView);
            textviewAlbum  = (TextView) itemView.findViewById(R.id.textViewAlbumName);
            textviewArtist = (TextView) itemView.findViewById(R.id.textViewArtistName);
            imageId        = (ImageView) itemView.findViewById(R.id.image_id);
        }
    }
}
