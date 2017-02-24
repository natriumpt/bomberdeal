package org.academiadecodigo.bootcamp.bomberdeal.server.gamefield;

import org.academiadecodigo.bootcamp.bomberdeal.server.gameobjects.TileType;

public class Position {
    private int posX;
    private int posY;
    private String tileType;
    private boolean visible;

    public Position(int x, int y, String tileType) {
        posX = x;
        posY = y;

        this.tileType = tileType;
    }

    public String getTileType() {
        return tileType;
    }

    public void setTileType(String tileType) {
        this.tileType = tileType;
    }

    public boolean isCollidable() {
        if ((TileType.getTileType(this.tileType) == TileType.BOMB ||
                TileType.getTileType(this.tileType) == TileType.BOX ||
                TileType.getTileType(this.tileType) == TileType.PLAYER)) {
            return true;
        }
        return false;
    }

    public boolean isDestructible() {

        if ((TileType.getTileType(this.tileType) == TileType.BOMB && isVisible()) ||
                TileType.getTileType(this.tileType) == TileType.WALL ||
                (TileType.getTileType(this.tileType) == TileType.BOX && isVisible()) ||
                TileType.getTileType(this.tileType) == TileType.PLAYER && isVisible()) {
            return true;
        }

        return false;

    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public int setPosX(int nrCol) {
        return posX = posX + nrCol;
    }

    public int setPosY(int nrRow) {
        return posY = posY + nrRow;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible() {
        visible = !visible;
    }
}
