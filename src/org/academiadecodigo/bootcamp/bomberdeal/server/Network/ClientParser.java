package org.academiadecodigo.bootcamp.bomberdeal.server.Network;

import org.academiadecodigo.bootcamp.bomberdeal.client.grid.TileType;
import org.academiadecodigo.bootcamp.bomberdeal.server.gameobject.Player;
import org.academiadecodigo.bootcamp.bomberdeal.server.gameobject.interfaces.Observable;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by codecadet on 2/24/17.
 */
public class ClientParser {

    private Player player;
    private Observable observer;

    public ClientParser() {
    }

    public InetAddress parseAddress(String receivedString) throws UnknownHostException { // primeiro index IP
        InetAddress inetAddress = InetAddress.getByName(receivedString);
        return inetAddress;
    }

    public String parseUserName(String receivedString) {
        return receivedString;
    }

    //TODO: parse TPC messages according to ServerMessagesClass

    public synchronized void handleTCPMessage(String message) throws UnknownHostException {

        Pattern pattern = Pattern.compile("^(\\w+):(\\w+)$");
        Matcher matcher = pattern.matcher(message);

        if (matcher.find()) {
            parseAddress(matcher.group(1));
            parseUserName(matcher.group(2));
        }

    }

    //TODO: parse player actions through UDP messages

    public synchronized void handleUDPMessage(String message, InetAddress inetAddress) {

        Pattern pattern = Pattern.compile("^(\\w+):(\\w+)$");
        Matcher matcher = pattern.matcher(message);

        while (matcher.find()) {

            if (matcher.group(1).equals("MOVEMENT")) {

                if (matcher.group(2).equals("LEFT")) {
                    observer.convertAction("LEFT", inetAddress);

                }

                if (matcher.group(2).equals("RIGHT")) {
                    observer.convertAction("LEFT", inetAddress);

                }

                if (matcher.group(2).equals("UP")) {
                    observer.convertAction("LEFT", inetAddress);

                }

                if (matcher.group(2).equals("DOWN")) {
                    observer.convertAction("LEFT", inetAddress);

                }

            }

            if (matcher.group(1).equals("ACTION")) {

                if (matcher.group(2).equals("BOMB_DEPLOY")) {
                    observer.convertAction("LEFT", inetAddress);

                }

            }


        }

    }

    public void setObserver(Observable observer){
        this.observer = observer;
    }

}
