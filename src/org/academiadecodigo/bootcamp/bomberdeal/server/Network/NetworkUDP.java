package org.academiadecodigo.bootcamp.bomberdeal.server.Network;

import java.io.IOException;
import java.net.*;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;

/**
 * Created by codecadet on 2/22/17.
 */
public class NetworkUDP implements Runnable {

    private DatagramSocket udpSocket;
    private byte[] receiveBuffer;
    private byte[] sendBuffer;
    private InetAddress playerAddress;
    private ClientParser parser;

    public NetworkUDP(DatagramSocket socket, ClientParser parser, InetAddress playerAddress) {

        this.udpSocket = socket;
        this.playerAddress = playerAddress;

        this.parser = parser;

        receiveBuffer = new byte[1500];

    }

    @Override
    public void run() {

        while(true) {

            DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);

            try {

                udpSocket.receive(receivePacket);

                synchronized (parser) {
                    parser.handleUDPMessage(new String(receiveBuffer, 0, receivePacket.getLength()), playerAddress);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    public void send(String message) {

        sendBuffer = message.getBytes();

        DatagramPacket packet = new DatagramPacket(sendBuffer, sendBuffer.length, playerAddress, 8779);

        try {

            System.out.println("SENDING UDP PACKET!");
            udpSocket.send(packet);
            System.out.println("UDP PACKET SENT.");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
