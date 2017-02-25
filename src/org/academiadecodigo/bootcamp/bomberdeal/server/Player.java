package org.academiadecodigo.bootcamp.bomberdeal.server;

import java.net.InetAddress;
import java.util.ArrayList;

public class Player {

    private String name;
    private int x;
    private int y;
    private InetAddress id;


    public Player(String name, InetAddress id){
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setPlayerSpawn(int i){
           if (i == 0){ //tudo ao martelo
               setX(0);
               setY(0);
           }
           if (i == 1){
               setX(15);
               setY(0);
           }
           if (i == 2){
               setX(0);
               setY(15);
           }
           if (i == 3){
               setX(15);
               setY(15);
           }
    }

}
