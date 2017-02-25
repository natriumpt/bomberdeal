package org.academiadecodigo.bootcamp.bomberdeal.server.Network;

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
    //ClientInterpret clientInterpret;

    public ClientDispatcher(Socket clientSocket, NetworkTCP server) throws IOException {
        fileEditor = new FileEditor();
        udpServer = new NetworkUDP(8080);
        this.clientSocket = clientSocket;
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
        this.server = server;
        state = 0;
    }

    public void run() {
        long startTime = System.currentTimeMillis();
        long elapsedTime = 0L;
        String s;

            System.out.println(Thread.currentThread().getName());
            try {
                //switch (state){
                  //  case 0:
                    //    while (elapsedTime < 30*1000 && (player.getSize() < 2)) {


                      //  }

                      //  break;

                send("SENDING");
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
