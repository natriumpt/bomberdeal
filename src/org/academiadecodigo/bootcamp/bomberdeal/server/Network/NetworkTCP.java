package org.academiadecodigo.bootcamp.bomberdeal.server.Network;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;

/**
 * Created by codecadet on 2/22/17.
 */
public class NetworkTCP implements Runnable {

    private Socket clientSocket;
    private ClientParser parser;
    private BufferedReader inStream;
    private PrintWriter outStream;

    public NetworkTCP(Socket clientSocket, ClientParser parser) throws IOException {

        this.clientSocket = clientSocket;
        this.parser = parser;

        inStream = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        outStream = new PrintWriter(clientSocket.getOutputStream());

    }

    @Override
    public void run() {

        try {

            String playerMessage = inStream.readLine();

            while (playerMessage != null) {

                parser.handleTCPMessage(playerMessage);
                playerMessage = inStream.readLine();

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void send(String message) {

        System.out.println("SENDIND TCP MESSAGE");

        outStream.write(message + "\r\n");
        outStream.flush();

        System.out.println("TCP MESSAGE SENT.");

    }

}
