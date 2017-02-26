package org.academiadecodigo.bootcamp.bomberdeal.client.grid;

public class Position {

    protected int posX;
    protected int posY;
    protected String tile;


    public Position(int x, int y, String tile) {

        posX = x;
        posY = y;

        this.tile = tile;

    }
}
