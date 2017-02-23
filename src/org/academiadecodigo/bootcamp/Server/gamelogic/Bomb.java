package org.academiadecodigo.bootcamp.Server.gamelogic;

import org.academiadecodigo.bootcamp.Server.GridServer;

/**
 * Created by codecadet on 2/21/17.
 */
public class Bomb {

    private int radius;
    private int timer;
    private PositionServer position;
    private CollisionChecker collisionChecker;
    private GridServer grid; //MUITO ESTRANHO

    public Bomb() {
        this.radius = 1;
    }

    public void deploy(int posX, int posY){
        position.setPosX(posX);
        position.setPosY(posY);
        position.setVisible();
    }

    public void explode(){ // timertask
        position.setVisible();
        grid.explodeArea(radius, this);//
    }

    private void increaseRadius(){
        this.radius += 1;
    }
    public PositionServer getPosition() {
        return position;
    }
}
