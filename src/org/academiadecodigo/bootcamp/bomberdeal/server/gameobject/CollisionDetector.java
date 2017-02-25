package org.academiadecodigo.bootcamp.bomberdeal.server.gameobject;

import org.academiadecodigo.bootcamp.bomberdeal.server.gamefield.Field;
import org.academiadecodigo.bootcamp.bomberdeal.server.gameobject.interfaces.Collidable;
import org.academiadecodigo.bootcamp.bomberdeal.server.gameobject.interfaces.Interactable;

import java.util.List;

/**
 * Created by codecadet on 2/24/17.
 */
public class CollisionDetector {
    private List<Interactable> interactables;
    private Field field;

    public CollisionDetector(List<Interactable> interactables, Field field){
        this.interactables = interactables;
        this.field = field;
    }

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
}
