package com.shihalev.soundcloud;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Playlist {
    private String name;
    private List<Soundtrack> tracks;

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

    public List<Soundtrack> getTracks() {
        return tracks;
    }


    public void printTrack() {
        for (Soundtrack track : tracks) {
            System.out.println(track.getName());
        }
    }

    public void playTrack(int numberSong) {
        if (numberSong <= tracks.size()) {
            for (Soundtrack track : tracks) {
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


}

