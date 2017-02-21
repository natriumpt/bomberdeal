package org.academiadecodigo.bootcamp.GameLogic;

/**
 * Created by codecadet on 2/21/17.
 */
public class CollisionChecker {
    private String map;

    public boolean check(Position position) {
        if (isWall(position) || isBox(position) || isPlayer(position) || isBomb(position)) {
            return false;
        }
        return true;
    }

    public boolean checkIsBombBlast(Position position) {
        if (isBombBlast(position)) {
            return true;
        }
        return false;
    }

    public void bombBlastDamage

    private boolean isBombBlast(Position position) {
        return false;
    }

    private boolean isBomb(Position position) {
        return false;
    }

    private boolean isPlayer(Position position) {
        return false;
    }

    private boolean isBox(Position position) {
        return false;
    }

    private boolean isWall(Position position) {
    }
}
