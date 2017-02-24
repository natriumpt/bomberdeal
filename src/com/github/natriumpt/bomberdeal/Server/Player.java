package com.github.natriumpt.bomberdeal.Server;

import java.util.ArrayList;

public class Player {

    private String name;
    private int x;
    private int y;

    private ArrayList<Player> players;

    public Player(){
        players = new ArrayList<>();
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

    public int getSize(){
        return players.size();
    }

    public void addPlayers(Player player){
        players.add(player);
    }

}
