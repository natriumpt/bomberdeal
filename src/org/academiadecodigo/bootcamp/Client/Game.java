package org.academiadecodigo.bootcamp.Client;

import com.github.natriumpt.bomberdeal.Server.Network.NetworkTCP;
import com.github.natriumpt.bomberdeal.Server.Network.NetworkUDP;
import org.academiadecodigo.bootcamp.Client.Grid.Grid;
import org.academiadecodigo.bootcamp.Client.UserInput.UserInput;
import org.academiadecodigo.bootcamp.menu.Menu;

import org.academiadecodigo.bootcamp.Client.Network.ClientNetworkTCP;
import org.academiadecodigo.bootcamp.Client.Network.ClientNetworkUDP;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.DatagramSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by andre on 2/20/2017.
 */
public class Game implements Runnable {

    private Grid grid;
    private DatagramSocket udpSocket;
    private Socket tcpSocket;
    //TODO:
    //private Menu menu;

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
            grid = new Grid(tcpSocket.getInputStream());

            grid.init();

            UserInput input = new UserInput(grid.getScreen());

            Thread inputThread = new Thread(input);
            inputThread.start();

            System.out.println("after grid completion");

            ClientNetworkTCP networkTCP = new ClientNetworkTCP(tcpSocket);
            ClientNetworkUDP networkUDP = new ClientNetworkUDP(udpSocket, tcpSocket.getInetAddress(), 8080);


            Thread tcpConnection = new Thread(networkTCP);
            Thread udpConnection = new Thread(networkUDP);

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


    public static void main(String[] args) {

        Game client = new Game();

        Thread gameThread = new Thread(client);
        gameThread.start();

    }

    @Override
    public void run() {

        Menu menu = new Menu();

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
