package org.academiadecodigo.bootcamp.gamelogic;

/**
 * Created by codecadet on 2/21/17.
 */
public class Bomb {

    private int radius;
    private int timer;
    private PositionServer position;
    private CollisionChecker collisionChecker;


    public Bomb(int radius) {
        this.radius = radius;
    }

    public void deploy(int posX, int posY){
        position.setPosX(posX);
        position.setPosY(posY);
        position.setVisible();
    }

    public void explode(){ // timertask
        position.setVisible();
        grid.explodeArea(radius);//
    }
}
