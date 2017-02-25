package org.academiadecodigo.bootcamp.Server;

import org.academiadecodigo.bootcamp.Server.gridserver.GridServer;

/**
 * Created by codecadet on 2/24/17.
 */
public class MainTest {
    public static void main(String[] args) {

        GridServer grid = new GridServer(5, 5);

        for(int i = 0; i < 5; i++){
            grid.getPosition(i, 0).setTileType("W");
        }

        grid.getPosition(2,2).setTileType("P");
        grid.getPlayer(0).setPosition(grid.getPosition(2,2));
        grid.getPlayer(0).moveLeft()



    }
}
