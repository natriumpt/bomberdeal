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
import java.net.DatagramSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

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

        Menu menu = new MenuLanterna();

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
        } catch (IOException e) {
            e.printStackTrace();
        }

        runGame();

    }

    public void runGame() {

        try {

            grid = new GridLanterna(tcpSocket.getInputStream());

            grid.init();

            serverHandler = new ServerParser(this, grid);

            UserInput input= new UserInputLanterna(((GridLanterna) grid).getScreen());

            Thread inputThread = new Thread(input);
            inputThread.start();

            ClientNetworkTCP networkTCP = new ClientNetworkTCP(tcpSocket);
            ClientNetworkUDP networkUDP = new ClientNetworkUDP(udpSocket, tcpSocket.getInetAddress(), 8080, serverHandler);

            Thread tcpConnection = new Thread(networkTCP);
            Thread udpConnection = new Thread(networkUDP);

            input.setUdpConnection(networkUDP);

            tcpConnection.start();
            udpConnection.start();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //TODO:
        // Game phase loop

        while(true) {

        }

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
}
