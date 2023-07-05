package com.shihalev.soundcloud;

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

    private void addSound() {
        System.out.println("Название группы");
        String group = scanner.next();
        System.out.println("Песня");
        String name = scanner.next();
        System.out.println("Длина");
        int time = scanner.nextInt();

        Track newTrack = new Track(group, name, time);
        if (tracks.contains(newTrack)) {
            System.out.println("Такой трек уже есть");
            return;
        }

        tracks.add(newTrack);
        System.out.println("Трек добавлен");
    }

    private void printSounds() {
        int number = 1;
        for (Track track : tracks) {
            System.out.println(number + ". " + track.getGroup() + " " + track.getName());
            number++;
        }
    }

    private void listenSound() {
        System.out.println("Номер трека");
        int trackIndex = scanner.nextInt() - 1;
        if (trackIndex >= 0 && trackIndex < tracks.size()) {
            tracks.get(trackIndex).listen();
            System.out.println("Поставить лайк ?");
            String yesOrNo = scanner.next();
            if (yesOrNo.equals("Yes")) {
                System.out.println("Лайк поставлен");
                tracks.get(trackIndex).like();
            }
        } else {
            System.out.println("Такого трека нет");
        }
    }

    private void addAlbum() {
        System.out.println("Название группы");
        String groupName = scanner.next();
        System.out.println("Название Альбома");
        String albumName = scanner.next();
        System.out.println("Длинна альбома");
        int duration = scanner.nextInt();

        Album newAlbum = new Album(groupName, albumName, duration);
        if (albums.contains(newAlbum)) {
            System.out.println("Такой альбом уже есть");
            return;
        }
        albums.add(newAlbum);
        System.out.println("Альбом успешно добавлен");
    }

    private void playlist() {
        if (playlists.isEmpty()) {
            System.out.println("Нет плейлистов");
            return;
        }
        printPlaylists();
        System.out.println("Выбрать No Плейлиста");
        final int playlistIndex = scanner.nextInt() - 1;
        if (isPlaylistPresent(playlistIndex)) {
            printPlaylistTracks(playlistIndex);
            while (true) {
                System.out.println("Выбрать команду");
                String playlistCommand = scanner.next();
                switch (playlistCommand) {
                    case "turnSong":
                        printPlaylistTracks(playlistIndex);
                        playSong(playlists.get(playlistIndex));
                        break;
                    case "addInPlaylist":
                        addInPlaylist(playlistIndex);
                        break;
                    case "mixPlaylist":
                        mixPlaylist(playlists.get(playlistIndex));
                        printPlaylistTracks(playlistIndex);
                        break;
                    case "likeTrack":
                        likeTrack(playlists.get(playlistIndex));
                    case "back":
                        return;
                }
            }
        } else {
            System.out.println("Плейлист не найден");
        }
    }

    private void printPlaylists() {
        int number = 1;
        for (Playlist playlist : playlists) {
            System.out.println(number + ". " + playlist.getName());
            number++;
        }
    }

    private boolean isPlaylistPresent(int playlistIndex) {
        return playlistIndex >= 0 && playlistIndex < playlists.size();
    }

    private void printPlaylistTracks(int playlistIndex) {
        if (playlists.get(playlistIndex).getTracks().isEmpty()) {
            System.out.println("Плейлист путой");
            return;
        }
        int number = 1;
        for (Track track : playlists.get(playlistIndex).getTracks()) {
            System.out.println(number + ". " + track.getName());
            number++;
        }
    }

    private void playSong(Playlist playlist) {
        if (playlist.getTracks().isEmpty()) {
            return;
        }
        System.out.println("Введите номер трека");
        int trackIndex = scanner.nextInt() - 1;
        playTrack(trackIndex, playlist);
    }

    private void addInPlaylist(int playlistIndex) {
        if (playlistIndex < 0 || playlistIndex > playlists.size()) {
            return;
        }
        printSounds();
        System.out.println("Какой добавить трек?");
        int soundNumber = scanner.nextInt() - 1;
        if (soundNumber < 0 || soundNumber > tracks.size()) {
            return;
        }
        playlists.get(playlistIndex)
                .getTracks()
                .add(
                        tracks.get(soundNumber));
    }

    public void mixPlaylist(Playlist playlist) {
        Collections.shuffle(playlist.getTracks());
    }


    private void addPlaylist() {
        System.out.println("Название плейлиста");
        String name = scanner.next();
        Playlist newPlaylist = new Playlist(name);

        if (playlists.contains(newPlaylist)) {
            System.out.println("Такой альбом уже есть");
            return;
        }
        playlists.add(newPlaylist);
        System.out.println("Плейлист добавлен");
    }

    private void likeTrack(Playlist playlist) {
        System.out.println("Какому треку поставить лайк ?");
        int trackIndex = scanner.nextInt() - 1;
        if (trackIndex >= 0 && trackIndex < tracks.size()) {
            return;
        }
        System.out.println("Вы поставили лайк");
        playlist.getTracks()
                .get(trackIndex)
                .like();
    }

    private void addPodcast() {
        System.out.println("Название подкаста");
        String name = scanner.next();
        System.out.println("Введите время");
        int duration = scanner.nextInt();

        Podcast newPodcast = new Podcast(name, duration);

        if (podcasts.contains(newPodcast)) {
            System.out.println("такой подкаст уже есть");
            return;
        }
        System.out.println("Подкаст добавлен");
        podcasts.add(newPodcast);
    }

    private void printPodcast() {
        int number = 1;
        for (Podcast podcast : podcasts) {
            System.out.println(number + ". " + podcast.getName());
            number++;
        }
    }

    private void playTrack(int trackIndex, Playlist playlist) {
        if (trackIndex >= 0 && trackIndex < playlist.getTracks().size()) {
            tracks.get(trackIndex).listen();
        }
        System.out.println("Такого трека нет");
    }

    public void fillTestData() {
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
            tracks.add(
                    new Track(group + amount, name + amount, duration + amount));

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
