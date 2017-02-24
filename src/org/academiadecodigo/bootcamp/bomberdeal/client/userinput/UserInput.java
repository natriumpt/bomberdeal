package org.academiadecodigo.bootcamp.bomberdeal.client.userinput;

import org.academiadecodigo.bootcamp.bomberdeal.client.network.ClientNetworkUDP;

public interface UserInput extends Runnable {

    void setUdpConnection(ClientNetworkUDP client);

}
