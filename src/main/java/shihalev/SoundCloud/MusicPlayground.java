package shihalev.SoundCloud;

import shihalev.Interface.Sound;

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
            System.out.println(playlist.getName());
        }
    }

    private void printSounds() {
        for (Track sound : tracks) {
            System.out.println(sound.getName());
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

    private void printList() {
        for (Track name : tracks) {
            System.out.println(name.getName());
        }
    }

    private void listenSound() {
        System.out.println("Название трека");
        String nameSound = scanner.next();
        for (Track sound : tracks) {
            if (nameSound.equals(sound.getName())) {
                sound.listen();
                System.out.println("Поставить лайк ?");
                String yesOrNo = scanner.next();
                if (yesOrNo.equals("Yes")) {
                    sound.like();
                }
                return;
            }
        }
        System.out.println("Такого трека нет");
    }

    private void playlist() {
        if (playlists.isEmpty()) {
            System.out.println("Нет плейлистов");
            return;
        }
        printPlaylists();
        System.out.println("Выбрать No Плейлиста");
        int numberPlaylist = scanner.nextInt();
        if (searchPlaylist(numberPlaylist)) {
            playlists.get(numberPlaylist).printTrack();
            while (true) {
                System.out.println("Выбрать команду");
                String playlistCommand = scanner.next();
                switch (playlistCommand) {
                    case "turnSong":
                        turnSong(playlists.get(numberPlaylist));
                        break;
                    case "addInPlayLIst":
                        addInPlayLIst(numberPlaylist);
                        break;
                    case "mixPlayList":
                        playlists.get(numberPlaylist).mixPlaylist();
                        break;
                    case "likeTrack":
                        likeTrack(playlists.get(numberPlaylist));
                    case "Back":
                        return;

                }
            }
        } else {
            System.out.println("Плейлист не найден");
        }
    }


    private boolean searchPlaylist(int numberPlaylist) {
        for (int number = 0; number < playlists.size(); number++) {
            if (number == numberPlaylist - 1) {
                return true;
            }
        }
        return false;
    }

    private boolean searchSound(int numberSound) {
        for (int number = 0; number < tracks.size(); number++) {
            if (number == numberSound - 1) {
                return true;
            }
        }
        return false;
    }

    private void turnSong(Playlist playlist) {
        System.out.println("Введите номер трека");
        int numberTrack = scanner.nextInt();
        playlist.playTrack(numberTrack);
    }

    private void addInPlayLIst(int numberPlaylist) {
        if (searchPlaylist(numberPlaylist)) {
            printSounds();
            System.out.println("Какой добавить трек?");
            int numberSound = scanner.nextInt();
            if (searchSound(numberSound)) {
                for (int number = 0; number < tracks.size(); number++) {
                    if (number == numberSound - 1) {
                        playlists(number);
                    }
                }
            }
        }
    }

    public void likeTrack(Playlist playlist) {
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
