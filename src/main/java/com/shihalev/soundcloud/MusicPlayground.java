package com.shihalev.soundcloud;

import com.shihalev.soundcloud.sound.Sound;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Обьект класса <code>MusicPlayGround<code>
 * имитирует музыкальную платформу
 * является Панелью Управления платформой
 */
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
        System.out.println("Добро пожаловать" + name);

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

    private void songRating(Track track) {
        int listenAmount = 0;
        int rating = 0;
        for (Track sound : tracks) {
            listenAmount = listenAmount + sound.getListenAmount();
        }
        rating = (listenAmount / (listenAmount / 100) * (track.getListenAmount()));
    }

    private void addSounds() {
        for (int amount = 0; amount < 10; amount++) {
            String group = "Og";
            String name = "Cloop";
            int time = 4;
            Track trackName = new Track(group, name, time);
            tracks.add(trackName);
            name = name + amount;
            time++;
        }
    }

    /**
     * Выводит список плейлистов
     */
    private void printPlaylists() {
        int number = 1;
        for (Playlist playlist : playlists) {
            System.out.println(number + ". " + playlist.getName());
            number++;
        }
    }

    /**
     * Создает и добавляет песню + проверяет нет ли такого же трека
     */
    private void addSound() {
        System.out.println("Название группы");
        String group = scanner.next();
        System.out.println("Песня");
        String name = scanner.next();
        System.out.println("Длина трека");
        int time = scanner.nextInt();
        Track trackName = new Track(group, name, time);
        for (Track track : tracks) {
            if (track.equals(trackName)) {
                System.out.println("Такое название уже есть в списке");
                return;
            }
        }
        tracks.add(trackName);
    }

    /**
     * Создает и добавляет альбом + проверяет нет ли такого же альбома
     */
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
        albums.add(album);
    }

    /**
     * Создает и добавляет плейлист + проверяет нет ли такого же плейлиста
     */
    private void addPlaylist() {
        System.out.println("Название плейлиста");
        String name = scanner.next();
        Playlist playlistName = new Playlist(name);
        for (Playlist playlist : playlists) {
            if (playlist.equals(name)) {
                System.out.println("Такой название уже есть");
                return;
            }
        }
        playlists.add(playlistName);
    }

    /**
     * Создает и добавляет подкаст + проверяет нет ли такого же плейлиста
     */
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
        System.out.println("Жанр подкаста один или несколько");
        int amount = scanner.nextInt();
        for (int categories = 0; categories < amount - 1; categories++) {
            String category = scanner.next();
            podcastName.getCategories().add(category);
        }
    }

    private void сhooseAlbumGenre(Album album) {
        System.out.println("Жанр альбома один или несколько");
        int amount = scanner.nextInt();
        for (int categories = 0; categories < amount; categories++) {
            System.out.println("Введи номер жанра");
            int genreAlbum = scanner.nextInt();
            if (genreAlbum < 1 && genreAlbum > 9) {
                System.out.println("Некоректный ввод");
            }

            album.getCategories().add(genre(genreAlbum));
        }
    }

    /**
     * Выводит список подкастов
     */
    private void printPodcast() {
        int number = 1;
        for (Podcast podcast : podcasts) {
            System.out.println(number + ". " + podcast.getName());
            number++;
        }
    }

    /**
     * Выводит список треков
     */
    private void printSounds() {
        int trackNumber = 1;
        for (Track name : tracks) {
            System.out.println(trackNumber + ". " + name.getName());
            trackNumber++;
        }
    }

    /**
     * Включает трек из списка + поставить лайк
     */
    private void listenSound() {
        System.out.println("Номер трека");
        int number = scanner.nextInt();
        if (number > tracks.size() || number < 1) {
            tracks.get(number - 1).listen();
            System.out.println("Поставить лайк ?");
            String yesOrNo = scanner.next();
            if (yesOrNo.equals("Yes")) {
                tracks.get(number - 1).like();
            }
        } else {
            System.out.println("Такого трека нет");
        }
    }


    /**
     * Пункт управления Плейлиста
     */
    private void playlist() {
        if (playlists.isEmpty()) {
            System.out.println("Нет плейлистов");
            return;
        }
        printPlaylists();
        System.out.println("Выбрать No Плейлиста");
        int number = scanner.nextInt();
        if (isPlaylistPresent(number)) {
            playlists.get(number).printTracks();
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


    /**
     * Проверка на наличие плейлиста
     *
     * @param playlistNumber номер нужного
     */
    private boolean isPlaylistPresent(int playlistNumber) {
        if (playlistNumber > playlists.size() || playlistNumber < 1) {
            return true;
        }
        return false;
    }

    /**
     * Воспроизводит трек из плейлиста
     *
     * @param playlist конкретный плейлист
     */
    private void turnSong(Playlist playlist) {
        System.out.println("Введите номер трека");
        int numberTrack = scanner.nextInt();
        playlist.playTrack(numberTrack);
    }

    /**
     * Добавляет трек в плейлист
     *
     * @param playlistNumber порядковый номер плейлиста
     */
    private void addInPlaylist(int playlistNumber) {
        if (playlistNumber < 1 || playlistNumber > playlists.size()) {
            System.out.println("Какой добавить трек?");
            int soundNumber = scanner.nextInt();
            if (soundNumber < 1 || soundNumber > tracks.size()) {
                playlists.get(playlistNumber).getTracks().add(tracks.get(soundNumber));
            }
        }
    }

    /**
     * Выбор какому трекку поставить лайк
     *
     * @param playlist кокретный плейлист
     */
    private void likeTrack(Playlist playlist) {
        playlist.printTracks();
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
