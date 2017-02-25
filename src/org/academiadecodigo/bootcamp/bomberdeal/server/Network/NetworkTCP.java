package org.academiadecodigo.bootcamp.bomberdeal.server.Network;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;

/**
 * Created by codecadet on 2/22/17.
 */
public class NetworkTCP {

    private LinkedList<ClientDispatcher> clients;
    private int portNumber = 8080;
    private ServerSocket serverSocket;
    private String message;
    ExecutorService pool;


    public NetworkTCP() throws IOException {

        this.serverSocket = new ServerSocket(portNumber);
        System.out.println("serverold.serverold connected on " + portNumber + "...");
        clients = new LinkedList<>();
        //pool = Executors.newFixedThreadPool(3);

        File file = new File("res/bombermap2.txt");

        System.out.println(file.exists());
    }

    public void sendAll(String message) throws IOException {
        for (ClientDispatcher client : clients){
            client.send(message);
        }
    }

    public void runServer() throws IOException {

        Socket clientSocket = new Socket();
        System.out.println("Waiting for requests\n");

        while (true) {
            //pool.submit(new branco.udpprototype.ClientDispatcher(clientSocket,this));
            clientSocket = serverSocket.accept();
            System.out.println("Request received.");

            ClientDispatcher clientDispatcher = new ClientDispatcher(clientSocket, this);
            clients.add(clientDispatcher);

            Thread t = new Thread(clientDispatcher);
            t.start();

        }
    }
}
