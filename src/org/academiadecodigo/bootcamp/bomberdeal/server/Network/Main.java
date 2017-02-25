package org.academiadecodigo.bootcamp.bomberdeal.server.Network;

import java.io.IOException;

/**
 * Created by codecadet on 2/25/17.
 */
public class Main {

    public static void main(String[] args) throws IOException {

        NetworkTCP networkTCP = new NetworkTCP();
        networkTCP.runServer();

    }
}
