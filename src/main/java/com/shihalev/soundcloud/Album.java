package com.shihalev.soundcloud;

import java.util.ArrayList;
import java.util.List;

/**
 * Обьект класса <code>Album<code>
 * имитирует Альбом
 * Пункт управления конкретным альбомом
 */

public class Album {
    private final String name;
    private final String groupName;
    private final List<Track> tracks;
    private final List<Enum> categories;
    private final int duration;
    private boolean like = false;

    public Album(String name, String groupName, int duration) {
        this.name = name;
        this.groupName = groupName;
        this.duration = duration;
        this.tracks = new ArrayList<>();
        this.categories = new ArrayList<>();
        tracks.add(new Track("Factor2", "Love", 3));
        tracks.add(new Track("Factor2", "Love", 3));
        tracks.add(new Track("Famaly", "Like", 2));
        tracks.add(new Track("Bookin", "Bad", 3));
        tracks.add(new Track("Diego", "Forlan", 4));
        tracks.add(new Track("Sex", "Beach", 5));
        tracks.add(new Track("Scorpions", "Animal", 2));
        tracks.add(new Track("Double", "Fight", 4));
    }

    public List<Enum> getCategories() {
        return categories;
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

    @Override
    public boolean equals(Object obj) {
        Album album = (Album) obj;
        return (name.equals(album.name)) &&
                (groupName.equals(album.groupName)) &&
                (duration == album.duration);
    }

    @Override
    public String toString() {
        return groupName + " " + name + " " + categories + " " + tracks;
    }

}
