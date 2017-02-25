package org.academiadecodigo.bootcamp.bomberdeal.server;

import org.academiadecodigo.bootcamp.bomberdeal.server.gamefield.Field;
<<<<<<< HEAD
import org.academiadecodigo.bootcamp.bomberdeal.server.gameobject.Fire;
import org.academiadecodigo.bootcamp.bomberdeal.server.gameobject.interfaces.Collidable;
import org.academiadecodigo.bootcamp.bomberdeal.server.gameobject.interfaces.DestroyableByFire;
import org.academiadecodigo.bootcamp.bomberdeal.server.gameobject.interfaces.Interactable;
import org.academiadecodigo.bootcamp.bomberdeal.server.gameobject.interfaces.Observable;
=======
import org.academiadecodigo.bootcamp.bomberdeal.server.gameobject.interfaces.Interactable;
import org.academiadecodigo.bootcamp.bomberdeal.server.helper.CollisionChecker;
>>>>>>> 10a3e24f2df19963d836f9b6e5815e2d41befe7a

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
        this.collisionChecker = new CollisionChecker();
    }

<<<<<<< HEAD
    public boolean checkCollision(int x, int y) {

        if (field.isWall(x, y)) {
            return true;
        }

        for (Interactable collidable : interactables) {
            if (collidable instanceof Collidable) {
                if (collidable.getX() == x && collidable.getY() == y)
                    return true;
            }
        }

        return false;

    }

    public void processFire() {

        for (Interactable fire : interactables) {
            if (fire instanceof Fire) {
                for (Interactable destroyable : interactables) {
                    if (destroyable instanceof DestroyableByFire) {
                        destroyable.destroy();
                    }
                }
            }
        }

    }
=======
>>>>>>> 10a3e24f2df19963d836f9b6e5815e2d41befe7a

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
