package org.academiadecodigo.bootcamp.bomberdeal.server;

import org.academiadecodigo.bootcamp.bomberdeal.client.Game;

/**
 * Created by andre on 2/26/2017.
 */
public class TesterClass {

    public static void main(String[] args) {

        Server server = new Server(8080);
        server.startServer();

    }
}
