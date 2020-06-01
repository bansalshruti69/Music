package com.example.android.music;


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
public class fragment_sad extends Fragment {


    public fragment_sad() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.list_view, container, false);
        ArrayList<Song> songs = new ArrayList<>();

        songs.add(new Song("Bekhayali",R.raw.sad_bekhayali));
        songs.add(new Song("Bulleya",R.raw.sad_bulleya));
        songs.add(new Song("Channa Mereya",R.raw.sad_channa_mereya));
        songs.add(new Song("Tera Ban Jaunga",R.raw.sad_tera_ban_jaunga));
        songs.add(new Song("Tujhe Kitna Chahne Lage Hum",R.raw.sad_tujhe_kitna_chahne_lage));
        songs.add(new Song("Tujhe Kitna Chahne Lage Hum",R.raw.sad_tujhe_kitna_chahne_lage));
        UsersAdapter itemsAdapter = new UsersAdapter(getContext(), songs);
        ListView listView = (ListView) view.findViewById(R.id.listview);
        listView.setAdapter(itemsAdapter);
        return listView;
    }

}
