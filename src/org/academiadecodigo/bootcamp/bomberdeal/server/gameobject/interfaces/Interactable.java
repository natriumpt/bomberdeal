package org.academiadecodigo.bootcamp.bomberdeal.server.gameobject.interfaces;

import org.academiadecodigo.bootcamp.bomberdeal.server.helper.TileType;

public interface Interactable {

    int getX();

    int getY();

    TileType getTileType();

    void destroy();

}
