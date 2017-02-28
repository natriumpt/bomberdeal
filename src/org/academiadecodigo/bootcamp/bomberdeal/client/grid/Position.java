package org.academiadecodigo.bootcamp.bomberdeal.client.grid;

public class Position {

    private int posX;
    private int posY;
    private TileType type;


    public Position(int x, int y, TileType type) {

        posX = x;
        posY = y;

        this.type = type;

    }
}
