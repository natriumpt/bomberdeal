package org.academiadecodigo.bootcamp.Server.gamelogic;

import com.googlecode.lanterna.terminal.Terminal;

/**
 * Created by andre on 2/20/2017.
 */
public enum TiletypeServer {

    FLOOR("0", Terminal.Color.CYAN),
    WALL("1", Terminal.Color.BLUE),
    PLAYER("P", Terminal.Color.WHITE ),
    BOMB("B", Terminal.Color.BLACK),
    FIRE("F", Terminal.Color.RED),
    BOX("C", Terminal.Color.YELLOW),
    POWERUP("U", Terminal.Color.GREEN);

    private String type;
    private Terminal.Color color;

    TiletypeServer(String value, Terminal.Color color) {
        this.type = value;
        this.color = color;
    }

    public String getType() {
        return this.type;
    }

    public Terminal.Color getColor() {
        return this.color;
    }

    public static TiletypeServer getTileType(String type){

        for(int i = 0; i < TiletypeServer.values().length; i++) {

            if(type.equals(TiletypeServer.values()[i].getType())) {
                return TiletypeServer.values()[i];
            }

        }
        return TiletypeServer.FLOOR;
    }

    @Override
    public String toString() {
        return type;
    }
}

