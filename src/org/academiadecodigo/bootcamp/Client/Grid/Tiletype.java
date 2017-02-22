package org.academiadecodigo.bootcamp.Client.Grid;

import com.googlecode.lanterna.terminal.Terminal;

/**
 * Created by andre on 2/20/2017.
 */
public enum Tiletype {

    FLOOR("0", Terminal.Color.CYAN),
    WALL("1", Terminal.Color.BLUE),
    PLAYER("P", Terminal.Color.WHITE ),
    BOMB("B", Terminal.Color.BLACK),
    FIRE("F", Terminal.Color.RED),
    BOX("C", Terminal.Color.YELLOW),
    POWERUP("U", Terminal.Color.GREEN);

    private String type;
    private Terminal.Color color;

    Tiletype(String value, Terminal.Color color) {
        this.type = value;
        this.color = color;
    }

    public String getType() {
        return type;
    }

    public Terminal.Color getColor() {
        return this.color;
    }

    public static Tiletype getTileType(String type){

        for(int i = 0; i < Tiletype.values().length; i++) {

            if(type.equals(Tiletype.values()[i].getType())) {
                return Tiletype.values()[i];
            }

        }
        return Tiletype.FLOOR;
    }

}
