package org.academiadecodigo.bootcamp.Client.Grid;

import com.googlecode.lanterna.screen.Screen;

/**
 * Created by andre on 2/20/2017.
 */
public class Position {

    protected int posX;
    protected int posY;
    protected String tile;


    public Position(int x, int y, String tile) {

        posX = x;
        posY = y;

        this.tile = tile;

    }

    public void draw(Screen screen) {

        screen.putString(posX, posY, tile, Tiletype.getTileType(tile).getTextColor(), Tiletype.getTileType(tile).getColor());

    }

}
