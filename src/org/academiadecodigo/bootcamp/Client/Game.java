package org.academiadecodigo.bootcamp.Client;

import org.academiadecodigo.bootcamp.Client.Grid.Grid;
import org.academiadecodigo.bootcamp.Client.Grid.GridLanterna;
import org.academiadecodigo.bootcamp.Client.UserInput.LanternaUserInput;
import org.academiadecodigo.bootcamp.Client.UserInput.UserInput;
import org.academiadecodigo.bootcamp.menu.LanternaMenu;
import org.academiadecodigo.bootcamp.menu.Menu;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.DatagramSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * Created by andre on 2/20/2017.
 */
public class Game implements Runnable {

    private Grid grid;
    private DatagramSocket udpSocket;
    private Socket tcpSocket;
    //TODO:
    //private LanternaMenu menu;

    public Game() {

        //TODO:
        //Add menu and start menu phase




    }

    public void runGame() {

        try {

            //TODO:
            //Instance sockets
            //Instance userInput

            System.out.println("here at rungame");
            grid = new GridLanterna(tcpSocket.getInputStream());

            //NetworkTCP networkTCP = new NetworkTCP(tcpSocket);
            //NetworkUDP networkUDP = new NetworkUDP(udpSocket, menu.getHostname, portNumber);

            grid.init();

            UserInput input= new LanternaUserInput(((GridLanterna) grid).getScreen());

            Thread inputThread = new Thread(input);
            inputThread.start();


            /*System.out.println("after grid completion");

            ClientNetworkTCP networkTCP = new ClientNetworkTCP(tcpSocket);
            ClientNetworkUDP networkUDP = new ClientNetworkUDP(udpSocket, tcpSocket.getInetAddress(), portNumber);


            Thread tcpConnection = new Thread(networkTCP);
//            Thread udpConnection = new Thread(networkUDP);

            tcpConnection.start();
//            udpConnection.start();

        */
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


    public static void main(String[] args) {

        Game client = new Game();

        Thread gameThread = new Thread(client);
        gameThread.start();

    }

    @Override
    public void run() {

        Menu menu = new LanternaMenu();

        Thread menuThread = new Thread(menu);
        menuThread.start();


            while (!menu.isPhaseOver()) {
                System.out.println("cenas.");
            }


        try {
            tcpSocket = new Socket("localhost", 8080);
            udpSocket = new DatagramSocket(8779);
        } catch (IOException e) {
            e.printStackTrace();
        }

        menuThread.interrupt();

        //Transition to game phase
        runGame();

    }
}
