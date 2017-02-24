package org.academiadecodigo.bootcamp.Client;

import org.academiadecodigo.bootcamp.Client.Grid.GridLanterna;
import org.academiadecodigo.bootcamp.Client.Network.ClientNetworkUDP;
import org.academiadecodigo.bootcamp.Client.Grid.Grid;
import org.academiadecodigo.bootcamp.Client.Network.ClientNetworkTCP;
import org.academiadecodigo.bootcamp.Client.UserInput.LanternaUserInput;
import org.academiadecodigo.bootcamp.Client.UserInput.UserInput;
import org.academiadecodigo.bootcamp.Client.Menu.LanternaMenu;
import org.academiadecodigo.bootcamp.Client.Menu.Menu;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.DatagramSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * Created by andre on 2/20/2017.
 */
public class Game {

    private Grid grid;
    private DatagramSocket udpSocket;
    private Socket tcpSocket;
    private String playerName;

    public static void main(String[] args) {

        Game bomberdeal = new Game();
        bomberdeal.startGame();

    }

    public void startGame() {

        Menu menu = new LanternaMenu();

        if(menu == null) {
            throw new ExceptionInInitializerError();
        }

        menu.init();

        if(menu instanceof LanternaMenu) {

            Thread menuThread = new Thread((LanternaMenu)menu);
            menuThread.start();
            waitForMenu(menu);
            synchronized (menuThread) {
                menuThread.interrupt();
            }

        } else {

            waitForMenu(menu);

        }

        try {
            tcpSocket = new Socket("localhost", 8080);
            udpSocket = new DatagramSocket(8779);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Transition to game phase
        runGame();

    }

    public void runGame() {

        try {

            grid = new GridLanterna(tcpSocket.getInputStream());

            grid.init();

            UserInput input= new LanternaUserInput(((GridLanterna) grid).getScreen());

            Thread inputThread = new Thread(input);
            inputThread.start();

            ClientNetworkTCP networkTCP = new ClientNetworkTCP(tcpSocket);
            ClientNetworkUDP networkUDP = new ClientNetworkUDP(udpSocket, tcpSocket.getInetAddress(), 8080);

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
