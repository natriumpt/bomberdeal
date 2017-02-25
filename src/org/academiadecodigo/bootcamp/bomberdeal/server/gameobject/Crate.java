package org.academiadecodigo.bootcamp.bomberdeal.server.gameobject;

import org.academiadecodigo.bootcamp.bomberdeal.server.gameobject.interfaces.Collidable;
import org.academiadecodigo.bootcamp.bomberdeal.server.gameobject.interfaces.DestroyableByFire;
import org.academiadecodigo.bootcamp.bomberdeal.server.gameobject.interfaces.Interactable;
import org.academiadecodigo.bootcamp.bomberdeal.server.gameobject.interfaces.Observable;
import org.academiadecodigo.bootcamp.bomberdeal.server.helper.TileType;
import org.academiadecodigo.bootcamp.bomberdeal.server.helper.Util;

/**
 * Created by betacodecadet on 24/02/17.
 */
public class Crate implements Collidable, DestroyableByFire, Interactable{

    private Observable observer;
    private int x;
    private int y;
    private TileType tileType;

    public Crate(int x, int y){
        this.x = x;
        this.y = y;
        tileType = TileType.CRATE;
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
       if(Util.trueWithProb(0.6)){
           PowerUp powerUp = new PowerUp();
       }
        observer.update(this);
    }
}
