package org.academiadecodigo.bootcamp.bomberdeal.client;

import org.academiadecodigo.bootcamp.bomberdeal.client.grid.Grid;
import org.academiadecodigo.bootcamp.bomberdeal.client.lanterna.MenuLanterna;
import org.academiadecodigo.bootcamp.bomberdeal.client.network.ServerParser;
import org.academiadecodigo.bootcamp.bomberdeal.client.network.ClientNetworkUDP;
import org.academiadecodigo.bootcamp.bomberdeal.client.network.ClientNetworkTCP;
import org.academiadecodigo.bootcamp.bomberdeal.client.lanterna.UserInputLanterna;
import org.academiadecodigo.bootcamp.bomberdeal.client.userinput.UserInput;
import org.academiadecodigo.bootcamp.bomberdeal.client.menu.SplashScreen;

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

        SplashScreen menu = new MenuLanterna();

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

        grid = new Grid();

        serverHandler = new ServerParser(grid);

        ClientNetworkTCP networkTCP = new ClientNetworkTCP(tcpSocket, serverHandler);
        ClientNetworkUDP networkUDP = new ClientNetworkUDP(udpSocket, tcpSocket.getInetAddress(), 8080, serverHandler);

        Thread tcpConnection = new Thread(networkTCP);
        Thread udpConnection = new Thread(networkUDP);

        /*while(((Grid)grid).getScreen() == null) {

            try {
                Thread.sleep(17);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }*/

        UserInput input = new UserInputLanterna((Grid)grid);
        Thread inputThread = new Thread(input);

        input.setUdpConnection(networkUDP);

        tcpConnection.start();
        udpConnection.start();
        inputThread.start();

        //TODO: loop grid update with server messages

        Timer gameLoop = new Timer();

        gameLoop.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {
                grid.updateScreen();
            }

        }, 50, 100);

    }

    public void waitForMenu(SplashScreen splashScreen) {

        while (!splashScreen.isPhaseOver()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            continue;
        }

    }

    public Grid getGrid() {
        return grid;
    }

    public void initGrid(InputStream stream) {
        this.grid = new Grid();
    }
}
