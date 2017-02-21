package branco.udpprototype;

import java.io.IOException;
import java.net.*;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;

/**
 * Created by codecadet on 2/8/17.
 */
public class UDPServer{

    DatagramPacket incoming;
    DatagramSocket socket;

    private LinkedList<ClientDispatcher> clients;
    private ServerSocket serverSocket;
    private String message;
    ExecutorService pool;

    public UDPServer(int portNumber) throws SocketException {
        byte[] recvBuffer = new byte[65536];

        this.incoming = new DatagramPacket(recvBuffer,recvBuffer.length);
        this.socket = new DatagramSocket(portNumber);
    }

    public void receiveSend(){
        try{
            while(true) {
                socket.receive(incoming);
                byte[] data = incoming.getData();
                String s = new String(data, 0, incoming.getLength());
                String [] positions = s.split(",");
                int col = Integer.parseInt(positions[0]);
                col++;
                int row = Integer.parseInt(positions[1]);
                row++;
                String novaString = row + "," + col;
                System.out.println(novaString);
                //s = s.toUpperCase();
                DatagramPacket sendPacket = new DatagramPacket(novaString.getBytes(), novaString.getBytes().length, incoming.getAddress(), incoming.getPort());
                socket.send(sendPacket);
                System.out.println(novaString);
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        finally{

        }
    }
    public static void main(String[] args) throws UnknownHostException, InterruptedException, SocketException {
        UDPServer udpServer = new UDPServer(8080);
        udpServer.receiveSend();
    }



}