package com.example.android.music;


import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class fragment_party extends Fragment {


    public fragment_party() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.list_view, container, false);
        ArrayList<Song> songs = new ArrayList<>();
        songs.add(new Song("Aao Kabhi Haveli Pe",R.raw.party_aao_kabhi_haveli_pe));
        songs.add(new Song("Buddhu sa Man Hai",R.raw.party_buddhu_sa_man_hai));
        songs.add(new Song("Kamariya(Stree)",R.raw.party_hila_de_kamariya));
        songs.add(new Song("Ladki Beautiful Kar gyi Chull",R.raw.party_kar_gyi_chull));
        songs.add(new Song("Milegi Milegi",R.raw.party_milegi_milegi));
        songs.add(new Song("Let's Nacho",R.raw.party_lets_nacho));
        songs.add(new Song("Kamariya",R.raw.party_kamariya));

        UsersAdapter itemsAdapter = new UsersAdapter(getContext(), songs);
        ListView listView = (ListView) view.findViewById(R.id.listview);
        listView.setAdapter(itemsAdapter);
        return listView;
    }

}
