package org.academiadecodigo.bootcamp.Client.UserInput;

import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.screen.Screen;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by codecadet on 2/22/17.
 */
public class UserInput implements Runnable {

    private Screen screen;
    private Key key;

    public UserInput(Screen screen) {

        this.screen = screen;

    }

    private Key getKey() {
        return key;
    }

    @Override
    public void run() {

        Key key = screen.readInput();

        while (true) {

            if (key == null) {

                TimerTask task = new TimerTask() {

                    @Override
                    public void run() {
                        Key key = getKey();

                        key = screen.readInput();
                    }

                };

                Timer timer = new Timer(true);
                timer.schedule(task, 0);

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
