package org.academiadecodigo.bootcamp.bomberdeal.server.Network;

import org.academiadecodigo.bootcamp.bomberdeal.client.grid.TileType;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    //TODO: parse TPC messages according to ServerMessagesClass

    public void handleTCPMessage(String message) {

    }

    //TODO: parse player actions through UDP messages

    public void handleUDPMessage(String message) {

        Pattern pattern = Pattern.compile("^(\\w+):(\\w+)$");
        Matcher matcher = pattern.matcher(message);

        while (matcher.find()) {

            if(matcher.group(1).equals("MOVEMENT")) {

                if(matcher.group(2).equals("LEFT")) {

                }
            }

            if(matcher.group(1).equals("ACTION")) {

            }



        }

    }

}
