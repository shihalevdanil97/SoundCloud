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

    /**
     * Выводит список плейлистов
     */
    private void printPlaylists() {
        int number = 1;
        for (Playlist playlist : playlists) {
            System.out.println(number + ". " + playlist.getName());
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
        for (Track track : tracks) {
            if (track.getName().equals(name)) {
                System.out.println("Такое название уже есть в списке");
                return;
            }
        }
        System.out.println("Длина трека");
        int time = scanner.nextInt();

        Track track = new Track(group, name, time);
        tracks.add(track);
    }

    /**
     * Создает и добавляет альбом + проверяет нет ли такого же альбома
     */
    private void addAlbum() {
        System.out.println("Название группы");
        String nameGroup = scanner.next();
        System.out.println("Название Альбома");
        String nameAlbum = scanner.next();
        System.out.println("Длинна альбома");
        int duration = scanner.nextInt();
        Album album = new Album(nameGroup, nameAlbum, duration);
        for (Album numberAlbum: albums){
            if(numberAlbum.equals(album)){
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
        for (Playlist playlist : playlists) {
            if (playlist.getName().equals(name)) {
                System.out.println("Такой название уже есть");
                return;
            }
        }
        Playlist playlistName = new Playlist(name);
        playlists.add(playlistName);
    }

    /**
     * Создает и добавляет подкаст + проверяет нет ли такого же плейлиста
     */
    private void addPodcast() {
        System.out.println("Название подкаста");
        String name = scanner.next();
        for (Podcast podcast : podcasts) {
            if (podcast.getName().equals(name)) {
                System.out.println("Такое имя уже есть в списке");
                return;
            }
        }
        System.out.println("Введите время");
        int duration = scanner.nextInt();
        Podcast podcastName = new Podcast(name, duration);
        System.out.println("Жанр подкаста один или несколько");
        int amount = scanner.nextInt();
        for (int categories = 0; categories < amount - 1; categories++) {
            String category = scanner.next();
            podcastName.getCategories().add(category);
        }
    }

    /**
     * Выводит список подкастов
     */
    private void printPodcast() {
        int number = 1;
        for (Podcast podcast : podcasts) {
            System.out.println(number + ". " + podcast.getName());
        }
    }

    /**
     * Выводит список треков
     */
    private void printSounds() {
        int trackNumber = 1;
        for (Sound name : tracks) {
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
     * @param numberPlaylist номер нужного
     */
    private boolean isPlaylistPresent(int numberPlaylist) {
        if (numberPlaylist > playlists.size() || numberPlaylist < 1) {
            return true;
        }
        return false;
    }

    /**
     * Воспроизводит трек из плейлиста
     * @param playlist конкретный плейлист
     */
    private void turnSong(Playlist playlist) {
        System.out.println("Введите номер трека");
        int numberTrack = scanner.nextInt();
        playlist.playTrack(numberTrack);
    }

    /**
     * Добавляет трек в плейлист
     * @param numberPlaylist порядковый номер плейлиста
     */
    private void addInPlaylist(int numberPlaylist) {
        if (numberPlaylist < 1 || numberPlaylist > playlists.size()) {
            System.out.println("Какой добавить трек?");
            int numberSound = scanner.nextInt();
            if (numberSound < 1 || numberSound > tracks.size()) {
                playlists.get(numberPlaylist).getTracks().add(tracks.get(numberSound));
            }
        }
    }
    /**
     * Выбор какому трекку поставить лайк
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
