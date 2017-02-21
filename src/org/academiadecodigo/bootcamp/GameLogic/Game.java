package org.academiadecodigo.bootcamp.GameLogic;

/**
 * Created by codecadet on 2/21/17.
 */
public class Game {
    private CollisionChecker collisionChecker;
    private Player[] players;

    private void playerMove(Player player){
        if(collisionChecker.checkIsBombBlast(player.getNextPosition)){
            player.die();
        }
        else if(collisionChecker.check(player.getNextPosition)){
            player.setPositon(player.getNextPosition);
        }
    }

}
