import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by codecadet on 2/19/17.
 */
public class TCPClient {

    BufferedReader inputStream;
    BufferedWriter outputStream;
    private int portNumber;
    private String localhost;
    private Socket socket;
    private MsgReceiver msgReceiver;


    public TCPClient(int portNumber, String localhost) throws IOException {
        this.localhost = localhost;
        this.portNumber = portNumber;
        socket = new Socket(localhost, portNumber);
        outputStream = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        inputStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }


    public void writeToServer(String s) throws IOException {
        outputStream.write(s + "\r\n");
        outputStream.flush();
    }


    public void runClient() throws IOException {

        Scanner scanner = new Scanner(System.in);
        MsgReceiver msgReceiver = new MsgReceiver(inputStream, socket);

        Thread t = new Thread(msgReceiver);
        t.start();

        while (true) {
            String message = scanner.nextLine();
            writeToServer(message);
        }

    }

    public static void main(String[] args) throws IOException {
        TCPClient tcpClient = new TCPClient(8080,"localhost");
        tcpClient.runClient();
    }
}
