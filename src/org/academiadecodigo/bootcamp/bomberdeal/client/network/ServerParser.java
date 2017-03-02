package org.academiadecodigo.bootcamp.bomberdeal.client.network;

import org.academiadecodigo.bootcamp.bomberdeal.client.grid.Grid;

import java.io.BufferedReader;
import java.io.IOException;


public class ServerParser {

    private Grid grid;

    public ServerParser(Grid grid){

        this.grid = grid;

    }

    public synchronized void handleTCPMessage(String message, BufferedReader reader) {

        String newMessage = null;



        if(message.equals(ClientNetworkMessages.SERVER_MAP_SENDING_LAYOUT)) {

            while(newMessage != ClientNetworkMessages.SERVER_MAP_LAYOUT_COMPLETE) {

                try {
                    newMessage += reader.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                grid.setGridMap(newMessage);

            }

        }

    }

    public synchronized void handleUDPMessage(String message) {

        String[] posUpdate = message.split("\n");
        String[] posCoord;

        if(((Grid)grid).isGridCreated()) {

            for (String serverPos : posUpdate) {

                posCoord = serverPos.split(";");

                grid.updatePosition(Integer.parseInt(posCoord[0]), Integer.parseInt(posCoord[1]), posCoord[2]);

            }

            grid.updateScreen();

        }

    }

}
