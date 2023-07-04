package com.shihalev.soundcloud;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Playlist {
    private final String name;
    private final List<Track> tracks;

    public Playlist(String name) {
        this.name = name;
        this.tracks = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Track> getTracks() {
        return tracks;
    }

    public void printTracks() {
        int number = 1;
        for (Track track : tracks) {
            System.out.println(number + ". " + track.getName());
            number++;
        }
    }

    public void playTrack(int number) {
        if (number > tracks.size() || number < 1) {
            System.out.println("Такого трека нет");
            return;
        }
        tracks.get(number - 1).listen();
    }

    @Override
    public boolean equals(Object obj) {
        Playlist playlist = (Playlist) obj;
        return (name.equals(playlist.name));
    }

    @Override
    public String toString() {
        return name;
    }

}

