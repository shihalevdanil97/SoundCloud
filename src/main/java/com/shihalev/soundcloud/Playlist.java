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
        tracks.add(new Track("Factor2", "Love", 3));
        tracks.add(new Track("Factor2", "Love", 3));
        tracks.add(new Track("Famaly", "Like", 2));
        tracks.add(new Track("Bookin", "Bad", 3));
        tracks.add(new Track("Diego", "Forlan", 4));
        tracks.add(new Track("Sex", "Beach", 5));
        tracks.add(new Track("Scorpions", "Animal", 2));
        tracks.add(new Track("Double", "Fight", 4));
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

    @Override
    public boolean equals(Object obj) {
        Playlist playlist = (Playlist) obj;
        return (name.equals(playlist.name));
    }

    @Override
    public String toString() {
        return name + tracks;
    }

}

