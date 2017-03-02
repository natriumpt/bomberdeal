package org.academiadecodigo.bootcamp.bomberdeal.client.grid;

import com.googlecode.lanterna.terminal.Terminal;

public enum TileType {

    FLOOR,
    WALL,
    PLAYER,
    BOMB,
    FIRE,
    CRATE,
    YOU,
    POWERUP;

    public TileType getTileType(String message) {

        TileType type = null;

        switch (message) {
            case "1":
                type = WALL;
                break;
            case "F":
                type = FLOOR;
                break;
            case "C":
                type = CRATE;
                break;
            case "Y":
                type = YOU;
                break;
            case "U":
                type = POWERUP;
                break;
            case "B":
                type = BOMB;
                break;
            case "P":
                type = PLAYER;
                break;
            default:
                type = FLOOR;
                break;
        }

        return type;
    }

}
