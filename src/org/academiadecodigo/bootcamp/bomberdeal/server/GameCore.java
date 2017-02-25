package org.academiadecodigo.bootcamp.bomberdeal.server;



import java.util.List;

public abstract class GameCore implements Runnable {
    /*

    private List<Interactable> interactables;
    private Field field;

    public GameCore() {
        this.field = new Field();
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

    public void processFire() {

        for (Interactable fire : interactables) {
            if (fire instanceof Fire) {
                for (Interactable destroyable : interactables) {
                    if (destroyable instanceof Destroyable) {
                        destroyable.destroy();
                    }
                }
            }
        }

    }

    @Override
    public void run() {

        while (true) {

        }

    }

*/
}
