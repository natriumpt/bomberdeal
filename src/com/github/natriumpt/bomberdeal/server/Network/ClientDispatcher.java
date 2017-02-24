package com.github.natriumpt.bomberdeal.server.Network;

import com.github.natriumpt.bomberdeal.server.FileEditor;
import com.github.natriumpt.bomberdeal.server.Player;

import java.io.*;
import java.net.Socket;


public class ClientDispatcher implements Runnable {

    private Socket clientSocket;
    BufferedReader in;
    BufferedWriter out;
    private com.github.natriumpt.bomberdeal.server.Network.NetworkTCP server;
    NetworkUDP udpServer ;
    FileEditor fileEditor;
    Player player;
    //ClientInterpret clientInterpret;

    public ClientDispatcher(Socket clientSocket, NetworkTCP server) throws IOException {
        fileEditor = new FileEditor();
        udpServer = new NetworkUDP(8080);
        this.clientSocket = clientSocket;
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
        this.server = server;
    }

    public void run() {
        long startTime = System.currentTimeMillis();
        long elapsedTime = 0L;
        String s;

            System.out.println(Thread.currentThread().getName());
            try {
                System.out.println(fileEditor.Loader());
                send(fileEditor.Loader());
                send("MAP:SENT");
                System.out.println("sent");

                //  while (elapsedTime < 30*1000 && (player.getSize() < 2)) {
                while(true) {
                    System.out.println(udpServer.listener());
                    udpServer.writer("10:10:F");
                }
                    //if ((s = udpServer.listener()).contains("playerconnected")) { // rever palavra reservada
                        //player = new Player();

                        //player.addPlayers();
                    //}
               // }
            } catch (IOException e) {
                e.printStackTrace();
            }
    }


    public void send(String message) throws IOException {
        out.write(message + "\r\n");
        out.flush();
    }

}
