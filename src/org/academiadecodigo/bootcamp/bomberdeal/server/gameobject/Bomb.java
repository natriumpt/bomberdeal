package org.academiadecodigo.bootcamp.bomberdeal.server.gameobject;

import org.academiadecodigo.bootcamp.bomberdeal.server.gameobject.interfaces.Collidable;
import org.academiadecodigo.bootcamp.bomberdeal.server.gameobject.interfaces.DestroyableByFire;
import org.academiadecodigo.bootcamp.bomberdeal.server.gameobject.interfaces.Interactable;
import org.academiadecodigo.bootcamp.bomberdeal.server.gameobject.interfaces.Observable;
import org.academiadecodigo.bootcamp.bomberdeal.server.helper.TileType;

import java.util.Timer;
import java.util.TimerTask;

public class Bomb implements Interactable, DestroyableByFire, Collidable {

    private Observable observer;
    private int x;
    private int y;
    private TileType tileType;
    private int range;
    private final int BOMB_TIMER = 3000; // in ms

    public Bomb(int x, int y) {
        this.x = x;
        this.y = y;
        tileType = TileType.BOMB;
        range = 3;

        setTimer(BOMB_TIMER);

        observer.update(this); //to remove bomb from interactables list
    }

    private void setTimer(int delay) {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                destroy();
            }
        }, delay);
    }

    @Override
    public void destroy() {

        for (int i = 1; i <= range; i++) {
            Fire fireRight = new Fire(x + i, y);
            updateObserver(fireRight);
            Fire fireLeft = new Fire(x - i, y);
            updateObserver(fireLeft);
            Fire fireDown = new Fire(x, y + i);
            updateObserver(fireDown);
            Fire fireUp = new Fire(x, y - i);
            updateObserver(fireUp);
        }
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public TileType getTileType() {
        return tileType;
    }

    private void updateObserver(Fire fire) {
        fire.attach(observer);
        observer.update(fire);
    }

    public void attach(Observable observer) {
        this.observer = observer;
    }

    public void setRange() {
        range++;
    }
}


