package org.academiadecodigo.bootcamp.bomberdeal.client.network;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by codecadet on 2/22/17.
 */
public class ClientNetworkUDP implements Runnable {

    private DatagramSocket udpSocket;
    private int portNumber;
    private InetAddress serverAddress;
    private byte[] sendBuffer;
    private byte[] receiveBuffer;
    private NetworkUDPReceiver udpReceiver;
    private ServerParser parser;

    public ClientNetworkUDP(DatagramSocket socket, InetAddress address, int portNumber, ServerParser parser) {

        udpSocket = socket;
        serverAddress = address;
        this.portNumber = portNumber;

        sendBuffer = new byte[1500];
        receiveBuffer = new byte[1500];

        this.parser = parser;

    }

    @Override
    public void run() {

        Timer timer = new Timer(true);
        //timer.scheduleAtFixedRate(createPacketSenderTask(createNewDat), 0, 17);

        Thread udpListenerThread = new Thread(new NetworkUDPReceiver());
        udpListenerThread.start();

    }

    public void sendPacket(String playerAction) {

        sendBuffer = playerAction.getBytes();

        DatagramPacket packet = new DatagramPacket(sendBuffer, sendBuffer.length, serverAddress, portNumber);

        try {

            udpSocket.send(packet);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public TimerTask createPacketSenderTask(DatagramPacket packet) {

        return new TimerTask() {

            @Override
            public void run() {

                try {
                    udpSocket.send(packet);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        };
    }

    public class NetworkUDPReceiver implements Runnable {

        @Override
        public void run() {

            while(true) {

                DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
                try {

                    udpSocket.receive(receivePacket);

                    System.out.println("UDP parser");

                    synchronized (parser) {
                        parser.handleUDPMessage(new String(receiveBuffer, 0, receivePacket.getLength()), udpSocket);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

        }

    }

}
