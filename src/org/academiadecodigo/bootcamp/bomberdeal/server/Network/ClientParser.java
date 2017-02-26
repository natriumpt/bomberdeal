package org.academiadecodigo.bootcamp.bomberdeal.server.Network;

import org.academiadecodigo.bootcamp.bomberdeal.client.grid.TileType;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

/**
 * Created by codecadet on 2/24/17.
 */
public class ClientParser {

    public InetAddress parseAddress(String receivedString) throws UnknownHostException { // primeiro index IP
        String []receivedFrame = receivedString.split(";");
        InetAddress inetAddress = InetAddress.getByName(receivedFrame[0]);
        return inetAddress;
    }

    public String parseUserName(String receivedString) {
        String []receivedFrame = receivedString.split(";");
        String userName = receivedFrame[1];
        return userName;
    }

    public void handleTCPMessage(String message) {

    }

    public void handleUDPMessage(String message) {

    }

}
