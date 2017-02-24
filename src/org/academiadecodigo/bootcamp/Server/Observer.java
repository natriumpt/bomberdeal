package org.academiadecodigo.bootcamp.Server;

import org.academiadecodigo.bootcamp.Server.gamelogic.Bomb;

/**
 * Created by codecadet on 2/24/17.
 */
public abstract class Observer {
    public abstract void update(Bomb bomb);
}
