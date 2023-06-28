package shihalev.SoundCloud;

import java.util.ArrayList;
import java.util.List;

public class Album {
    private String name;

    private String groupName;

    private List<Track> tracks;

    public Album(String name, String groupName) {
        this.name = name;
        this.groupName = groupName;
        this.tracks = new ArrayList<>();
    }
}
