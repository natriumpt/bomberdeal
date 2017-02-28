package org.academiadecodigo.bootcamp.bomberdeal.client.lanterna;

import com.googlecode.lanterna.terminal.Terminal;

/**
 * Created by andre on 2/20/2017.
 */
public enum TileTypeLanterna {

    FLOOR("0", Terminal.Color.CYAN, Terminal.Color.CYAN),
    WALL("W", Terminal.Color.BLUE, Terminal.Color.BLUE),
    PLAYER("P", Terminal.Color.WHITE, Terminal.Color.BLACK),
    BOMB("B", Terminal.Color.BLACK, Terminal.Color.BLACK),
    FIRE("F", Terminal.Color.RED, Terminal.Color.RED),
    CRATE("C", Terminal.Color.YELLOW, Terminal.Color.WHITE),
    YOU("Y", Terminal.Color.WHITE, Terminal.Color.BLACK),
    POWERUP("U", Terminal.Color.GREEN, Terminal.Color.WHITE);

    private String type;
    private Terminal.Color color;
    private Terminal.Color textColor;

    TileTypeLanterna(String value, Terminal.Color color, Terminal.Color textColor) {

        this.type = value;
        this.color = color;
        this.textColor = textColor;

    }

    public String getType() {
        return type;
    }

    public Terminal.Color getFcolor(TileTypeLanterna type) {

        Terminal.Color color;

        switch (type) {
            case FLOOR:
                color = Terminal.Color.CYAN;
                break;
            case WALL:
                color = Terminal.Color.BLUE;
                break;
            case PLAYER:
            case YOU:
                color = Terminal.Color.WHITE;
                break;
            case BOMB:
                color = Terminal.Color.BLACK;
                break;
            case FIRE:
                color = Terminal.Color.RED;
                break;
            case CRATE:
                color = Terminal.Color.YELLOW;
                break;
            case POWERUP:
                color = Terminal.Color.GREEN;
                break;
            default:
                color = null;
                System.out.println("Oops in getFcolor.");
        }

        return color;
    }

    public Terminal.Color getBcolor(TileTypeLanterna type) {

        switch (type) {
            case FLOOR:
                color = Terminal.Color.CYAN;
                break;
            case WALL:
                color = Terminal.Color.BLUE;
                break;
            case PLAYER:
            case YOU:
            case BOMB:
                color = Terminal.Color.BLACK;
                break;
            case FIRE:
            case CRATE:
            case POWERUP:
                color = Terminal.Color.WHITE;
                break;
            default:
                color = null;
                System.out.println("Oops in getFcolor.");
        }

        return color;
    }

    public static TileTypeLanterna getTileType(String type) {

        for(int i = 0; i < TileTypeLanterna.values().length; i++) {

            if(type.equals(TileTypeLanterna.values()[i].getType())) {
                return TileTypeLanterna.values()[i];
            }

        }

        return TileTypeLanterna.FLOOR;

    }

}
