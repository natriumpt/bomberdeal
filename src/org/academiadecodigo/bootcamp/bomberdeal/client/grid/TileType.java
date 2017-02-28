package org.academiadecodigo.bootcamp.bomberdeal.client.grid;

import com.googlecode.lanterna.terminal.Terminal;

public enum TileType {

    FLOOR("0"),
    WALL("1"),
    PLAYER("P"),
    BOMB("B"),
    FIRE("F"),
    CRATE("C"),
    YOU("Y"),
    POWERUP("U");

    private String type;

    TileType(String value) {

        this.type = value;

    }

    public String getType() {
        return type;
    }

}
