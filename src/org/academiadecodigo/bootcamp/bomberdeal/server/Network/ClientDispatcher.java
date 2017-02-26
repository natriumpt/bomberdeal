package org.academiadecodigo.bootcamp.bomberdeal.server.Network;

import org.academiadecodigo.bootcamp.bomberdeal.client.network.ServerMessages;
import org.academiadecodigo.bootcamp.bomberdeal.server.Player;

import java.io.*;
import java.net.Socket;


public class ClientDispatcher implements Runnable {

    private Socket clientSocket;
    BufferedReader in;
    BufferedWriter out;
    private NetworkTCP server;
    NetworkUDP udpServer ;
    FileEditor fileEditor;
    private int state;
    Player player;
    ClientInterpret clientInterpret;

    public ClientDispatcher(Socket clientSocket, NetworkTCP server) throws IOException {

        fileEditor = new FileEditor();
        udpServer = new NetworkUDP(8080);

        this.clientSocket = clientSocket;

        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

        this.server = server;
        state = 0;
        clientInterpret = new ClientInterpret();
    }

    public void run() {
        long startTime = System.currentTimeMillis();
        long elapsedTime = 0L;
        String s;
        int playersQuantity = 0;

        System.out.println(Thread.currentThread().getName());
        try {/*
            switch (state) {
                case 0:
                    //while (elapsedTime < 30 * 1000 && (player.getSize() < 2 && player.getSize() < 5) ) {
                        if ((s = udpServer.listener()).contains("id")) { // rever palavra reservada
                            player = new Player(clientInterpret.parseUserName(s), clientInterpret.parseAddress(s));
                            send(fileEditor.Loader());
                            send("MAP:SENT");
                            player.setPlayerSpawn(playersQuantity++);
                            System.out.println("sent");
                            state = 1;

                            //}
                    }
                    break;

                case 1: // placement dos players
                    send("GAMESTART");
                    state = 2;
                    break;

                case 2:  // movements from the players

                    break;*/

                send(ServerMessages.SERVER_MAP_SENDING_LAYOUT);

                System.out.println(fileEditor.Loader());
                send(fileEditor.Loader());

                send(ServerMessages.SERVER_MAP_LAYOUT_COMPLETE);

                System.out.println(ServerMessages.SERVER_MAP_LAYOUT_COMPLETE);

                //  while (elapsedTime < 30*1000 && (player.getSize() < 2)) {

                while(true) {

                    System.out.println(udpServer.listener());
                    udpServer.writer("10:10:F");

                }
                    //if ((s = udpServer.listener()).contains("playerconnected")) { // rever palavra reservada
                        //player = new Player();

                        //player.addPlayers();
                    //}
              //  }

                //  while (elapsedTime < 30*1000 && (player.getSize() < 2)) {
                //while (true) {
                  //  System.out.println(udpServer.listener());
                  //  udpServer.writer("10:10:F");
               // }

                //  }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void send(String message) throws IOException {
        out.write(message + "\r\n");
        out.flush();
    }

}
