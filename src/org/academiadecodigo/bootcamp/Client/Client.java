package org.academiadecodigo.bootcamp.Client;

import java.io.*;

/**
 * Created by andre on 2/20/2017.
 */
public class Client {

    private Grid grid;
    private Position pos;

    public Client() {

        try {
            grid = new Grid(new FileReader("testing/cenas"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {

        Client client = new Client();

    }

}
