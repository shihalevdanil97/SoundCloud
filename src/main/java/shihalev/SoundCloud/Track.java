package shihalev.SoundCloud;

import shihalev.Interface.Sound;

public class Track implements Sound {

    private String group;
    private String name;
    private int duration;
    private boolean like;

    public Track(String group, String name, int duration) {
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
