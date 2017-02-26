package org.academiadecodigo.bootcamp.bomberdeal.client.network;

import org.academiadecodigo.bootcamp.bomberdeal.client.Game;
import org.academiadecodigo.bootcamp.bomberdeal.client.grid.Grid;

import java.io.BufferedReader;

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

        if(message == null) {

            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        System.out.println(message);
        String[] posUpdate = message.split(",");

        grid.updatePositions(Integer.parseInt(posUpdate[0]), Integer.parseInt(posUpdate[1]), posUpdate[2]);
        grid.updateScreen();

        notify();

    }

}
