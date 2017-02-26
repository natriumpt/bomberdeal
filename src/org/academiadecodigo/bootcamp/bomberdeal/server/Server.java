package org.academiadecodigo.bootcamp.bomberdeal.server;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.SocketException;

/**
 * Created by andre on 2/26/2017.
 */
public class  Server {

    private ServerGame game;
    private ServerSocket serverTPCSocket;
    private DatagramSocket serverUDPSocket;

    public Server(int portNumber) {

        try {
            serverTPCSocket = new ServerSocket(portNumber);
            serverUDPSocket = new DatagramSocket(portNumber);
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void startServer() {

        game = new ServerGame();


    }

}
