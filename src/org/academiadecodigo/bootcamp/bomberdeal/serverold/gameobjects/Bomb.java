package org.academiadecodigo.bootcamp.bomberdeal.serverold.gameobjects;

import org.academiadecodigo.bootcamp.bomberdeal.serverold.Observer;
import org.academiadecodigo.bootcamp.bomberdeal.serverold.gamefield.Position;

import java.util.ArrayList;

/**
 * Created by codecadet on 2/21/17.
 */
public class Bomb {

    private int range;
    private int timer;
    private Position position;
    private CollisionChecker collisionChecker;
    private ArrayList<Observer> observers;
    private boolean active;

    public Bomb() {
        this.range = 1;
    }

    public void deploy(int posX, int posY){
        position.setPosX(posX);
        position.setPosY(posY);
        position.setVisible();
    }

    public void explode(){ // timertask
        position.setVisible();
        setActive();
    }

    private void increaseRange(){
        this.range += 1;
    }
    public Position getPosition() {
        return position;
    }

    public void setActive(){
        active = true;
        notifyAllObservers();
    }
    public void attach(Observer observer){
        observers.add(observer);
    }

    public void notifyAllObservers(){
        for(Observer observer: observers){
            observer.update(this);
        }
    }

    public int getRange() {
        return range;
    }
}
