package org.academiadecodigo.bootcamp.bomberdeal.server.gameobject;

import org.academiadecodigo.bootcamp.bomberdeal.server.GameCore;

public class Player {

    private int x;
    private int y;

    public Player() {

    }

    public void move(Direction direction) {

        switch (direction) {
            case NORTH:
                if (!checkCollision(x, y - 1)) {
                    y--;
                }
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
        throw new UnsupportedOperationException();
    }

    private enum Direction {
        NORTH,
        SOUTH,
        WEST,
        EAST;
    }

}
