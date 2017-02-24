package org.academiadecodigo.bootcamp.Client.Network;

import javax.xml.crypto.Data;
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
        //timer.scheduleAtFixedRate(createPacketSenderTask(createNewDat), 0, 17);

        Thread udpListenerThread = new Thread(new NetworkUDPReceiver());
        udpListenerThread.start();

    }

    public void sendPacket(String playerAction) {

        String playerMessage = new String(receiveBuffer);

        DatagramPacket packet = new DatagramPacket(sendBuffer, sendBuffer.length, serverAddress, portNumber);

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

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

        }

    }
}
