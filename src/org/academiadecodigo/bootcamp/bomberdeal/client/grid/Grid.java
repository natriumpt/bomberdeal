package org.academiadecodigo.bootcamp.bomberdeal.client.grid;

public class Grid {

    private int cols;
    private int rows;
    private Position[][] positions;
    private String gridMap;

    public Grid(int x, int y) {

        this.cols = x;
        this.rows = y;

        positions = new Position[x - 1][y - 1];

    }

    public void setGridMap(String message) {
        gridMap = message;
    }

    public void updatePosition(int x, int y, TileType type) {
        positions[x][y].setType(type);
    }
}
