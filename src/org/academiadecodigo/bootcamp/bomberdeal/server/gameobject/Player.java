package org.academiadecodigo.bootcamp.bomberdeal.server.gameobject;

import org.academiadecodigo.bootcamp.bomberdeal.server.gameobject.interfaces.Observable;

import java.util.Timer;
import java.util.TimerTask;


public class Player {

    private Observable observer;
    private int x;
    private int y;
    private boolean onCooldown;
    private Timer cooldownTimer;

    public Player(int x, int y, Observable observer) {
        this.observer = observer;
        this.x = x;
        this.y = y;
        cooldownTimer = new Timer();

    }

    private void beginCooldown() {
        onCooldown = true;
        cooldownTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                onCooldown = false;
            }
        }, 50);
    }

    public void move(Direction direction) {
        if (!onCooldown) {
            //TODO: implement collision check
            switch (direction) {
                case NORTH:
                    y--;
                    break;
                case SOUTH:
                    y++;
                    break;
                case WEST:
                    x--;
                    break;
                case EAST:
                    x++;
                    break;
            }
            beginCooldown();
        }
    }

    public void deploy() {
        Bomb bomb = new Bomb(x, y);
        bomb.attach(observer);
        notifyAll(bomb);

        if (!onCooldown) {
            // TODO: Implement action here
            beginCooldown();
        }
        throw new UnsupportedOperationException();

    }

    private enum Direction {
        NORTH,
        SOUTH,
        WEST,
        EAST
    }

    private void notifyAll(Bomb bomb) {

        observer.update(bomb);

    }

}
