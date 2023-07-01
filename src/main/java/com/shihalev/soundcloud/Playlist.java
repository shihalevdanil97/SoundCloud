package com.shihalev.soundcloud;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**
 * Обьект класса <code>Playlist<code>
 * имитирует музыкальный Плейлист
 * Пункт управления плейлистом
 */

public class Playlist {
    private final String name;
    private final List<Track> tracks;

    public Playlist(String name) {
        this.name = name;
        this.tracks = new ArrayList<>();

    }

    @Override
    public boolean equals(Object obj) {
        Playlist playlist = (Playlist)obj;
        return name == playlist.name;
    }

    public String getName() {
        return name;
    }

    public List<Track> getTracks() {
        return tracks;
    }


    public void printTracks() {
        for (Track track : tracks) {
            System.out.println(track.getName());
        }
    }

    public void playTrack(int number) {
        if (number > tracks.size() || number < 1) {
            System.out.println("Такого трека нет");
            return;
        }
        tracks.get(number - 1).listen();
    }

    public void mixPlaylist() {
        Collections.shuffle(tracks);
        printTracks();
    }
}

