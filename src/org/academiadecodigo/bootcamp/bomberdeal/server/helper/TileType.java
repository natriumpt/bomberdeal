package org.academiadecodigo.bootcamp.bomberdeal.server.helper;

public enum TileType {

    FLOOR("0"),
    WALL("W"),
    BOMB("B"),
    CRATE("C"),
    FIRE("F"),
    PLAYER("P"),
    SPAWN("S"),
    POWERUP("@");

    private String symbol;

    TileType(String symbol){
        this.symbol = symbol;
    }

    public String getSymbol(){
        return symbol;
    }

    public static TileType getTileType(String type){

        for(int i = 0; i < TileType.values().length; i++) {

            if(type.equals(TileType.values()[i].getSymbol())) {
                return TileType.values()[i];
            }

        }

        return TileType.FLOOR;

    }
}
