package org.academiadecodigo.bootcamp.Client;

/**
 * Created by andre on 2/20/2017.
 */
public class Position {

    private int posX;
    private int posY;
    private Tiletype tile;

    public Position(int x, int y, Tiletype tile) {

        posX = x;
        posY = y;

        this.tile = tile;
        System.out.println(tile);

    }


}
