package org.academiadecodigo.bootcamp.bomberdeal.server.gameobject;

import org.academiadecodigo.bootcamp.bomberdeal.server.gamefield.Field;
import org.academiadecodigo.bootcamp.bomberdeal.server.gameobject.interfaces.Interactable;
import org.academiadecodigo.bootcamp.bomberdeal.server.helper.PowerUpType;
import org.academiadecodigo.bootcamp.bomberdeal.server.helper.TileType;
import org.academiadecodigo.bootcamp.bomberdeal.server.helper.Util;

public class PowerUp implements Interactable{

    private int x;
    private int y;
    private String tileType;
    private PowerUpType powerUpType;


    public PowerUp(){

        tileType = TileType.POWERUP.getSymbol();
        powerUpType = PowerUpType.values()[Util.rndIdxInRange(PowerUpType.values().length)];

    }

    public PowerUpType getPowerUpType() {
        return powerUpType;
    }

    @Override
    public void setField(Field field) {

    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public String getTileType() {
        return tileType;
    }

    @Override
    public void destroy() {

    }
}
