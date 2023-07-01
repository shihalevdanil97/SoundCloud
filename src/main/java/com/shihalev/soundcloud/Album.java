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
    private final List<String> categories;
    private final int duration;
    private boolean like = false;

    public Album(String name, String groupName, int duration) {
        this.name = name;
        this.groupName = groupName;
        this.duration = duration;
        this.tracks = new ArrayList<>();
        this.categories = new ArrayList<>();
    }

    public boolean equals(Object obj) {
        Album album = (Album) obj;
        return name == album.name && groupName == album.groupName && duration == album.duration;
    }

    public List<String> getCategories() {
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
}
