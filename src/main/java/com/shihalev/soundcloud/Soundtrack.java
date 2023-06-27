package com.shihalev.soundcloud;

public class Soundtrack implements com.shihalev.soundcloud.sound.Soundtrack {

    private String group;
    private String name;
    private int duration;
    private boolean like;

    private int number;



    public Soundtrack(String group, String name, int duration) {
        this.group = group;
        this.name = name;
        this.duration = duration;
        this.like = false;
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

    public int getNumber() {
        return number;
    }

    public void setNumber(int number){
        this.number = number;
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
