package org.academiadecodigo.bootcamp.bomberdeal.server.gameobject.interfaces;

import org.academiadecodigo.bootcamp.bomberdeal.server.gamefield.Field;
import org.academiadecodigo.bootcamp.bomberdeal.server.helper.TileType;

public interface Interactable {

    void setField(Field field);

    int getX();

    int getY();

    String getTileType();

    void destroy();

}
