package org.academiadecodigo.bootcamp.bomberdeal.serverold.gameobjects;

import org.academiadecodigo.bootcamp.bomberdeal.serverold.gamefield.Position;

public class CollisionChecker {
    private String map;

    public boolean checkCollidable(Position position) {
        return position.isCollidable();
    }

    public boolean checkIsBombBlast(Position position) {
        return position.getTileType().equals(TileType.FIRE.toString());
    }

    public String checkTileType(Position position) {
        return TileType.getTileType(position.getTileType()).getType();
    }

}
