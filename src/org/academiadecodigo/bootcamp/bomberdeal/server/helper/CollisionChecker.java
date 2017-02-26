package org.academiadecodigo.bootcamp.bomberdeal.server.helper;

import org.academiadecodigo.bootcamp.bomberdeal.server.gamefield.Field;
import org.academiadecodigo.bootcamp.bomberdeal.server.gameobject.Fire;
import org.academiadecodigo.bootcamp.bomberdeal.server.gameobject.PowerUp;
import org.academiadecodigo.bootcamp.bomberdeal.server.gameobject.interfaces.Collidable;
import org.academiadecodigo.bootcamp.bomberdeal.server.gameobject.interfaces.DestroyableByFire;
import org.academiadecodigo.bootcamp.bomberdeal.server.gameobject.interfaces.Interactable;

import java.util.List;

public class CollisionChecker {

    private Field field;
    private List<Interactable> interactables;

    public CollisionChecker(Field field, List<Interactable> interactables) {
        this.field = field;
        this.interactables = interactables;
    }

    public boolean checkCollision(int x, int y) {

        if (field.isWall(x, y)) {
            return true;
        }

        for (Interactable interactable : interactables) {
            System.out.println(interactable.getTileType());
            if (interactable instanceof Collidable) {
                if (interactable.getX() == x && interactable.getY() == y)
                    return true;
            }
        }

        return false;

    }

    public boolean checkWall(int x, int y) {
        if (field.isWall(x, y)) {
            return true;
        }
        return false;
    }

    public void processFire(int x, int y) {
        System.out.println("a entrar no processo");

        for (Interactable fire : interactables) {
            if (fire instanceof Fire) {
                System.out.println("no processo do fogo");
                for (Interactable destroyable : interactables) {
                    if (destroyable instanceof DestroyableByFire) {
                        if (destroyable.getX() == x && destroyable.getY() == y) {
                            destroyable.destroy();
                        }
                    }
                }
            }
        }

    }

    public PowerUp checkPowerUp(int x, int y) {
        PowerUp powerUp2 = null;
        for (Interactable powerUp : interactables) {
            if (powerUp instanceof PowerUp) {
                powerUp2 = (PowerUp) powerUp;
            }
        }
        return powerUp2;
    }

}
