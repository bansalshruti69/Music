package com.example.android.music;


import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class fragment_romatic extends Fragment {

    public fragment_romatic() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.list_view, container, false);
        ArrayList<Song> songs = new ArrayList<>();
        songs.add(new Song("Bolna",R.raw.romantic_bolna));
        songs.add(new Song("Chal Ghar Chalien",R.raw.romantic_chal_ghar_chalien));
        songs.add(new Song("Humraah",R.raw.romantic_humraah));
        songs.add(new Song("Pehla Pehla Pyaar",R.raw.romantic_pehla_pyaar));
        songs.add(new Song("Bolna",R.raw.romantic_bolna));
        songs.add(new Song("Chal Ghar Chalien",R.raw.romantic_chal_ghar_chalien));
        songs.add(new Song("Humraah",R.raw.romantic_humraah));
        songs.add(new Song("Pehla Pehla Pyaar",R.raw.romantic_pehla_pyaar));
        UsersAdapter itemsAdapter = new UsersAdapter(getContext(), songs);
        ListView listView = (ListView) view.findViewById(R.id.listview);
        listView.setAdapter(itemsAdapter);
        return listView;
    }

}
