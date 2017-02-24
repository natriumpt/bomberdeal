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
    private String currentAction;

    public UserInput(Screen screen) {

        this.screen = screen;

    }

    @Override
    public void run() {

        while(true) {

            Key key = screen.readInput();

            if (key == null) {
                try {
                    Thread.sleep(10);
                    continue;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

                switch (key.getKind()) {

                    case NormalKey:

                        if (key.getCharacter() == ' ') {
                            playerAction = playerAction + PlayerActions.DEPLOY;
                        }

                        break;
                    case Escape:
                        System.exit(0);
                        break;
                    case ArrowLeft:
                        playerAction += PlayerActions.MOVELEFT;
                        System.out.println("cenas esquerda");
                        break;
                    case ArrowRight:
                        playerAction += PlayerActions.MOVERIGHT;
                        System.out.println("cenas direita");
                        break;
                    case ArrowUp:
                        playerAction += PlayerActions.MOVEUP;
                        System.out.println("cenas cima");
                        break;
                    case ArrowDown:
                        playerAction += PlayerActions.MOVEDOWN;
                        System.out.println("cenas baixo");
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


