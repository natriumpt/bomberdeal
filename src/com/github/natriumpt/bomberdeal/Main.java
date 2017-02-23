package com.github.natriumpt.bomberdeal;

import com.github.natriumpt.bomberdeal.Server.Network.NetworkTCP;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {


        NetworkTCP networkTCP = new NetworkTCP();
        networkTCP.runServer();
    }
}
