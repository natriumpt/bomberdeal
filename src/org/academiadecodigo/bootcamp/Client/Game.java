package org.academiadecodigo.bootcamp.Client;

import org.academiadecodigo.bootcamp.Client.Grid.Grid;
import org.academiadecodigo.bootcamp.Client.Network.ClientNetworkTCP;
import org.academiadecodigo.bootcamp.Client.Network.ClientNetworkUDP;
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
    //TODO:
    //private Menu menu;

    public Game() {

        //TODO:
        //Add menu and start menu phase
        //Menu menu = new Menu();
        //menu.init();
        //Transition to game phase

    }

    public void runGame(String hostName, int portNumber) {

        try {

            //TODO:
            //Instance sockets
            //Instance userInput
            //

            tcpSocket = new Socket("localhost", 8080);
            udpSocket = new DatagramSocket(8779);

            grid = new Grid(tcpSocket.getInputStream());

            grid.init();

            /*System.out.println("after grid completion");

            ClientNetworkTCP networkTCP = new ClientNetworkTCP(tcpSocket);
            ClientNetworkUDP networkUDP = new ClientNetworkUDP(udpSocket, tcpSocket.getInetAddress(), portNumber);

            Thread tcpConnection = new Thread(networkTCP);
            Thread udpConnection = new Thread(networkUDP);

            tcpConnection.start();
            udpConnection.start();

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

        client.runGame("192.168.0.123", 8080);

    }

}
