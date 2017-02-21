package org.academiadecodigo.bootcamp.GameLogic;

/**
 * Created by codecadet on 2/21/17.
 */
public class Player {
    private int wins;
    private Bomb[] bombs;
    private boolean isAlive;
    private Position position;
    private CollisionChecker collisionChecker;


    public Position moveRight(Position position){
        Position nextPosition = new Position(position.addCol(), position.getRow());
        if(collisionChecker.checkIsBombBlast(nextPosition)){
            isAlive = false;
        }
        else if(collisionChecker.check(nextPosition)){
            return nextPosition;
        }
        return position;
    }

    public Position moveLeft(Position position){
        Position nextPosition = new Position(position.removeCol(), position.getRow());
        if(collisionChecker.checkIsBombBlast(nextPosition)){
            isAlive = false;
        }
        else if(collisionChecker.check(nextPosition)){
            return nextPosition;
        }
        return position;
    }
    public Position moveUp(Position position){
        Position nextPosition = new Position(position.getCol(), position.removeRow());
        if(collisionChecker.checkIsBombBlast(nextPosition)){
            isAlive = false;
        }
        else if(collisionChecker.check(nextPosition)){
            return nextPosition;
        }
        return position;
    }
    public Position moveDown(Position position){
        Position nextPosition = new Position(position.getCol(), position.addRow());
        if(collisionChecker.checkIsBombBlast(nextPosition)){
            isAlive = false;
        }
        else if(collisionChecker.check(nextPosition)){
            return nextPosition;
        }
        return position;
    }
    public void deployBomb(){

    }

    public void die(){
        isAlive = false;
    }


}
