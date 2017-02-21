package branco.udpprototype;

import com.googlecode.lanterna.gui.GUIScreen;
import com.googlecode.lanterna.gui.Theme;
import com.googlecode.lanterna.gui.component.Label;
import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;
import java.net.*;

/**
 * Created by codecadet on 2/8/17.
 */
public class UDPClient {

    int port;
    InetAddress host;

    public UDPClient(int port) throws UnknownHostException {
        this.host = InetAddress.getByName("localhost");
        this.port = port;
    }

    public void sendMessage() throws InterruptedException, SocketException {
        String s;
        int col = 0;
        int row = 0;
        byte[] buffer = new byte[65536];
        s = (String) (row + "," + col);
        DatagramSocket sock = new DatagramSocket();
        String [] positions = s.split(",");
        Field.init(10,10);
        Field.draw(row,col,"xx");
        Thread.sleep(3000);

        try{
            while (Integer.parseInt(positions[0]) < 8 ) {
                DatagramPacket dp = new DatagramPacket(s.getBytes(), s.length(), host, port);
                sock.send(dp);
                DatagramPacket reply = new DatagramPacket(buffer, buffer.length);

                sock.receive(reply);
                byte[] data = reply.getData();
                s = new String(data, 0, reply.getLength());
                positions = s.split(",");
                System.out.println(s);
                Field.draw(Integer.parseInt(positions[0]),Integer.parseInt(positions[1]),"xx");
                Thread.sleep(1000);
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        finally{

        }


    }

    public static void main(String[] args) throws UnknownHostException, InterruptedException, SocketException {
        UDPClient udpClient = new UDPClient(8080);
        udpClient.sendMessage();
    }

}