package com.shihalev.soundcloud;

import java.util.ArrayList;
import java.util.List;

public class Album {
    private final String name;
    private final String groupName;
    private final List<Track> tracks;
    private final int duration;
    private boolean like;

    public Album(String name, String groupName, int duration) {
        this.name = name;
        this.groupName = groupName;
        this.duration = duration;
        this.tracks = new ArrayList<>();
        this.like = false;
    }

    public int getDuration() {
        return duration;
    }

    public void like() {
        this.like = true;
    }

    public String getName() {
        return name;
    }

    public List<Track> getTracks() {
        return tracks;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj){
            return true;
        }
        if(!(obj instanceof Album)){
            return false;
        }
        Album album = (Album) obj;
        return (name.equals(album.name)) &&
                (groupName.equals(album.groupName)) &&
                (duration == album.duration);
    }

    @Override
    public String toString() {
        return groupName + " " + name + " " + tracks;
    }

}
