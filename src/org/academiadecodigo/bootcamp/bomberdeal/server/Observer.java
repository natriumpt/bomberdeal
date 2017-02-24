package org.academiadecodigo.bootcamp.bomberdeal.server;

import org.academiadecodigo.bootcamp.bomberdeal.server.gameobjects.Bomb;

public abstract class Observer {
    public abstract void update(Bomb bomb);
}
