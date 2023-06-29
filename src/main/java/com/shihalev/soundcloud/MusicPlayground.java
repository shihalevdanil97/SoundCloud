package com.shihalev.soundcloud;

import com.shihalev.soundcloud.sound.Sound;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MusicPlayground {
    private final Scanner scanner;
    private final List<Album> albums;
    private final List<Track> tracks;
    private final List<Playlist> playlists;

    public MusicPlayground(Scanner scanner) {
        this.scanner = scanner;
        this.albums = new ArrayList<>();
        this.tracks = new ArrayList<>();
        this.playlists = new ArrayList<>();
    }

    public void start() {
        System.out.println("Это музыкальная площадка");
        System.out.println("SoundCloud");
        System.out.println("Что бы продолжить, напишите как к вам обращаться");
        String name = scanner.next();
        System.out.println("Добро пожаловать" + name);

        while (true) {
            System.out.println("Выбирай команду");
            String command = scanner.next();
            switch (command) {
                case "addSound":
                    addSound();
                    break;

                case "addAlbum":
                    addAlbum();
                    break;

                case "addPlaylist":
                    addPlaylist();
                    break;

                case "printList":
                    printSounds();
                    break;

                case "listenSound":
                    listenSound();
                    break;

                case "playlist":
                    playlist();
                    break;

                case "exit":
                    return;
            }
        }
    }

    private void printPlaylists() {
        int number = 1;
        for (Playlist playlist : playlists) {
            System.out.println(number + ". " + playlist.getName());
        }
    }

    private void addSound() {
        System.out.println("Название группы");
        String group = scanner.next();
        System.out.println("Песня");
        String track = scanner.next();
        System.out.println("Длина трека");
        int time = scanner.nextInt();

        Track sound = new Track(group, track, time);
        tracks.add(sound);
    }

    private void addAlbum() {
        System.out.println("Название группы");
        String nameGroup = scanner.next();
        System.out.println("Название Альбома");
        String nameAlbum = scanner.next();
        System.out.println("Сколько треков");
        Album album = new Album(nameGroup, nameAlbum);
        albums.add(album);
    }

    private void addPlaylist() {
        System.out.println("Название плейлиста");
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

    private void printSounds() {
        int trackNumber = 1;
        for (Sound name : tracks) {
            System.out.println(trackNumber + " . " + name.getName());
            trackNumber++;
        }
    }

    private void listenSound() {
        System.out.println("Номер трека");
        int number = scanner.nextInt();
        if (number > tracks.size() || number < 1) {
            tracks.get(number + 1).listen();
            System.out.println("Поставить лайк ?");
            String yesOrNo = scanner.next();
            if (yesOrNo.equals("Yes")) {
                tracks.get(number + 1).like();
            }
        } else {
            System.out.println("Такого трека нет");
        }
    }


    private void playlist() {
        if (playlists.isEmpty()) {
            System.out.println("Нет плейлистов");
            return;
        }
        printPlaylists();
        System.out.println("Выбрать No Плейлиста");
        int number = scanner.nextInt();
        if (isThatPlaylist(number)) {
            playlists.get(number).printTrack();
            while (true) {
                System.out.println("Выбрать команду");
                String playlistCommand = scanner.next();
                switch (playlistCommand) {
                    case "turnSong":
                        turnSong(playlists.get(number - 1));
                        break;
                    case "addInPlayLIst":
                        addInPlaylist(number - 1);
                        break;
                    case "mixPlayList":
                        playlists.get(number - 1).mixPlaylist();
                        break;
                    case "likeTrack":
                        likeTrack(playlists.get(number - 1));
                    case "Back":
                        return;
                }
            }
        } else {
            System.out.println("Плейлист не найден");
        }
    }


    private boolean isThatPlaylist(int numberPlaylist) {
        if (numberPlaylist > playlists.size() || numberPlaylist < 1) {
            return true;
        }
        return false;
    }

    private void turnSong(Playlist playlist) {
        System.out.println("Введите номер трека");
        int numberTrack = scanner.nextInt();
        playlist.playTrack(numberTrack);
    }

    private void addInPlaylist(int numberPlaylist) {
        if (numberPlaylist < 1 || numberPlaylist > playlists.size()) {
            System.out.println("Какой добавить трек?");
            int numberSound = scanner.nextInt();
            if (numberSound < 1 || numberSound > tracks.size()) {
                playlists.get(numberPlaylist).getTracks().add(tracks.get(numberSound));
            }
        }
    }

    private void likeTrack(Playlist playlist) {
        playlist.printTrack();
        System.out.println("Какому треку поставить лайк ?");
        String likeTrackName = scanner.next();
        for (Track sound : playlist.getTracks()) {
            if (likeTrackName.equals(sound.getName())) {
                sound.like();
                break;
            } else {
                System.out.println("Такого трека нет");
            }
        }
    }
}
