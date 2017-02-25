package org.academiadecodigo.bootcamp.bomberdeal.server;

import org.academiadecodigo.bootcamp.bomberdeal.server.gamefield.Field;
import org.academiadecodigo.bootcamp.bomberdeal.server.gameobject.interfaces.Interactable;
import org.academiadecodigo.bootcamp.bomberdeal.server.gameobject.interfaces.Observable;
import org.academiadecodigo.bootcamp.bomberdeal.server.helper.CollisionChecker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;



public class GameCore implements Runnable, Observable {

    private Field field;
    private List<Interactable> interactables;
    private CollisionChecker collisionChecker;

    public GameCore() {
        this.field = new Field();
        this.interactables = Collections.synchronizedList(new ArrayList<>());
        this.collisionChecker = new CollisionChecker(field,interactables);
    }

    public void processFire(){
        collisionChecker.processFire();
    }

    @Override
    public void run() {

        while (true) {


        }

    }


    @Override
    public void update(Interactable gameObject) {

        if(interactables.contains(gameObject)) {
            interactables.remove(gameObject);
        } else {
            interactables.add(gameObject);
        }
    }
}
