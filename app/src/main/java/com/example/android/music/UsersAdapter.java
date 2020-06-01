package com.example.android.music;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class UsersAdapter extends ArrayAdapter<Song>{
    private MediaPlayer mediaPlayer;
    public UsersAdapter(Context context, ArrayList<Song> users) {
        super(context, 0, users);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        final Song user = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_category, parent, false);
        }
        // Lookup view for data population
        TextView tvName = (TextView) convertView.findViewById(R.id.song_text_view);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.play_image_view);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean gotFocus = requestAudioFocusForMyApp(getContext());
                if(gotFocus) {
                    if(mediaPlayer!=null){
                        mediaPlayer.release();
                    }
                    mediaPlayer = MediaPlayer.create(getContext(), user.getSong_id());
                    mediaPlayer.start();
                }

                 final String TAG = "AudioFocus";
                 AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {

                    @Override
                    public void onAudioFocusChange(int focusChange) {
                        switch (focusChange) {
                            case AudioManager.AUDIOFOCUS_GAIN:
                                releaseAudioFocusForMyApp(getContext());
                                //restart/resume your sound
                                break;
                            case AudioManager.AUDIOFOCUS_LOSS:
                                releaseAudioFocusForMyApp(getContext());
                                //Loss of audio focus for a long time
                                //Stop playing the sound
                                break;
                            case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:
                                mediaPlayer.pause();
                                mediaPlayer.start();
                                //Loss of audio focus for a short time
                                //Pause playing the sound
                                break;
                            case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK:
                                mediaPlayer.pause();
                                mediaPlayer.start();
                                break;

                            default:
                                //
                        }
                    }
                };
            }
        });
        // Populate the data into the template view using the data object
        tvName.setText(user.getSong_name());
        // Return the completed view to render on screen
        return convertView;
    }

    private boolean requestAudioFocusForMyApp(final Context context) {
        AudioManager am = (AudioManager)context.getSystemService(Context.AUDIO_SERVICE);

        // Request audio focus for playback
        int result = am.requestAudioFocus(null,
                // Use the music stream.
                AudioManager.STREAM_MUSIC,
                // Request permanent focus.
                AudioManager.AUDIOFOCUS_GAIN);

        if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
            Log.d("AudioFocus", "Audio focus received");
            return true;
        } else {
            Log.d("AudioFocus", "Audio focus NOT received");
            return false;
        }
    }

    void releaseAudioFocusForMyApp(final Context context) {
        AudioManager am = (AudioManager)context.getSystemService(Context.AUDIO_SERVICE);
        am.abandonAudioFocus(null);
    }
}
