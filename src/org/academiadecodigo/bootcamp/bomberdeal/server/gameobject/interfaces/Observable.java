package org.academiadecodigo.bootcamp.bomberdeal.server.gameobject.interfaces;

import org.academiadecodigo.bootcamp.bomberdeal.server.Network.PlayerHandler;

import java.net.InetAddress;

/**
 * Created by codecadet on 2/25/17.
 */
public interface Observable {

    void update(Interactable gameObject);

}
