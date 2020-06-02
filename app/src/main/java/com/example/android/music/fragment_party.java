package com.example.android.music;


import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import static android.media.AudioManager.AUDIOFOCUS_LOSS_TRANSIENT;
import static android.media.AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK;


/**
 * A simple {@link Fragment} subclass.
 */
public class fragment_party extends Fragment {
    private MediaPlayer mp;

    private AudioManager audioManager;

    AudioManager.OnAudioFocusChangeListener afChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AUDIOFOCUS_LOSS_TRANSIENT|| focusChange== AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK){
            mp.pause();
        } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
            mp.start();
        } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
            releaseMediaPlayer();
        }
    }
};
    public fragment_party() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.list_view, container, false);
        final ArrayList<Song> songs = new ArrayList<>();

        audioManager = (AudioManager) getContext().getSystemService(Context.AUDIO_SERVICE);
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
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Song song = songs.get(position);
                releaseMediaPlayer();
                int result = audioManager.requestAudioFocus(afChangeListener,
                        // Use the music stream.
                        AudioManager.STREAM_MUSIC,
                        // Request permanent focus.
                        AudioManager.AUDIOFOCUS_GAIN);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                   // audioManager.unregisterMediaButtonEventReceiver(RemoteControlReceiver);
                    mp = MediaPlayer.create(getContext(), song.getSong_id());
                    mp.start();
                    // Start playback.
                    mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            releaseMediaPlayer();
                        }
                    });
                }
            }
        });
        return listView;
    }

    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mp != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mp.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mp = null;
            audioManager.abandonAudioFocus(afChangeListener);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        releaseMediaPlayer();
    }

}
