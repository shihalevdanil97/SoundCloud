package com.shihalev.soundcloud;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MusicPlayground {
    private final Scanner scanner;
    private final List<Album> albums;
    private final List<Soundtrack> sounds;
    private final List<Playlist> playlists;

    public MusicPlayground(Scanner scanner) {
        this.scanner = scanner;
        this.albums = new ArrayList<>();
        this.sounds = new ArrayList<>();
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
                    printList();
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
        for (Playlist playlist : playlists) {
            System.out.println(playlist.getName() + " " + playlist.getNumber());
        }
    }

    private void printSounds() {
        for (com.shihalev.soundcloud.sound.Soundtrack sound : sounds) {
            System.out.println(sound.getName());
        }
    }

    private void createPlaylist(List<Playlist> playlists) {
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

    private boolean searchPlaylist(int number) {
        for (Playlist playlist : playlists) {
            if (number == playlist.getNumber()) {
                return true;
            }
        }
        System.out.println("Плейлист не найден");
        return false;
    }

    public void printPlaylist(List<Playlist> playlists) {
        for (Playlist playlist : playlists) {
            System.out.println(playlist.getNumber() + playlist.getName());
        }
    }

    private void addSound() {
        System.out.println("Название группы");
        String group = scanner.next();
        System.out.println("Песня");
        String track = scanner.next();
        System.out.println("Длина трека");
        int time = scanner.nextInt();

        Soundtrack sound = new Soundtrack(group, track, time);
        sounds.add(sound);
    }

    public void addAlbum() {
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
        String name = scanner.next();
        playlists.add(new Playlist(name));
    }

    private void printList() {
        for (Soundtrack name : sounds) {
            System.out.println(name.getName());
        }
    }

    public void listenSound() {
        System.out.println("Название трека");
        String nameSound = scanner.next();
        for (Soundtrack sound : sounds) {
            if (nameSound.equals(sound.getName())) {
                sound.listen();
                System.out.println("Поставить лайк ?");
                String yesOrNo = scanner.next();
                if (yesOrNo.equals("Yes")) {
                    sound.like();
                    return;
                } else {
                    return;
                }
            }
        }
        System.out.println("Такого трека нет");

    }

    public void playlist() {
        if (!playlists.isEmpty()) {
            printPlaylists();
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
                                addInPlayLIst();
                                break;
                            case "mixPlayList":
                                playlist.mixPlaylist();
                                break;
                            case "likeTrack":
                                likeTrack(playlist, sounds);
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

    private boolean searchSound(int number) {
        for (Soundtrack soundtrack : sounds) {
            if (number == soundtrack.getNumber()) {
                return true;
            }
        }
        return false;
    }

    private void addInPlayLIst() {
        if (playlists.isEmpty()) {
            addPlaylist();
        }
        System.out.println("Введите номер плейлиста");
        int number = scanner.nextInt();
        if (searchPlaylist(number)) {
            printSounds();
            for (Playlist playlist : playlists) {
                System.out.println("Какой добавить трек?");
                int numberSound = scanner.nextInt();
                if (searchSound(numberSound)) {
                    for (Soundtrack sound : sounds) {
                        if (sound.getNumber() == numberSound) {
                            playlist.getTracks().add(sound);
                        }
                    }
                }
            }
        }
    }

    public void likeTrack(Playlist playlist, List<Soundtrack> sounds) {
        playlist.printTrack();
        System.out.println("Какому треку поставить лайк ?");
        String likeTrackName = scanner.next();
        for (Soundtrack sound : sounds) {
            if (likeTrackName.equals(sound.getName())) {
                sound.like();
                break;
            } else {
                System.out.println("Такого трека нет");
            }
        }
    }
}
