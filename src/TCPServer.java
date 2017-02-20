import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by codecadet on 2/19/17.
 */
public class TCPServer {


    private LinkedList<ClientDispatcher> clients;
    private int portNumber = 8080;
    private ServerSocket serverSocket;
    private String message;
    ExecutorService pool;


    public TCPServer() throws IOException {
        this.serverSocket = new ServerSocket(portNumber);
        System.out.println("server.server connected on " + portNumber + "...");
        clients = new LinkedList<>();
        pool = Executors.newFixedThreadPool(3);
    }

    public void sendAll(String message) throws IOException {
        for (ClientDispatcher client : clients){
            client.send(message);
        }
    }

    public void runServer() throws IOException {

        Socket clientSocket = new Socket();
        System.out.println("Waiting for requests\n");

        while (true) {
            //pool.submit(new ClientDispatcher(clientSocket,this));
            clientSocket = serverSocket.accept();
            System.out.println("Request received.");

            ClientDispatcher clientDispatcher = new ClientDispatcher(clientSocket, this);
            clients.add(clientDispatcher);
            
            Thread t = new Thread(clientDispatcher);
            t.start();

        }
    }

    public static void main(String[] args) throws IOException {
       TCPServer tcpServer = new TCPServer();
        tcpServer.runServer();
    }
}
