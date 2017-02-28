package org.academiadecodigo.bootcamp.bomberdeal.client.lanterna;

import com.googlecode.lanterna.terminal.Terminal;
import org.academiadecodigo.bootcamp.bomberdeal.client.grid.TileType;

/**
 * Created by andre on 2/20/2017.
 */
public enum TileTypeLanterna {

    FLOOR,
    WALL,
    PLAYER,
    BOMB,
    FIRE,
    CRATE,
    YOU,
    POWERUP;

    public Terminal.Color getFcolor(TileType type) {

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

    public Terminal.Color getBcolor(TileType type) {

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

}
