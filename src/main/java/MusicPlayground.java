
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MusicPlayground {
    public void start() {
        System.out.println("Это музыкальная площадка");
        System.out.println("SoundCloud");
        System.out.println("Что бы продолжить, напишите как к вам обращаться");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.next();
        System.out.println("Добро пожаловать" + name);

        List<Album> albums = new ArrayList<>();
        List<Track> tracks = new ArrayList<>();
        ArrayList<Playlist> playList = new ArrayList<>();

        while (true) {
            System.out.println("Выбирай команду");
            String command = scanner.next();
            switch (command) {
                case "addSound":
                    System.out.println("Название группы");
                    String group = scanner.next();
                    System.out.println("Песня");
                    String track = scanner.next();
                    System.out.println("Длина трека");
                    int time = scanner.nextInt();

                    Track sound = new Track(group, track, time);
                    tracks.add(sound);
                    break;

                case "addAlbum":
                    System.out.println("Название группы");
                    String nameGroup = scanner.next();
                    System.out.println("Название Альбома");
                    String nameAlbum = scanner.next();
                    System.out.println("Сколько треков");
                    int amountTrack = scanner.nextInt();

                    Album album = new Album(nameGroup, nameAlbum, amountTrack);
                    albums.add(album);
                    break;

                case "addPlayList":
                    System.out.println("Название плейлиста");
                    System.out.println("название песни");
                    break;

                case "printList":
                    for (Track n : tracks) {
                        System.out.println(n.getName());
                    }
                    break;

                case "listenSound":
                    System.out.println("Название трека");
                    String nameSound = scanner.next();
                    for (Track element : tracks)
                        if (nameSound.equals(element.getName())) {
                            element.listenToTheSong();
                            System.out.println("Поставить лайк ?");
                            String yesOrNou = scanner.next();
                            if (yesOrNou.equals("Yes")) {
                                element.likeSound();
                            }
                        } else System.out.println("Трек не найден");
                    break;

                case "playList":
                    if (playList.size() == 0) {
                        System.out.println("Создать плейлист ?");
                        String answer = scanner.next();
                        if (answer.equals("yes")) {
                            String nameList = scanner.next();
                            Playlist playList1 = new Playlist(nameList);
                            if (answer.equals("no")) {
                                System.out.println("Выбрать плейлсит");
                                printPlayLists(playList);
                                String list = scanner.next();
                                for (Playlist element : playList) {
                                    if (list.equals(element.getName())) {
                                        element.printSounds();
                                        while (true) {
                                            System.out.println("Выбрать команду");
                                            String commandPlayList = scanner.next();
                                            switch (commandPlayList) {
                                                case "turnSong":
                                                    System.out.println("Введите номер трека");
                                                    int numberTrack = scanner.nextInt();
                                                    element.turnSong(numberTrack);
                                                    break;
                                                case "addInPlayLIst":
                                                    printSounds(tracks);
                                                    System.out.println("Написать название трека");
                                                    String nameTrack = scanner.next();
                                                    element.addTrack(nameTrack, tracks);
                                                    break;
                                                case "mixPlayList":
                                                    element.MixPlaylist();
                                                    break;
                                                case "likeTrack":
                                                    element.printSounds();
                                                    System.out.println("Какому треку поставить лайк ?");
                                                    String likeTrack = scanner.next();
                                                    for (Track track1 : tracks){
                                                        if (likeTrack.equals(track1)){
                                                            track1.likeSound();
                                                        }else System.out.println("Такого трека нетlsls");
                                                    }
                                                    break;
                                                case "exit":
                                                    return;
                                            }
                                        }
                                    }
                                }
                            }
                        } else
                            for (Track n : tracks) {
                                System.out.println(n.getName());
                            }

                    }

                case "exit":
                    return;
            }
        }
    }

    public static void printPlayLists(ArrayList<Playlist> name) {
        for (Playlist element : name) {
            System.out.println(element.getName());
        }
    }

    public static void printSounds(List<Track> name){
        for (Track sound : name){
            System.out.println(sound.getName());
        }
    }


}