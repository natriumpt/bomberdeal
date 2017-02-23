package org.academiadecodigo.bootcamp.Server.gamelogic;


/**
 * Created by codecadet on 2/21/17.
 */
public class CollisionChecker {
    private String map;

    public boolean checkCollidable(PositionServer position) {
        return position.isCollidable();
    }

    public boolean checkIsBombBlast(PositionServer position) {
        return position.getTileType().equals(TiletypeServer.FIRE.toString());
    }

    public String checkBlastDamage(PositionServer position){
        return TiletypeServer.getTileType(position.getTileType()).getType();
    }
}
