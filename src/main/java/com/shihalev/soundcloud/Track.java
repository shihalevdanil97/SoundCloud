package com.shihalev.soundcloud;

import com.shihalev.soundcloud.sound.Sound;

import java.util.List;

public class Track implements Sound {

    private String group;
    private String name;
    private int duration;
    private boolean like;

    private int number;

    private static int nextNumber = 1;


    public Track(String group, String name, int duration) {
        this.group = group;
        this.name = name;
        this.duration = duration;
        this.like = false;
        number = nextNumber;
        nextNumber++;
    }

    public String getGroup() {
        return group;
    }

    public String getName() {
        return name;
    }

    public int getDuration() {
        return duration;
    }

    public int getNumber() {
        return number;
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
