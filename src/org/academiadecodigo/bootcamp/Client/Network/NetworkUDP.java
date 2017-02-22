package org.academiadecodigo.bootcamp.Client.Network;

import javax.xml.crypto.Data;
import java.net.DatagramSocket;

/**
 * Created by codecadet on 2/22/17.
 */
public class NetworkUDP implements Runnable {

    private DatagramSocket udpSocket;

    public NetworkUDP(DatagramSocket socket) {

        udpSocket = socket;

    }

    @Override
    public void run() {



    }

    public class NetworkUDPListener implements Runnable {

        @Override
        public void run() {

        }

    }
}
