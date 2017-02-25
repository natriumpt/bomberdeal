package org.academiadecodigo.bootcamp.bomberdeal.client.grid;

import com.googlecode.lanterna.terminal.Terminal;

/**
 * Created by andre on 2/20/2017.
 */
public enum TiletypeLanterna {

    FLOOR("0", Terminal.Color.CYAN, Terminal.Color.CYAN),
    WALL("WALL", Terminal.Color.BLUE, Terminal.Color.BLUE),
    PLAYER("P", Terminal.Color.WHITE, Terminal.Color.BLACK),
    BOMB("B", Terminal.Color.BLACK, Terminal.Color.WHITE),
    FIRE("F", Terminal.Color.RED, Terminal.Color.RED),
    CRATE("C", Terminal.Color.YELLOW, Terminal.Color.WHITE),
    YOU("Y", Terminal.Color.WHITE, Terminal.Color.BLACK),
    POWERUP("U", Terminal.Color.GREEN, Terminal.Color.WHITE);

    private String type;
    private Terminal.Color color;
    private Terminal.Color textColor;

    TiletypeLanterna(String value, Terminal.Color color, Terminal.Color textColor) {

        this.type = value;
        this.color = color;
        this.textColor = textColor;

    }

    public String getType() {
        return type;
    }

    public Terminal.Color getColor() {
        return this.color;
    }

    public Terminal.Color getTextColor() {
        return textColor;
    }

    public static TiletypeLanterna getTileType(String type){

        for(int i = 0; i < TiletypeLanterna.values().length; i++) {

            if(type.equals(TiletypeLanterna.values()[i].getType())) {
                return TiletypeLanterna.values()[i];
            }

        }

        return TiletypeLanterna.FLOOR;

    }

}
