package org.academiadecodigo.bootcamp.Server.gamelogic;

import org.academiadecodigo.bootcamp.Server.gridserver.PositionServer;

/**
 * Created by codecadet on 2/21/17.
 */
public class Player {
    private int wins;
    private Bomb[] bombs;
    private Bomb bomb;
    private int bombsDeployed;
    private boolean isAlive;
    private PositionServer position;
    private CollisionChecker collisionChecker;


    public Player(Bomb[] bombs) {
        this.bombs = bombs;
        for(int i = 0; i < bombs.length; i++){
            bombs[i]  = new Bomb();
        }
        this.bombsDeployed = 0;
        this.isAlive = true;
    }

    public PositionServer moveRight(PositionServer position){
        PositionServer nextPosition = new PositionServer(position.setPosX(1), position.getPosY(), TiletypeServer.PLAYER.toString());
        if(isAvailableNextPosition(nextPosition)){ // Faz o checkCollidable se pode mexer ou se 'e bomba
            return nextPosition;
        }
        return position;
    }

    public PositionServer moveLeft(PositionServer position){
        PositionServer nextPosition = new PositionServer(position.setPosX(-1), position.getPosY(), TiletypeServer.PLAYER.toString());
       if(isAvailableNextPosition(nextPosition)){
           return nextPosition;
       }
        return position;
    }
    public PositionServer moveUp(PositionServer position){
        PositionServer nextPosition = new PositionServer(position.getPosX(), position.setPosY(-1), TiletypeServer.PLAYER.toString());
        if(isAvailableNextPosition(nextPosition)){
            return nextPosition;
        }
        return position;
    }
    public PositionServer moveDown(PositionServer position){
        PositionServer nextPosition = new PositionServer(position.getPosX(), position.setPosY(1), TiletypeServer.PLAYER.toString());
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

    private boolean isAvailableNextPosition(PositionServer nextPosition) {
        if(collisionChecker.checkIsBombBlast(nextPosition)){
            isAlive = false;
            return true;
        }
        else if(collisionChecker.checkCollidable(nextPosition)){
            return true;
        }
        return false;
    }

    public PositionServer getPosition() {
        return position;
    }
}
