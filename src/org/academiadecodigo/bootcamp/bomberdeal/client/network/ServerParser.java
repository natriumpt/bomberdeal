package org.academiadecodigo.bootcamp.bomberdeal.client.network;

import org.academiadecodigo.bootcamp.bomberdeal.client.grid.Grid;
import org.academiadecodigo.bootcamp.bomberdeal.client.grid.GridLanterna;

import java.io.BufferedReader;


public class ServerParser {

    private Grid grid;

    public ServerParser(Grid grid){

        this.grid = grid;

    }

    public synchronized void handleTCPMessage(String message, BufferedReader reader) {

        if(message == null) {

            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        if(message.equals(ClientNetworkMessages.SERVER_MAP_SENDING_LAYOUT)) {

                grid.init(reader);
                grid.drawGrid();

        }

        notify();
    }

    public synchronized void handleUDPMessage(String message) {

        String[] posUpdate = message.split("\n");
        String[] posCoord;

        if(((GridLanterna)grid).isGridCreated()) {

            for (String serverPos : posUpdate) {

                posCoord = serverPos.split(";");

                grid.updatePositions(Integer.parseInt(posCoord[0]), Integer.parseInt(posCoord[1]), posCoord[2]);

            }

            grid.updateScreen();

        }

    }

}
