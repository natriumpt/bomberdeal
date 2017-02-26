package org.academiadecodigo.bootcamp.bomberdeal.client;

import org.academiadecodigo.bootcamp.bomberdeal.client.grid.Grid;
import org.academiadecodigo.bootcamp.bomberdeal.client.network.ServerParser;
import org.academiadecodigo.bootcamp.bomberdeal.client.grid.GridLanterna;
import org.academiadecodigo.bootcamp.bomberdeal.client.network.ClientNetworkUDP;
import org.academiadecodigo.bootcamp.bomberdeal.client.network.ClientNetworkTCP;
import org.academiadecodigo.bootcamp.bomberdeal.client.userinput.UserInputLanterna;
import org.academiadecodigo.bootcamp.bomberdeal.client.userinput.UserInput;
import org.academiadecodigo.bootcamp.bomberdeal.client.menu.MenuLanterna;
import org.academiadecodigo.bootcamp.bomberdeal.client.menu.Menu;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.*;
import java.util.Timer;
import java.util.TimerTask;

public class Game {

    private Grid grid;
    private DatagramSocket udpSocket;
    private Socket tcpSocket;
    private String playerName;
    private ServerParser serverHandler;

    public static void main(String[] args) {

        Game bomberdeal = new Game();
        bomberdeal.startGame();

    }

    public void startGame() {

        /*Menu menu = new MenuLanterna();

        if(menu == null) {
            throw new ExceptionInInitializerError();
        }

        menu.init();

        if(menu instanceof MenuLanterna) {

            Thread menuThread = new Thread((MenuLanterna)menu);
            menuThread.start();
            waitForMenu(menu);

            synchronized (menuThread) {
                menuThread.interrupt();
            }

        } else {

            waitForMenu(menu);

        }

        playerName = menu.getUsername();
        */

        try {
            tcpSocket = new Socket("localhost", 8080);
            udpSocket = new DatagramSocket(8779);
        } catch (ConnectException e) {
            System.err.println("Address is offline.");
            System.exit(0);
        } catch (IOException e) {
            e.printStackTrace();
        }

        runGame();

    }

    public void runGame() {

        grid = new GridLanterna();

        serverHandler = new ServerParser(this, grid);

        ClientNetworkTCP networkTCP = new ClientNetworkTCP(tcpSocket, serverHandler);
        ClientNetworkUDP networkUDP = new ClientNetworkUDP(udpSocket, tcpSocket.getInetAddress(), 8080, serverHandler);

        Thread tcpConnection = new Thread(networkTCP);
        Thread udpConnection = new Thread(networkUDP);

        UserInput input = new UserInputLanterna(((GridLanterna) grid).getScreen());

        Thread inputThread = new Thread(input);
        inputThread.start();

        input.setUdpConnection(networkUDP);

        tcpConnection.start();
        udpConnection.start();

        //TODO: loop grid update with server messages

        Timer gameLoop = new Timer();

        gameLoop.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {
                grid.updateScreen();
            }

        }, 34, 17);

    }

    public void waitForMenu(Menu menu) {

        while (!menu.isPhaseOver()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            continue;
        }

    }

    public void initGrid(InputStream stream) {
        this.grid = new GridLanterna();
    }
}
