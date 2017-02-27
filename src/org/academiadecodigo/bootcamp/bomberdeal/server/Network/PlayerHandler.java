package org.academiadecodigo.bootcamp.bomberdeal.server.Network;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.academiadecodigo.bootcamp.bomberdeal.server.gameobject.Player;

import java.io.*;
import java.net.DatagramSocket;
import java.net.InetAddress;
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
            udpConnection = new NetworkUDP(udpSocket, clientParser, tcpSocket.getInetAddress(), this);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void run() {

        Thread tcpThread = new Thread(tcpConnection);
        Thread udpThread = new Thread(udpConnection);

        tcpThread.start();
        udpThread.start();

    }

    public synchronized void sendTCP(String message) {
        tcpConnection.send(message);
    }

    public synchronized void sendUDP(String message) {
        udpConnection.send(message);
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

}
