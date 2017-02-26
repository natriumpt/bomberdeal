package org.academiadecodigo.bootcamp.bomberdeal.server;

import org.academiadecodigo.bootcamp.bomberdeal.server.Network.ClientParser;
import org.academiadecodigo.bootcamp.bomberdeal.server.Network.PlayerHandler;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

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
        Thread gameThread = new Thread(game);
        gameThread.start();

        ClientParser parser = new ClientParser();

        Socket playerSocket;
        PlayerHandler player = null;

        parser.setObserver(game.getGameCore());
        ExecutorService playerPool = Executors.newFixedThreadPool(4);

        while (true) {

            try {
                System.out.println("Waiting for connection...");
                playerSocket = serverTPCSocket.accept();
                System.out.println("Player connected.");

                player = new PlayerHandler(playerSocket, serverUDPSocket, parser);

            } catch (IOException e) {
                e.printStackTrace();
            }

            playerPool.submit(player);
            synchronized (player) {
                game.addPlayer(player);
            }

        }


    }

}
