package com.shihalev.soundcloud;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Playlist {
    private String name;
    private List<Track> tracks;

    private int number;

    private static int nextNumber = 1;

    private Scanner scanner;


    public Playlist(String name) {
        this.name = name;
        this.tracks = new ArrayList<>();
        this.number = nextNumber;
        nextNumber++;
        this.scanner = new Scanner(System.in);
    }

    public String getName() {
        return name;
    }

    public int getNumber() {
        return number;
    }

    public List<Track> getTracks() {
        return tracks;
    }

    public void printPlaylist(List<Playlist>playlists){
        for (Playlist playlist : playlists){
            System.out.println(playlist.getNumber() + playlist.getName());
        }
    }

    public void printTrack() {
        for (Track track : tracks) {
            System.out.println(track.getName());
        }
    }

    public void playTrack(int numberSong) {
        if (numberSong <= tracks.size()) {
            for (Track track : tracks) {
                track.listen();
            }
        } else {
            System.out.println("track не найден");
        }
    }


    public void mixPlaylist() {
        Collections.shuffle(tracks);
        printTrack();
    }

    public Playlist selectPlaylist(List<Playlist> playlists) {
        if (playlists.isEmpty()) {
            createPlaylist(playlists);
            printPlaylist(playlists);
        }

        System.out.println("Выбрать No плейлиста");
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();
        for (Playlist playlist : playlists) {
            if (number == playlist.getNumber()) {
                return playlist;
            }
            else {
                System.out.println("Плейлист не найден");
            }
        }

    }

    public void createPlaylist(List<Playlist> playlists) {
        System.out.println("Введите название");
        String answer = scanner.next();
        for (Playlist playlist : playlists) {
            if (playlist.getName().equals(answer)) {
                System.out.println("Такой название уже есть");
            } else {
                Playlist playlistName = new Playlist(answer);
                playlists.add(playlistName);
            }
        }
    }
}

