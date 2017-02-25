package org.academiadecodigo.bootcamp.bomberdeal.server.gameobject;

import org.academiadecodigo.bootcamp.bomberdeal.server.gameobject.interfaces.Collidable;
import org.academiadecodigo.bootcamp.bomberdeal.server.gameobject.interfaces.DestroyableByFire;
import org.academiadecodigo.bootcamp.bomberdeal.server.gameobject.interfaces.Interactable;
import org.academiadecodigo.bootcamp.bomberdeal.server.gameobject.interfaces.Observable;
import org.academiadecodigo.bootcamp.bomberdeal.server.helper.TileType;

import java.util.TimerTask;

/**
 * Created by betacodecadet on 24/02/17.
 */
public class Bomb implements Interactable, DestroyableByFire, Collidable{

    private Observable observer;
    private int x;
    private int y;
    private TileType tileType;
    private int range;

    public Bomb(int x, int y){
        this.x = x;
        this.y = y;
        tileType = TileType.BOMB;

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
        for(int row = 0; row<range; row++){
            createVerticalFire(row);
        }

        for (int col = 0; col<range; col++){
            createHorizontalFire(col);
        }

    }

    private void createHorizontalFire(int col) {
        Fire horizontalFire = new Fire(x+col, y);
        horizontalFire.attach(observer);
        observer.update(horizontalFire);
        Fire horizontalFire1 = new Fire(x-col,y);
        horizontalFire1.attach(observer);
        observer.update(horizontalFire1);
    }

    private void createVerticalFire(int row) {
        Fire verticalFire = new Fire(x,y+row);
        verticalFire.attach(observer);
        observer.update(verticalFire);
        Fire verticalFire1 = new Fire(x,y-row);
        verticalFire1.attach(observer);
        observer.update(verticalFire1);
    }

    public void setRange() {
        range++;
    }
}
