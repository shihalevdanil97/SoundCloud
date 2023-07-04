package com.shihalev.soundcloud;

import com.shihalev.soundcloud.sound.Sound;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class MusicPlayground {
    private final Scanner scanner;
    private final List<Album> albums;
    private final List<Track> tracks;
    private final List<Playlist> playlists;
    private final List<Podcast> podcasts;

    public MusicPlayground(Scanner scanner) {
        this.scanner = scanner;
        this.albums = new ArrayList<>();
        this.tracks = new ArrayList<>();
        this.playlists = new ArrayList<>();
        this.podcasts = new ArrayList<>();
    }

    public void start() {
        System.out.println("Это музыкальная площадка");
        System.out.println("SoundCloud");
        System.out.println("Что бы продолжить, напишите как к вам обращаться");
        String name = scanner.next();
        System.out.println("Добро пожаловать  " + name);

        while (true) {
            System.out.println("Выбирай команду");
            String command = scanner.next();
            switch (command) {
                case "addSound":
                    addSound();
                    break;
                case "printList":
                    printSounds();
                    break;
                case "listenSound":
                    listenSound();
                    break;
                case "songRating":

                case "addAlbum":
                    addAlbum();
                    break;
                case "playlist":
                    playlist();
                    break;
                case "addPlaylist":
                    addPlaylist();
                    break;
                case "addPodcast":
                    addPodcast();
                    break;
                case "printPodcast":
                    printPodcast();
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
            number++;
        }
    }

    private void printTracksPlaylist(int playlistNumber) {
        if (playlists.get(playlistNumber).getTracks().isEmpty()) {
            System.out.println("Плейлист путой");
            return;
        }
        int number = 1;
        for (Track track : playlists.get(playlistNumber).getTracks()) {
            System.out.println(number + ". " + track.getName());
            number++;
        }
    }


    private void addSound() {
        System.out.println("Название группы");
        String group = scanner.next();
        System.out.println("Песня");
        String name = scanner.next();
        System.out.println("Длина");
        int time = scanner.nextInt();
        Track trackName = new Track(group, name, time);
        for (Track track : tracks) {
            if (track.equals(trackName)) {
                System.out.println("Такое название уже есть в списке");
                return;
            }
        }
        System.out.println("Трек добавлен");
        tracks.add(trackName);
    }

    private void addAlbum() {
        System.out.println("Название группы");
        String groupName = scanner.next();
        System.out.println("Название Альбома");
        String albumName = scanner.next();
        System.out.println("Длинна альбома");
        int duration = scanner.nextInt();
        Album album = new Album(groupName, albumName, duration);
        for (Album albumNumber : albums) {
            if (albumNumber.equals(album)) {
                System.out.println("Такой альбом уже есть");
                return;
            }
        }
        System.out.println("Альбом успешно добавлен");
        albums.add(album);
    }

    private void addPlaylist() {
        System.out.println("Название плейлиста");
        String name = scanner.next();
        Playlist playlistName = new Playlist(name);
        for (Playlist playlist : playlists) {
            if (playlist.equals(playlistName)) {
                System.out.println("Такой название уже есть");
                return;
            }
        }
        System.out.println("Плейлист добавлен");
        playlists.add(playlistName);
    }


    private void addPodcast() {
        System.out.println("Название подкаста");
        String name = scanner.next();
        System.out.println("Введите время");
        int duration = scanner.nextInt();
        Podcast podcastName = new Podcast(name, duration);
        for (Podcast podcast : podcasts) {
            if (podcast.equals(name)) {
                System.out.println("Такое имя уже есть в списке");
                return;
            }
        }
        System.out.println("Подкаст добавлен");
        podcasts.add(podcastName);
    }

    private void printPodcast() {
        int number = 1;
        for (Podcast podcast : podcasts) {
            System.out.println(number + ". " + podcast.getName());
            number++;
        }
    }

    private void printSounds() {
        int trackNumber = 1;
        for (Track name : tracks) {
            System.out.println(trackNumber + ". " + name.getGroup() + " " + name.getName());
            trackNumber++;
        }
    }

    public void mixPlaylist(Playlist playlist) {
        Collections.shuffle(playlist.getTracks());
    }

    private void listenSound() {
        System.out.println("Номер трека");
        int number = scanner.nextInt();
        if (!(number > tracks.size() || number < 1)) {
            tracks.get(number - 1).listen();
            System.out.println("Поставить лайк ?");
            String yesOrNo = scanner.next();
            if (yesOrNo.equals("Yes")) {
                System.out.println("Лайк поставлен");
                tracks.get(number - 1).like();
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
        if (isPlaylistPresent(number - 1)) {
            playlists.get(number - 1).printTracks();
            while (true) {
                System.out.println("Выбрать команду");
                String playlistCommand = scanner.next();
                switch (playlistCommand) {
                    case "turnSong":
                        printTracksPlaylist(number - 1);
                        turnSong(playlists.get(number - 1));
                        break;
                    case "addInPlaylist":
                        addInPlaylist(number);
                        break;
                    case "mixPlaylist":
                        mixPlaylist(playlists.get(number - 1));
                        printTracksPlaylist(number - 1);
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

    private boolean isPlaylistPresent(int playlistNumber) {
        if (playlistNumber < playlists.size() || playlistNumber > 1) {
            return true;
        }
        return false;
    }

    private void turnSong(Playlist playlist) {
        if (playlist.getTracks().isEmpty()) {
            return;
        }
        System.out.println("Введите номер трека");
        int numberTrack = scanner.nextInt();
        playlist.playTrack(numberTrack);
    }

    private void addInPlaylist(int playlistNumber) {
        if (playlistNumber < 1 || playlistNumber > playlists.size()) {
            return;
        }
        printSounds();
        System.out.println("Какой добавить трек?");
        int soundNumber = scanner.nextInt();
        if (soundNumber < 1 || soundNumber > tracks.size()) {
            return;
        }
        playlists.get(playlistNumber - 1).getTracks().add(tracks.get(soundNumber - 1));
    }

    private void likeTrack(Playlist playlist) {
        playlist.printTracks();
        System.out.println("Какому треку поставить лайк ?");
        int likeTrackNumber = scanner.nextInt();
        if (likeTrackNumber > tracks.size() || likeTrackNumber < 1) {
            return;
        }
        System.out.println("Вы поставили лайк");
        playlist.getTracks().get(likeTrackNumber - 1).like();
    }

    public void startFillTestData() {
        addSounds();
        addAlbums();
        addIntAlbums();
        addPlaylists();
        addInPlaylists();

    }

    private void addSounds() {
        for (int amount = 0; amount < 10; amount++) {
            String group = "LOVV66";
            String name = "UhUh";
            int duration = 4;
            Track trackName = new Track(group + amount, name + amount, duration + amount);
            tracks.add(trackName);
        }
    }

    private void addAlbums() {
        for (int amount = 0; amount < 3; amount++) {
            String group = "Bushido Zho";
            String name = "No Bang! Hold On";
            int duration = 3;
            Album album = new Album(group + amount, name + amount, duration + amount);
            albums.add(album);
        }
    }

    private void addPlaylists() {
        for (int amount = 0; amount < 3; amount++) {
            String name = "Novelties";
            Playlist playlist = new Playlist(name + amount);
            playlists.add(playlist);
        }
    }

    private void addIntAlbums() {
        int numberAlbum = 0;
        for (int number = 0; number < 5; number++) {
            String group = "Lil Uzi Vert";
            String name = "Luv is Rage 2";
            int duration = 2;
            Track trackName = new Track(group + number, name + number, duration + number);
            albums.get(numberAlbum).getTracks().add(trackName);
        }
    }

    private void addInPlaylists() {
        int numberPlaylist = 0;
        for (int number = 0; number < 5; number++) {
            String group = "Yanix";
            String name = "Ghetto street show";
            int duration = 2;
            Track trackName = new Track(group + number, name + number, duration + number);
            playlists.get(numberPlaylist).getTracks().add(trackName);
        }
    }
}
