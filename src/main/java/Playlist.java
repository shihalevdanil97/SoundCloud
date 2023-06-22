import Interface.Sound;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Playlist implements Sound {
    private String name;

    private List<Track> playList = new ArrayList<>();


    Playlist(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void printSounds() {
        for (Track track : playList) {
            System.out.println(track.getName());
        }
    }

    public void turnSong(int numberSong) {
        if (numberSong <= playList.size()) {
            for (Track track : playList) {
                track.listenToTheSong();
            }
        } else System.out.println("track не найден");
    }


    public void MixPlaylist() {
        Collections.shuffle(playList);
        for (Track track : playList) {
            System.out.println(track.getName());
        }
    }

    public void addTrack(String name, List<Track> nameList) {
        for (Track track : playList) {
            if (name.equals(track.getName())) {
                System.out.println("Такой трек уже есть");
            } else
                for (Track song : nameList) {
                    if (name.equals(song.getName())) {
                        playList.add(song);
                    }
                }
        }
    }
    @Override
    public void likeSound(String name){
        for (Track track : playList){
            if (name.equals(track)){
                track.likeSound();
            }
        }
    }
}

