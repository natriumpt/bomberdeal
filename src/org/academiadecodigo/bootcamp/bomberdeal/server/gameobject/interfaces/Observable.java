package org.academiadecodigo.bootcamp.bomberdeal.server.gameobject.interfaces;

import java.net.InetAddress;

/**
 * Created by codecadet on 2/25/17.
 */
public interface Observable {

    void update(Interactable gameObject);

    void convertAction(String movement, InetAddress inetAddress);

}
