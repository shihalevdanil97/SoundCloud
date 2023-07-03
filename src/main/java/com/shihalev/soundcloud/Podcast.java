package com.shihalev.soundcloud;

import com.shihalev.soundcloud.sound.Sound;

import java.util.ArrayList;
import java.util.List;

/**
 * Обьект класса <code>Podcast<code>
 * имитирует Подкаст
 * пункт управления конкретным подкастом
 */
public class Podcast implements Sound {
    private final String name;
    private final int duration;

    private boolean like;

    private final List<String> categories;

    public Podcast(String name, int duration) {
        this.name = name;
        this.duration = duration;
        this.categories = new ArrayList<>();
        this.like = false;
    }

    public List<String> getCategories() {
        return categories;
    }

    public int getDuration() {
        return duration;
    }

    @Override
    public void listen() {
        System.out.println("Вы слушаете подкаст");
    }

    @Override
    public void like() {
        this.like = true;
    }

    @Override
    public String getName() {
        return name;
    }

    public void pause() {
        System.out.println("Пауза");
    }

    public void play() {
        System.out.println("Воспроизвести");
    }

    @Override
    public boolean equals(Object obj) {
        Podcast podcast = (Podcast) obj;
        return (name.equals(podcast.name) && (duration == podcast.duration));
    }

    @Override
    public String toString() {
        return name + " " + categories;
    }
}
