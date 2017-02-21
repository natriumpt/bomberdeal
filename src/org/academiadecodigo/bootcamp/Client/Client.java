package org.academiadecodigo.bootcamp.Client;

import org.academiadecodigo.bootcamp.Client.Grid.Grid;
import org.academiadecodigo.bootcamp.Client.Grid.Position;

import java.io.*;

/**
 * Created by andre on 2/20/2017.
 */
public class Client {

    private Grid grid;
    private Position pos;

    public Client() {

        try {
            grid = new Grid(new FileReader("test-res/cenas"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {

        Client client = new Client();

    }

}
