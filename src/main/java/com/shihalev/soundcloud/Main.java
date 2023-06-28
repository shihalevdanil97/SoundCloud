package com.shihalev.soundcloud;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        MusicPlayground musicPlayground = new MusicPlayground(new Scanner(System.in));
        musicPlayground.start();
    }
}
