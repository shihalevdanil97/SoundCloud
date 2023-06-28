package com.shihalev.soundcloud;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Playlist {
    private String name;
    private List<Track> tracks;

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


    public void printTrack() {
        for (Track track : tracks) {
            System.out.println(track.getName());
        }
    }

    public void playTrack(int number) {
        if (number > tracks.size()) {
            System.out.println("Такого трека нет");
        } else
            for (int numberTrack = 0; numberTrack < tracks.size(); numberTrack++) {
                if (number - 1 == numberTrack) {
                    tracks.get(numberTrack).listen();
                }

            }
    }

    public void mixPlaylist() {
        Collections.shuffle(tracks);
        printTrack();
    }
}

