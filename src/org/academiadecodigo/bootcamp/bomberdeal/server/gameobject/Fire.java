package org.academiadecodigo.bootcamp.bomberdeal.server.gameobject;

import org.academiadecodigo.bootcamp.bomberdeal.server.gamefield.Field;
import org.academiadecodigo.bootcamp.bomberdeal.server.gameobject.interfaces.Interactable;
import org.academiadecodigo.bootcamp.bomberdeal.server.gameobject.interfaces.Observable;
import org.academiadecodigo.bootcamp.bomberdeal.server.helper.TileType;

import java.util.Timer;
import java.util.TimerTask;

public class Fire implements Interactable {

    private Observable observer;
    private int x;
    private int y;
    private TileType tileType;

    public Fire(int x, int y) {

        this.x = x;
        this.y = y;
        tileType = TileType.FIRE;
        System.out.println(x + ":" + y);

        setTimer(1000);

    }

    public void attach(Observable observer) {
        this.observer = observer;
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
    public void setField(Field field) {

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

    @Override
    public void destroy() {
        observer.update(this); //to remove from interactables list
    }
}
