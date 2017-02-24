package com.github.natriumpt.bomberdeal.server.Network;

import com.github.natriumpt.bomberdeal.server.FileEditor;

import java.io.*;
import java.net.Socket;


public class ClientDispatcher implements Runnable {

    private Socket clientSocket;
    BufferedReader in;
    BufferedWriter out;
    private NetworkTCP server;
    //UDPServer udpServer ;
    FileEditor fileEditor;



    public ClientDispatcher(Socket clientSocket, NetworkTCP server) throws IOException {
        fileEditor = new FileEditor();
        //udpServer = new UDPServer(7070);
        this.clientSocket = clientSocket;
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
        this.server = server;
    }

    public void run() {


            System.out.println(Thread.currentThread().getName());
            try {
                System.out.println(fileEditor.Loader());
                send(fileEditor.Loader());
                send("MAP:SENT");
                System.out.println("sent");
            } catch (IOException e) {
                e.printStackTrace();
            }
            //String message = in.readLine();
            //udpServer.receiveSend();





    }


    public void send(String message) throws IOException {
        out.write(message + "\r\n");
        out.flush();
    }

}
