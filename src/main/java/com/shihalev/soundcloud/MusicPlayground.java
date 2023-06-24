package com.shihalev.soundcloud;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MusicPlayground {
    private final Scanner scanner;

    public MusicPlayground(Scanner scanner) {
        this.scanner = scanner;
    }

    public void start() {
        System.out.println("Это музыкальная площадка");
        System.out.println("SoundCloud");
        System.out.println("Что бы продолжить, напишите как к вам обращаться");
        String tracks = scanner.next();
        System.out.println("Добро пожаловать" + tracks);

        List<Album> albums = new ArrayList<>();
        List<Track> tracks = new ArrayList<>();
        List<Playlist> playlists = new ArrayList<>();

        while (true) {
            System.out.println("Выбирай команду");
            String command = scanner.next();
            switch (command) {
                case "addSound":
                    addSound(tracks);
                    break;

                case "addAlbum":
                    addAlbum(albums);
                    break;

                case "addPlaylist":
                    addPlaylist(playlists);
                    break;

                case "printList":
                    printlist(tracks);
                    break;

                case "listenSound":
                    listenSound(tracks);
                    break;

                case "playlist":
                    playlist(playlists, tracks);
                    break;

                case "addInPlaylist":
                    if (playlists.isEmpty()) {
                        addPlaylist(playlists);

                    } else {
                        printSounds(tracks);
                        System.out.println("Выбрать No трека");
                        int number = scanner.nextInt();
                        addInPlayLIst(playlists, tracks, number);
                    }
                    break;
                case "exit":
                    return;
            }
        }
    }

    public void printPlaylists(List<Playlist> playists) {
        for (Playlist playlist : playists) {
            System.out.println(playlist.getName() + " " + playlist.getNumber());
        }
    }

    public void printSounds(List<Track> tracks) {
        for (Track sound : tracks) {
            System.out.println(sound.getNumber() + sound.getName());
        }
    }

    public void addSound(List<Track> tracks) {
        System.out.println("Название группы");
        String group = scanner.next();
        System.out.println("Песня");
        String track = scanner.next();
        System.out.println("Длина трека");
        int time = scanner.nextInt();

        Track sound = new Track(group, track, time);
        tracks.add(sound);
    }

    public void addAlbum(List<Album> albums) {
        System.out.println("Название группы");
        String nameGroup = scanner.next();
        System.out.println("Название Альбома");
        String nameAlbum = scanner.next();
        System.out.println("Сколько треков");
        Album album = new Album(nameGroup, nameAlbum);
        albums.add(album);
    }

    public void addPlaylist(List<Playlist> playlists) {
        System.out.println("Название плейлиста");
        String name = scanner.next();
        playlists.add(new Playlist(name));
    }

    public void printlist(List<Track> tracks) {
        for (Track n : tracks) {
            System.out.println(n.getName());
        }
    }

    public void listenSound(List<Track> tracks) {
        System.out.println("Название трека");
        String nameSound = scanner.next();
        for (Track track : tracks)
            if (nameSound.equals(track.getName())) {
                track.listen();
                System.out.println("Поставить лайк ?");
                String yesOrNou = scanner.next();
                if (yesOrNou.equals("Yes")) {
                    track.like();
                }
            } else System.out.println("Трек не найден");
    }

    public void playlist(List<Playlist> playlists, List<Track> tracks) {
        if (!playlists.isEmpty()) {
            printPlaylists(playlists);
            System.out.println("Выбрать No Плейлиста");
            int number = scanner.nextInt();
            for (Playlist playlist : playlists) {
                if (number == playlist.getNumber()) {
                    playlist.printTrack();
                    while (true) {
                        System.out.println("Выбрать команду");
                        String playlistCommand = scanner.next();
                        switch (playlistCommand) {
                            case "turnSong":
                                turnSong(playlist);
                                break;
                            case "addInPlayLIst":
                                addTrack(playlist.getTracks(),playlists);
                                break;
                            case "mixPlayList":
                                playlist.mixPlaylist();
                                break;
                            case "likeTrack":
                                likeTrack(playlist, tracks);
                            case "Back":
                                return;

                        }
                    }
                } else {
                    System.out.println("Введите название");
                    String answer = scanner.next();
                    Playlist playlistName = new Playlist(answer);
                    playlists.add(playlistName);
                }
            }
        }
    }


    public void turnSong(Playlist playlist) {
        System.out.println("Введите номер трека");
        int numberTrack = scanner.nextInt();
        playlist.playTrack(numberTrack);
    }

    public void addInPlayLIst(List<Playlist> playlists, List<Track> tracks) {
        if(playlists.isEmpty()){
            addPlaylist(playlists);

        }
        selectPlaylist(playlists).add;
        int number = scanner.nextInt();
        for(Track sound : tracks){
            if (number == sound.getNumber()){
                int
                for(Track track : pl)
            }
        }
    }

    public void likeTrack(Playlist playlist, List<Track> tracks) {
        playlist.printTrack();
        System.out.println("Какому треку поставить лайк ?");
        String likeTrackName = scanner.next();
        for (Track track : tracks) {
            if (likeTrackName.equals(track.getName())) {
                track.like();
                break;
            } else {
                System.out.println("Такого трека нет");

            }
        }
    }

    public void addTrack(List<Track> tracks, List<Playlist> playlists){
        int number = scanner.nextInt();
        for(Track track : tracks){
            if(track.getNumber() == number){
                selectPlaylist(playlists).add(track);
            }
        }

    }

}
