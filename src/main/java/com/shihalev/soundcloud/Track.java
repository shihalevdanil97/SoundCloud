package com.shihalev.soundcloud;

import com.shihalev.soundcloud.sound.Sound;
/**
 * Обьект класса <code>Track<code>
 * имитирует песню
 * Пункт управления конкретной песни
 */

public class Track implements Sound {

    private final String group;
    private final String name;
    private final int duration;
    private boolean like;

    public Track(String group, String name, int duration) {
        this.group = group;
        this.name = name;
        this.duration = duration;
        this.like = false;
    }
    @Override
    public boolean equals(Object obj) {
        Track track = (Track) obj;
        return name == track.name && group == track.getGroup() && duration == track.getDuration();
    }
    public String getGroup() {
        return group;
    }

    @Override
    public String getName() {
        return name;
    }

    public int getDuration() {
        return duration;
    }

    @Override
    public void listen() {
        System.out.println("ЛаЛаЛа играет песня");
    }

    @Override
    public void like() {
        this.like = true;
    }


    public void pause() {
        System.out.println("Пауза");
    }

    public void play() {
        System.out.println("Воспроизвести");
    }


}
