package org.academiadecodigo.bootcamp.Client.Network;

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

    public ClientNetworkUDP(DatagramSocket socket, InetAddress address, int portNumber) {

        udpSocket = socket;
        serverAddress = address;
        this.portNumber = portNumber;

        sendBuffer = new byte[1500];
        receiveBuffer = new byte[1500];

    }

    @Override
    public void run() {

        Timer timer = new Timer(true);
        timer.scheduleAtFixedRate(createPacketSenderTask(), 0, 17);

        Thread udpListener = new Thread(new NetworkUDPListener());
        udpListener.start();

    }

    public TimerTask createPacketSenderTask() {

        return new TimerTask() {

            @Override
            public void run() {

                DatagramPacket packet = new DatagramPacket(sendBuffer, sendBuffer.length, serverAddress, portNumber);

                try {
                    udpSocket.send(packet);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

        };

    }

    public class NetworkUDPListener implements Runnable {

        @Override
        public void run() {

            while(true) {

                DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
                try {

                    udpSocket.receive(receivePacket);

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

        }

    }
}
