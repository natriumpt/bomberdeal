package org.academiadecodigo.bootcamp.bomberdeal.server.gameobject;

import org.academiadecodigo.bootcamp.bomberdeal.server.Network.ServerNetworkMessages;
import org.academiadecodigo.bootcamp.bomberdeal.server.gamefield.Field;
import org.academiadecodigo.bootcamp.bomberdeal.server.gameobject.interfaces.Interactable;
import org.academiadecodigo.bootcamp.bomberdeal.server.gameobject.interfaces.Collidable;
import org.academiadecodigo.bootcamp.bomberdeal.server.gameobject.interfaces.DestroyableByFire;
import org.academiadecodigo.bootcamp.bomberdeal.server.gameobject.interfaces.Observable;
import org.academiadecodigo.bootcamp.bomberdeal.server.helper.PowerUpHandler;
import org.academiadecodigo.bootcamp.bomberdeal.server.helper.CollisionChecker;
import org.academiadecodigo.bootcamp.bomberdeal.server.helper.TileType;


import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


public class Player implements Interactable, DestroyableByFire, Collidable {

    private Observable observer;
    private int x;
    private int y;
    private ArrayList<Bomb> bombs;
    private final int N_INITIAL_BOMB_ = 3; // in ms
    private String type;
    private PowerUpHandler powerUpHandler;
    private CollisionChecker collisionChecker;
    private boolean alive;
    private Field field;

    private boolean onCooldown;
    private Timer cooldownTimer;

    public Player(String spawnPointCoords, CollisionChecker collisionChecker, Observable observer, Field field) {

        this.collisionChecker = collisionChecker;
        this.type = TileType.PLAYER.getSymbol();

        this.observer = observer;

        String[] coords = spawnPointCoords.split(";");

        x = Integer.valueOf(coords[0]);
        y = Integer.valueOf(coords[1]);

        alive = true;

        cooldownTimer = new Timer();
        bombs = new ArrayList<>();
        this.field = field;

        for (int bomb = 0; bomb < N_INITIAL_BOMB_; bomb++) {
            bombs.add(bomb, new Bomb(x, y, collisionChecker));

//            Bomb bombI = new Bomb(x, y, this.observer, collisionChecker, field);
//            bombI.setField(field);
//            bombs.add(bomb, bombI);

        }

    }

    public void increaseBombs(Player player){

        Bomb bomb = new Bomb(player.getX(), player.getY(), collisionChecker);
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

    public void move(String direction) {

        if (!onCooldown) {
            switch (direction) {
                case "UP":
                    if (!collisionChecker.checkCollision(x, y - 1)) {
                        y--;
                        checkPowerUps();
                    }
                    break;
                case "DOWN":
                    if (!collisionChecker.checkCollision(x, y + 1)) {
                        y++;
                        checkPowerUps();
                    }
                    break;
                case "LEFT":
                    if (!collisionChecker.checkCollision(x - 1, y)) {
                        x--;
                        checkPowerUps();
                    }
                    break;
                case "RIGHT":
                    if (!collisionChecker.checkCollision(x + 1, y)) {
                        x++;
                        checkPowerUps();
                    }
                    break;
            }
        }
    }

    public Bomb getBomb(int i){
        return bombs.get(i);
    }

    public int getNrOfBombs(){
        return bombs.size();
    }

    private void checkPowerUps() {
        if(!(collisionChecker.checkPowerUp(x,y) == null)){
            powerUpHandler.assignPowerUp(collisionChecker.checkPowerUp(x, y), this);
        }
    }

    public void deploy() {

        for (int i = 0; i < bombs.size(); i++) {

            if (!bombs.get(i).isOnField()) {
                updateObserver(bombs.get(i));

//                field.getFieldPositions()[x][y] = "B";
//                observer.update(bombs.get(i));
                bombs.get(i).explode(x,y);
                break;

            }
        }

        if (!onCooldown) {
            beginCooldown(); //by Alexandre 23/02/2017 RIP in Pepperonis
        }
    }

    @Override
    public void setField(Field field) {
        this.field = field;
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
    public String getTileType() {
        return type;
    }

    @Override
    public void destroy() {
        System.out.println("Player morreu");
        observer.update(this);
        alive = false;
    }

    public String getPosition() {
        return x + ServerNetworkMessages.COORDS_SPACE + y + ServerNetworkMessages.COORDS_SPACE + "Y";
    }

    public boolean isAlive() {
        return alive;
    }
//
    private void updateObserver(Bomb bomb) {
        bomb.attach(observer);
        observer.update(bomb);
    }

}
