package com.github.natriumpt.bomberdeal.Client;

import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.screen.Screen;

public class Controller implements Runnable {

    private Screen screen;

    public Controller(Screen screen) {
        this.screen = screen;
    }

    @Override
    public void run() {

        Key key = screen.readInput();

        while (true) {
            while (key == null) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                key = screen.readInput();
            }
            switch (key.getKind()) {
                case NormalKey:
                    System.out.println(key.getCharacter());
                    break;
                case Escape:
                    System.out.println("Escape");
                    System.exit(0);
                    break;
                case ArrowLeft:
                    System.out.println("Left");
                    break;
                case ArrowRight:
                    System.out.println("Right");
                    break;
                case ArrowUp:
                    System.out.println("Up");
                    break;
                case ArrowDown:
                    System.out.println("Down");
                    break;
                case Enter:
                    System.out.println("Enter");
                    break;
                case Unknown:
                    break;
            }

        }

    }

}
