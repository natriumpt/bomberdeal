package org.academiadecodigo.bootcamp.bomberdeal.server.helper;

import org.academiadecodigo.bootcamp.bomberdeal.server.gameobject.Bomb;
import org.academiadecodigo.bootcamp.bomberdeal.server.gameobject.Player;
import org.academiadecodigo.bootcamp.bomberdeal.server.gameobject.PowerUp;

/**
 * Created by codecadet on 2/25/17.
 */
public class PowerUpHandler {
    public Player[] players;

    public PowerUpHandler(Player[]players){
        this.players = players;
    }

    public void assignPowerUp(PowerUp powerup, Player player){
        if(powerup.getPowerUpType() == PowerUpType.BOMBS){
           player.increaseBombs(player);
        }
        if(powerup.getPowerUpType() == PowerUpType.RANGE){
            for(int i = 0; i < player.getNrOfBombs(); i++){
                player.getBomb(i).setRange();
            }
        }
    }
}

