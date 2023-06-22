import Interface.Sound;

public class Track implements Sound {

    private String group;
    private String name;
    private int time;

    private boolean like = false;


    public Track(String group, String name, int time) {
        this.group = group;
        this.name = name;
        this.time = time;
    }
    @Override
    public void likeSound() {
        like = true;
    }

    public String getGroup() {
        return group;
    }

    public String getName() {
        return name;
    }

    public int getTime() {
        return time;
    }

    public void listenToTheSong() {
        System.out.println("ЛаЛаЛа играет песня");
    }


    public void pause() {
        System.out.println("Пауза");
    }

    public void play() {
        System.out.println("Воспроизвести");
    }
}
