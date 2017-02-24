package org.academiadecodigo.bootcamp.Client.UserInput;

import org.academiadecodigo.bootcamp.Client.Network.ClientNetworkUDP;

/**
 * Created by codecadet on 2/24/17.
 */
public interface UserInput extends Runnable {

    void setUdpConnection(ClientNetworkUDP client);

}
