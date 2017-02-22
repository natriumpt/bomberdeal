package org.academiadecodigo.bootcamp.Client.Network;

import org.academiadecodigo.bootcamp.Client.Game;

import java.io.*;
import java.net.Socket;
import java.util.Timer;

/**
 * Created by codecadet on 2/22/17.
 */
public class NetworkTCP implements Runnable {

    private Socket tcpSocket;
    private Game game;

    public NetworkTCP(Socket socket) {

        tcpSocket = socket;

    }

    @Override
    public void run() {

        try {

            NetworkTCPListener listener = new NetworkTCPListener(tcpSocket.getInputStream());
            DataOutputStream outStream = new DataOutputStream(tcpSocket.getOutputStream());

            Thread udpListener = new Thread(listener);
            udpListener.start();

            //TODO:
            //playerinfo
            //outStream.write(playerInfo.getBytes());

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public class NetworkTCPListener implements Runnable {

        private BufferedReader reader;

        public NetworkTCPListener(InputStream stream) {

            reader = new BufferedReader(new InputStreamReader(stream));

        }

        @Override
        public void run() {


            while (true) {

                try {

                    String message = reader.readLine();

                    while (message != null) {

                        message = message + reader.readLine();

                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }

                //TODO:
                //synchronized
                //game.updateScoreboard(message);

            }

        }
    }
}
