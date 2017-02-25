package org.academiadecodigo.bootcamp.bomberdeal.server.gamefield;

import org.academiadecodigo.bootcamp.bomberdeal.server.helper.TileType;

public class Field {

    private final int FIELD_WIDTH = 15;
    private final int FIELD_HEIGHT = 13;

    private TileType[][] field;

    public Field() {

        createEmptyField();
        populateField();

    }

    public TileType[][] getField() {
        //TODO: Provide client with populated map information
        return field;
    }

    /**
     * Creates the field array
     */
    private void createEmptyField() {
        field = new TileType[FIELD_WIDTH][FIELD_HEIGHT];

        for (int x = 0; x < FIELD_WIDTH; x++) {
            for (int y = 0; y < FIELD_HEIGHT; y++) {
                field[x][y] = TileType.EMPTY;
            }
        }
    }

    /**
     * Populates the field with the pre-set map tiles
     */
    private void populateField() {
        throw new UnsupportedOperationException();
    }

    /**
     * Returns true if the TileType in the coordinates is WALL
     * @param x
     * @param y
     * @return
     */
    public boolean isWall(int x, int y) {
        return field[x][y].equals(TileType.WALL);
    }

}
