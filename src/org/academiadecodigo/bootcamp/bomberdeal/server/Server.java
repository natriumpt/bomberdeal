package org.academiadecodigo.bootcamp.bomberdeal.server;

import org.academiadecodigo.bootcamp.bomberdeal.server.Network.ClientParser;
import org.academiadecodigo.bootcamp.bomberdeal.server.Network.PlayerHandler;
import org.academiadecodigo.bootcamp.bomberdeal.server.gameobject.Player;

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

    private GameCore game;
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

        game = new GameCore();

        ClientParser parser = new ClientParser();
        parser.setObserver(game);

        Socket playerSocket;
        PlayerHandler playerHandler = null;

        ExecutorService playerPool = Executors.newFixedThreadPool(4);

        game.startGame();

        while (true) {

            try {

                System.out.println("Waiting for connection...");
                playerSocket = serverTPCSocket.accept();
                System.out.println("Player connected.");

                playerHandler = new PlayerHandler(playerSocket, serverUDPSocket, parser);
                playerHandler.setPlayer(new Player(game.getSpawn(), game.getCollisionChecker(), game, game.getField()));
                playerPool.submit(playerHandler);

            } catch (IOException e) {
                e.printStackTrace();
            }

            synchronized (playerHandler) {

                game.addPlayer(playerHandler);

            }



        }

    }
}
