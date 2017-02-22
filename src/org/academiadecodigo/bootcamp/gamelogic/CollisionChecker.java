package org.academiadecodigo.bootcamp.gamelogic;

/**
 * Created by codecadet on 2/21/17.
 */
public class CollisionChecker {
    private String map;

    public boolean check(PositionServer position) {
        return position.isCollidable();
    }

    public boolean checkIsBombBlast(PositionServer position) {
        return position.getTileType().equals(TiletypeServer.FIRE.toString());
    }
}
