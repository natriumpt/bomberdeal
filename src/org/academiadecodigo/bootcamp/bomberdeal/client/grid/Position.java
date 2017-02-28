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

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public TileType getType() {
        return type;
    }

    public void setType(TileType type) {
        this.type = type;
    }

}
