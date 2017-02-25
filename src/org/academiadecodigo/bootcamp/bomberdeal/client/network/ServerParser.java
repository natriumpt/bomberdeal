package org.academiadecodigo.bootcamp.bomberdeal.client.network;

import org.academiadecodigo.bootcamp.bomberdeal.client.Game;
import org.academiadecodigo.bootcamp.bomberdeal.client.grid.Grid;
import org.academiadecodigo.bootcamp.bomberdeal.client.network.ServerMessages;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.Socket;

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

    public void handleTCPMessage(String message, Socket socket) {

        if(message.equals(ServerMessages.SERVER_MAP_SENDING_LAYOUT)) {
            try {
                game.initGrid(socket.getInputStream());
                grid.init();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    public void handleUDPMessage(String message, DatagramSocket socket) {

        System.out.println(message);
        String[] posUpdate = message.split(":");

        grid.updatePositions(Integer.parseInt(posUpdate[0]), Integer.parseInt(posUpdate[1]), posUpdate[2]);
        grid.updateScreen();

    }

}
