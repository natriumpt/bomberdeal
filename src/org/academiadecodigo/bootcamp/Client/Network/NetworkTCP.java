package org.academiadecodigo.bootcamp.Client.Network;

import org.academiadecodigo.bootcamp.Client.Game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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

            try {

                String message = reader.readLine();

                while (message != null) {

                    message = message + reader.readLine();

                }

            } catch (IOException e) {
                e.printStackTrace();
            }

            //TODO:
            //updateScoreboard(message);

        }

    }
}
