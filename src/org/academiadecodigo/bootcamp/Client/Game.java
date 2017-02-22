package org.academiadecodigo.bootcamp.Client;

import org.academiadecodigo.bootcamp.Client.Grid.Grid;
import org.academiadecodigo.bootcamp.Client.Grid.Position;

import java.io.*;
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

        //Transition to game phase

    }

    public void runGame(String hostName, int portNumber) {
        
        try {

            //TODO:
            //Instance sockets

            tcpSocket = new Socket(hostName, portNumber);
            udpSocket = new DatagramSocket(8779);

            grid = new Grid(tcpSocket.getInputStream());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        grid.init();

        //TODO:
        // Game phase loop

        while (true) {



        }

    }



    public static void main(String[] args) {

        Game client = new Game();

    }

}
