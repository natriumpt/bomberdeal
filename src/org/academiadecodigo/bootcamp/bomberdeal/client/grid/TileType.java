package org.academiadecodigo.bootcamp.bomberdeal.client.grid;

import com.googlecode.lanterna.terminal.Terminal;

public enum TileType {

    FLOOR("0", Terminal.Color.CYAN, Terminal.Color.CYAN),
    WALL("1", Terminal.Color.BLUE, Terminal.Color.BLUE),
    PLAYER("P", Terminal.Color.WHITE, Terminal.Color.BLACK),
    BOMB("B", Terminal.Color.BLACK, Terminal.Color.WHITE),
    FIRE("F", Terminal.Color.RED, Terminal.Color.RED),
    CRATE("C", Terminal.Color.YELLOW, Terminal.Color.WHITE),
    YOU("Y", Terminal.Color.WHITE, Terminal.Color.BLACK),
    POWERUP("U", Terminal.Color.GREEN, Terminal.Color.WHITE);

    private String type;
    private Terminal.Color color;
    private Terminal.Color textColor;

    TileType(String value, Terminal.Color color, Terminal.Color textColor) {

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

    public static TileType getTileType(String type){

        for(int i = 0; i < TileType.values().length; i++) {

            if(type.equals(TileType.values()[i].getType())) {
                return TileType.values()[i];
            }

        }

        return TileType.FLOOR;

    }

}
