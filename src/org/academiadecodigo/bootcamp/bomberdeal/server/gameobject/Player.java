package org.academiadecodigo.bootcamp.bomberdeal.server.gameobject;

import org.academiadecodigo.bootcamp.bomberdeal.server.GameCore;
import org.academiadecodigo.bootcamp.bomberdeal.server.gameobject.interfaces.Collidable;
import org.academiadecodigo.bootcamp.bomberdeal.server.gameobject.interfaces.Destroyable;
import org.academiadecodigo.bootcamp.bomberdeal.server.gameobject.interfaces.Interactable;
import org.academiadecodigo.bootcamp.bomberdeal.server.helper.TileType;
import org.academiadecodigo.bootcamp.bomberdeal.server.gameobject.CollisionDetector;

public class Player implements Interactable, Destroyable, Collidable{

    private int x;
    private int y;
    private TileType type;
    private CollisionDetector collisionDetector;

    public Player(CollisionDetector collisionDetector){
        this.collisionDetector= collisionDetector;
        this.type = TileType.PLAYER;
    }

    public void move(Direction direction) {

        switch (direction) {
            case NORTH:
                if (!collisionDetector.checkCollision(x, y-1)){
                    y--;
                }
                break;
            case SOUTH:
                if (!collisionDetector.checkCollision(x, y+1)){
                y++;
            }break;
            case WEST:
                if (!collisionDetector.checkCollision(x-1, y)){
                x--;
            }
                break;
            case EAST:
                if (!collisionDetector.checkCollision(x+1, y)){
                x++;
            }
                break;
        }

        throw new UnsupportedOperationException();
    }

    public void deploy() {
        Bomb bomb = new Bomb();
        notifyAll(bomb);

        throw new UnsupportedOperationException();
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
        return type;
    }

    @Override
    public void destroy() {


    }

    private enum Direction {
        NORTH,
        SOUTH,
        WEST,
        EAST;
    }

}
