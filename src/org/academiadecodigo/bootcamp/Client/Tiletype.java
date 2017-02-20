package org.academiadecodigo.bootcamp.Client;

/**
 * Created by andre on 2/20/2017.
 */
public enum Tiletype {

    FLOOR("0"),
    WALL("1"),
    PLAYER("P"),
    BOMB("B"),
    FIRE("F"),
    BOX("C"),
    POWERUP("U");

    private String type;

    Tiletype(String value) {
        this.type = value;
    }

    public String getType() {
        return type;
    }

    public static Tiletype getTileType(String type){

        for(int i = 0; i < Tiletype.values().length; i++) {

            if(type.equals(Tiletype.values()[i])) {
                return Tiletype.values()[i];
            }

        }

        return null;
    }
}
