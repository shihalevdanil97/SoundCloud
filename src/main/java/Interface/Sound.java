package Interface;
import Track;
import java.util.List;

public interface Sound {
    void listenToTheSong();

    void likeSound(String name);
    void addTrack(String name, List<Track> nameList);

}



