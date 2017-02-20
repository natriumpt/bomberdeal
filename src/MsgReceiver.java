import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by codecadet on 2/19/17.
 */
public class MsgReceiver implements Runnable {

    BufferedReader inputStream;
    UDPClient udpClient;

    public MsgReceiver(BufferedReader inputStream, Socket clientSocket) throws IOException {;
        this.inputStream = inputStream;
        udpClient = new UDPClient(7070);
    }

    @Override
    public void run() {
        while (true) {
            try{
                System.out.println(inputStream.readLine());
                udpClient.sendMessage();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}