package com.example.android.music;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class UsersAdapter extends ArrayAdapter<Song>{
    public UsersAdapter(Context context, ArrayList<Song> users) {
        super(context, 0, users);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Song user = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_category, parent, false);
        }
        // Lookup view for data population
        TextView tvName = (TextView) convertView.findViewById(R.id.song_text_view);
      //  ImageView imageView = (ImageView) convertView.findViewById(R.id.play_image_view);
        // Populate the data into the template view using the data object
        tvName.setText(user.getSong_name());
        // Return the completed view to render on screen
        return convertView;
    }
}
