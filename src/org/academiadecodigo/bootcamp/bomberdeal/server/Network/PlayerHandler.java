package org.academiadecodigo.bootcamp.bomberdeal.server.Network;

import org.academiadecodigo.bootcamp.bomberdeal.client.network.ServerMessages;
import org.academiadecodigo.bootcamp.bomberdeal.server.Player;

import java.io.*;
import java.net.DatagramSocket;
import java.net.Socket;


public class PlayerHandler implements Runnable {

    private Socket tcpSocket;
    private DatagramSocket udpSocket;
    private ClientParser clientParser;
    private NetworkTCP tcpConnection;
    private NetworkUDP udpConnection;

    private Player player;

    public PlayerHandler(Socket tcpSocket, DatagramSocket udpSocket, ClientParser parser) {

        this.tcpSocket = tcpSocket;
        this.udpSocket = udpSocket;

        this.clientParser = parser;

        try {

            tcpConnection = new NetworkTCP(tcpSocket, clientParser);
            udpConnection = new NetworkUDP(udpSocket, clientParser, tcpSocket.getInetAddress());

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Player thread created, sockets instanced.");

    }

    @Override
    public void run() {

            Thread tcpThread = new Thread(tcpConnection);
            Thread udpThread = new Thread(udpConnection);

            tcpThread.start();
            udpThread.start();

    }

    public synchronized void sendTCP(String message) {

        try {
            tcpConnection.send(message);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public synchronized void sendUDP(String message) {

        udpConnection.send(message);

    }

    /*long startTime = System.currentTimeMillis();
        long elapsedTime = 0L;
        String s;
        int playersQuantity = 0;

        System.out.println(Thread.currentThread().getName());
        try {
            switch (state) {
                case 0:
                    //while (elapsedTime < 30 * 1000 && (player.getSize() < 2 && player.getSize() < 5) ) {
                        if ((s = udpServer.listener()).contains("id")) { // rever palavra reservada
                            player = new Player(clientInterpret.parseUserName(s), clientInterpret.parseAddress(s));
                            send(fileEditor.getMap());
                            send("MAP:SENT");
                            player.setPlayerSpawn(playersQuantity++);
                            System.out.println("sent");
                            state = 1;

                            //}
                    }
                    break;

                case 1: // placement dos players
                    send("GAMESTART");
                    state = 2;
                    break;

                case 2:  // movements from the players

                    break;*/

}
