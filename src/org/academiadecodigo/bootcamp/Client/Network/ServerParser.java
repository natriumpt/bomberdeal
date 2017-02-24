package org.academiadecodigo.bootcamp.Client.Network;

import org.academiadecodigo.bootcamp.Client.Game;
import org.academiadecodigo.bootcamp.Client.Grid.Grid;

/**
 * Created by codecadet on 2/24/17.
 */
public class ServerParser {

    private Game game;
    private Grid grid;

    public ServerParser(Game game, Grid grid){

        this.game = game;
        this.grid = grid;

    }

    public void handleTCPMessage(String message) {

        if(message.equals(ServerMessages.SERVER_MAP_SENDING_LAYOUT)) {
            grid.init();
        }

    }

    public void handleUDPMessage(String message) {

        System.out.println(message);
        String[] posUpdate = message.split(":");

        grid.updatePositions(Integer.parseInt(posUpdate[0]), Integer.parseInt(posUpdate[1]), posUpdate[2]);
        grid.updateScreen();

    }

}
