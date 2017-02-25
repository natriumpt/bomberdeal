package org.academiadecodigo.bootcamp.bomberdeal.server.helper;

public enum TileType {

    EMPTY("0"),
    WALL("W"),
    BOMB("B"),
    CRATE("C"),
    FIRE("F"),
    PLAYER("P"),
    POWERUP("@");


    private String symbol;

    TileType(String symbol){
        this.symbol = symbol;
    }

    public String getSymbol(){
        return symbol;
    }

}
