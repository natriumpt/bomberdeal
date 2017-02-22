package org.academiadecodigo.bootcamp.Client.UserInput;

import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.screen.Screen;
import org.academiadecodigo.bootcamp.Client.Actions.PlayerActions;

import java.lang.management.PlatformLoggingMXBean;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by codecadet on 2/22/17.
 */
public class UserInput implements Runnable {

    private Screen screen;
    private String playerAction;

    public UserInput(Screen screen) {

        this.screen = screen;

    }

    @Override
    public void run() {

        Key key = screen.readInput();

        if (key != null) {

            switch (key.getKind()) {

                case NormalKey:

                    if(key.getCharacter() == ' ') {
                        playerAction = playerAction + PlayerActions.DEPLOY;
                    }

                    break;
                case Escape:
                    System.exit(0);
                    break;
                case ArrowLeft:
                    playerAction += PlayerActions.MOVELEFT;
                    break;
                case ArrowRight:
                    playerAction += PlayerActions.MOVERIGHT;
                    break;
                case ArrowUp:
                    playerAction += PlayerActions.MOVEUP;
                    break;
                case ArrowDown:
                    playerAction += PlayerActions.MOVEDOWN;
                    break;
                case Enter:
                    System.out.println("Enter");
                    break;
                case Unknown:
                    break;
                default:
                    System.out.println("Woah there!");
                    break;

            }

        }
    }
}

