package com.example.android.music;

public class Song {
    private String song_name;
    private int song_id;

    Song(String song_name,int song_id){
        this.song_name = song_name;
        this.song_id = song_id;
    }

    public String getSong_name() {
        return song_name;
    }

    public int getSong_id() {
        return song_id;
    }
}
