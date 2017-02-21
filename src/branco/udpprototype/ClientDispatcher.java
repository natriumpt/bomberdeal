package branco.udpprototype;

import java.io.*;
import java.net.Socket;

/**
 * Created by codecadet on 2/19/17.
 */
public class ClientDispatcher implements Runnable{

    private Socket clientSocket;
    BufferedReader in;
    BufferedWriter out;
    private TCPServer server;
    UDPServer udpServer ;



    public ClientDispatcher(Socket clientSocket, TCPServer server) throws IOException {
        udpServer = new UDPServer(7070);
        this.clientSocket = clientSocket;
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
        this.server = server;
    }

    public void run() {
        while(true) {
                System.out.println(Thread.currentThread().getName());
                //String message = in.readLine();
                udpServer.receiveSend();

        }
    }


    public void send(String message) throws IOException {
        out.write(message + "\r\n");
        out.flush();
    }
}
