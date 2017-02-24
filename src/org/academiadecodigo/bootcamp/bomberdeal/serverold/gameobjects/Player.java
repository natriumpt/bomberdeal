package org.academiadecodigo.bootcamp.bomberdeal.serverold.gameobjects;

import org.academiadecodigo.bootcamp.bomberdeal.serverold.gamefield.Position;

/**
 * Created by codecadet on 2/21/17.
 */
public class Player {
    private int wins;
    private Bomb[] bombs;
    private Bomb bomb;
    private int bombsDeployed;
    private boolean isAlive;
    private Position position;
    private CollisionChecker collisionChecker;


    public Player(Bomb[] bombs) {
        this.bombs = bombs;
        for(int i = 0; i < bombs.length; i++){
            bombs[i]  = new Bomb();
        }
        this.bombsDeployed = 0;
        this.isAlive = true;
    }

    public Position moveRight(Position position){
        Position nextPosition = new Position(position.setPosX(1), position.getPosY(), TileType.PLAYER.toString());
        if(isAvailableNextPosition(nextPosition)){ // Faz o checkCollidable se pode mexer ou se 'e bomba
            return nextPosition;
        }
        return position;
    }

    public Position moveLeft(Position position){
        Position nextPosition = new Position(position.setPosX(-1), position.getPosY(), TileType.PLAYER.toString());
       if(isAvailableNextPosition(nextPosition)){
           return nextPosition;
       }
        return position;
    }
    public Position moveUp(Position position){
        Position nextPosition = new Position(position.getPosX(), position.setPosY(-1), TileType.PLAYER.toString());
        if(isAvailableNextPosition(nextPosition)){
            return nextPosition;
        }
        return position;
    }
    public Position moveDown(Position position){
        Position nextPosition = new Position(position.getPosX(), position.setPosY(1), TileType.PLAYER.toString());
        if(isAvailableNextPosition(nextPosition)){
            return nextPosition;
        }
        return position;
    }

    public void deployBomb(){
        bombs[bombsDeployed].deploy(position.getPosX(), position.getPosY());
        this.bombsDeployed += 1;
    }

    public void die(){
        isAlive = false;
    }

    private boolean isAvailableNextPosition(Position nextPosition) {
        if(collisionChecker.checkIsBombBlast(nextPosition)){
            isAlive = false;
            return true;
        }
        else if(collisionChecker.checkCollidable(nextPosition)){
            return true;
        }
        return false;
    }

    public Position getPosition() {
        return position;
    }
}
