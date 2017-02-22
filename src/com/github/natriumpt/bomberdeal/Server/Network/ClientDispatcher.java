package com.github.natriumpt.bomberdeal.Server.Network;

import com.github.natriumpt.bomberdeal.Client.Network;
import com.github.natriumpt.bomberdeal.Server.FileEditor;

import java.io.*;
import java.net.Socket;

/**
 * Created by codecadet on 2/22/17.
 */
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
        while(true) {
            System.out.println(Thread.currentThread().getName());
            try {
                send(fileEditor.Loader());
            } catch (IOException e) {
                e.printStackTrace();
            }
            //String message = in.readLine();
            //udpServer.receiveSend();

        }
    }


    public void send(String message) throws IOException {
        out.write(message + "\r\n");
        out.flush();
    }

}
