package org.academiadecodigo.bootcamp.bomberdeal.server.gameobject;

import org.academiadecodigo.bootcamp.bomberdeal.server.gameobject.interfaces.Interactable;
import org.academiadecodigo.bootcamp.bomberdeal.server.gameobject.interfaces.Observable;
import org.academiadecodigo.bootcamp.bomberdeal.server.helper.TileType;

import java.util.TimerTask;

/**
 * Created by betacodecadet on 24/02/17.
 */
public class Fire implements Interactable {

    private Observable observer;
    private int x;
    private int y;
    private TileType tileType;

    public Fire(int x, int y){
        this.x = x;
        this.y = y;
        tileType = TileType.FIRE;

        TimerTask timer = new TimerTask() {
            @Override
            public void run() {

            }
        };
        destroy();
        observer.update(this);
    }

    public void attach(Observable observer){
        this.observer = observer;
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
        observer.update(this);
    }
}
