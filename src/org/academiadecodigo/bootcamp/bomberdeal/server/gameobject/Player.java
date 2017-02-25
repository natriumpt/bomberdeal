package org.academiadecodigo.bootcamp.bomberdeal.server.gameobject;

import org.academiadecodigo.bootcamp.bomberdeal.server.gameobject.interfaces.Interactable;
import org.academiadecodigo.bootcamp.bomberdeal.server.GameCore;
import org.academiadecodigo.bootcamp.bomberdeal.server.gameobject.interfaces.Collidable;
import org.academiadecodigo.bootcamp.bomberdeal.server.gameobject.interfaces.DestroyableByFire;
import org.academiadecodigo.bootcamp.bomberdeal.server.gameobject.interfaces.Interactable;
import org.academiadecodigo.bootcamp.bomberdeal.server.gameobject.interfaces.Observable;
import org.academiadecodigo.bootcamp.bomberdeal.server.helper.PowerUpHandler;
import org.academiadecodigo.bootcamp.bomberdeal.server.helper.TileType;
import org.academiadecodigo.bootcamp.bomberdeal.server.gameobject.CollisionDetector;

import java.util.Timer;
import java.util.TimerTask;


public class Player implements Interactable, DestroyableByFire, Collidable{

    private Observable observer;
    private int x;
    private int y;

    private TileType type;
    private CollisionDetector collisionDetector;
    private PowerUpHandler powerUpHandler;
    private PowerUp powerUp;

    private boolean onCooldown;
    private Timer cooldownTimer;

    public Player(int x, int y, CollisionDetector collisionDetector, Observable observer){
        this.collisionDetector= collisionDetector;
        this.type = TileType.PLAYER;
        this.observer = observer;
        this.x = x;
        this.y = y;
        cooldownTimer = new Timer();
    }

    public void catchPowerUp(){
        if()

    }

    public void increaseBombs(){
       Bomb bomb = new Bomb();
        bombs.add(bombs.size(), bomb);
    }

    private void beginCooldown() {
        onCooldown = true;
        cooldownTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                onCooldown = false;
            }
        }, 50);
    }

    public void move(Direction direction) {
        if (!onCooldown) {
            switch (direction) {
                case NORTH:
                    if (!collisionDetector.checkCollision(x, y - 1)) {
                        y--;
                        checkPowerUps();
                    }
                    break;
                case SOUTH:
                    if (!collisionDetector.checkCollision(x, y + 1)) {
                        y++;
                        checkPowerUps();
                    }
                    break;
                case WEST:
                    if (!collisionDetector.checkCollision(x - 1, y)) {
                        x--;
                        checkPowerUps();
                    }
                    break;
                case EAST:
                    if (!collisionDetector.checkCollision(x + 1, y)) {
                        x++;
                        checkPowerUps();
                    }
                    break;
            }
        }
    }

    private void checkPowerUps() {
        if(!collisionChecker.checkPowerUp() == null){
            powerUpHandler.assingPowerUp(collisionChecker.chekPowerUp());
        }
        ;
    }

    public void deploy() {
        Bomb bomb = new Bomb(x, y);
        bomb.attach(observer);
        notifyAll(bomb);

        if (!onCooldown) {
            // TODO: Implement action here
            beginCooldown();
        }
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
        EAST
    }

    private void notifyAll(Bomb bomb) {

        observer.update(bomb);

    }

}
