package com.github.natriumpt.bomberdeal.server.Network;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.SocketException;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;

/**
 * Created by codecadet on 2/22/17.
 */
public class NetworkUDP {
    DatagramPacket incoming;
    DatagramSocket socket;

    private LinkedList<com.github.natriumpt.bomberdeal.server.Network.ClientDispatcher> clients;
    private ServerSocket serverSocket;
    private String message;
    ExecutorService pool;

    public NetworkUDP(int portNumber) throws SocketException {
        byte[] recvBuffer = new byte[1500];
        this.incoming = new DatagramPacket(recvBuffer, recvBuffer.length);
        this.socket = new DatagramSocket(portNumber);
    }

    public String listener() throws IOException {
        socket.receive(incoming);
        if (incoming!=null) {
            byte[] data = incoming.getData();
            return new String(data, 0, incoming.getLength());
        }
        return null;
    }

    public void writer(String newString) throws IOException {

        byte[] sendBuffer = newString.getBytes();

        DatagramPacket sendPacket = new DatagramPacket(sendBuffer, 0, sendBuffer.length, incoming.getAddress(), incoming.getPort());

        socket.send(sendPacket);
        }
    }

    /*

    public void receiveSend() {
        try {
            while (true) {
                socket.receive(incoming);
                byte[] data = incoming.getData();
                String s = new String(data, 0, incoming.getLength());
                String[] positions = s.split(",");
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
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

        }
    }
    */
