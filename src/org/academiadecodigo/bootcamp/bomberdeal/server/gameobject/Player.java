package org.academiadecodigo.bootcamp.bomberdeal.server.gameobject;

import org.academiadecodigo.bootcamp.bomberdeal.server.gameobject.interfaces.Observable;

public class Player {

    private Observable observer;
    private int x;
    private int y;

    public Player(Observable observer) {
        this.observer = observer;
    }

    public void move(Direction direction) {

        switch (direction) {
            case NORTH:
//                if (!checkCollision(x, y - 1)) {
//                    y--;
//                }
                break;
            case SOUTH:
                break;
            case WEST:
                break;
            case EAST:
                break;
        }

        throw new UnsupportedOperationException();
    }

    public void deploy() {
        Bomb bomb = new Bomb(x, y);
        bomb.attach(observer);
        notifyAll(bomb);
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
