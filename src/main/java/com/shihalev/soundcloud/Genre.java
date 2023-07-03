package com.shihalev.soundcloud;

public enum Genre {

    HIP_HOP(1),
    POP(2),
    ROCK(3),
    JAZZ(4),

    COMEDY(5),
    HORROR(6),
    DETECTIVE(7),
    DRAMA(8),
    OTHER(9);

    private int number;

    Genre(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }
}
