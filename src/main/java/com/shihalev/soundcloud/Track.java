package com.shihalev.soundcloud;

import com.shihalev.soundcloud.sound.Sound;

import java.util.ArrayList;
import java.util.List;

public class Track implements Sound {

    private final String group;
    private final String name;
    private final int duration;
    private boolean like;
    private int listenAmount;

    public Track(String group, String name, int duration) {
        this.group = group;
        this.name = name;
        this.duration = duration;
        this.like = false;
        this.listenAmount = 0;
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
    public int getListenAmount(){
        return listenAmount;
    }

    @Override
    public void listen() {
        System.out.println("ЛаЛаЛа играет песня");
        listenAmount++;
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

    @Override
    public boolean equals(Object obj) {
        if(this == obj){
            return true;
        }
        if(!(obj instanceof Track)){
            return false;
        }
        Track track = (Track) obj;
        return (name.equals(track.name)) &&
                (group.equals(track.group)) &&
                (duration == track.duration);
    }

    @Override
    public String toString() {
        return group + " " + name;
    }

}
