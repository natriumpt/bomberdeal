package org.academiadecodigo.bootcamp.bomberdeal.serverold.gameobjects;

import com.googlecode.lanterna.terminal.Terminal;

/**
 * Created by andre on 2/20/2017.
 */
public enum TileType {

    FLOOR("0", Terminal.Color.CYAN),
    WALL("1", Terminal.Color.BLUE),
    PLAYER("P", Terminal.Color.WHITE ),
    BOMB("B", Terminal.Color.BLACK),
    FIRE("F", Terminal.Color.RED),
    BOX("C", Terminal.Color.YELLOW),
    POWERUP("U", Terminal.Color.GREEN);

    private String type;
    private Terminal.Color color;

    TileType(String value, Terminal.Color color) {
        this.type = value;
        this.color = color;
    }

    public String getType() {
        return this.type;
    }

    public Terminal.Color getColor() {
        return this.color;
    }

    public static TileType getTileType(String type){

        for(int i = 0; i < TileType.values().length; i++) {

            if(type.equals(TileType.values()[i].getType())) {
                return TileType.values()[i];
            }

        }
        return TileType.FLOOR;
    }

    @Override
    public String toString() {
        return type;
    }
}

