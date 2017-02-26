package org.academiadecodigo.bootcamp.bomberdeal.server;

import org.academiadecodigo.bootcamp.bomberdeal.client.network.ClientNetworkMessages;
import org.academiadecodigo.bootcamp.bomberdeal.server.Network.MapHandler;
import org.academiadecodigo.bootcamp.bomberdeal.server.Network.PlayerHandler;
import org.academiadecodigo.bootcamp.bomberdeal.server.gamefield.Field;
import org.academiadecodigo.bootcamp.bomberdeal.server.gameobject.interfaces.Observable;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by andre on 2/26/2017.
 */
public class ServerGame implements Runnable {

    private ArrayList<PlayerHandler> players;
    private Field field;
    private GameCore gameCore;


    public ServerGame() {

        players = new ArrayList<>();
        field = new Field();

    }

    //TODO: method that will wait until 2 or more players are connected with a timer

    private void waitForPlayers() {

        while(players.size()<2) {

            try {

                Thread.sleep(1000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    //TODO: start game, start Field

    public void startGame() {
        gameCore = new GameCore(players.size(), players);



    }

    //TODO: send players map layout on connection and waiting timers

    private void initPlayer(PlayerHandler player) {

        player.sendTCP(ClientNetworkMessages.SERVER_MAP_SENDING_LAYOUT);
        player.sendTCP(MapHandler.getMap());
        player.sendTCP(ClientNetworkMessages.SERVER_MAP_LAYOUT_COMPLETE);

    }

    public void addPlayer(PlayerHandler player) {

        synchronized (players) {
            players.add(player);
        }

        initPlayer(player);

    }

    //TODO: implement broadcastField method

    public void broadcastField() {

        synchronized (players) {

            if(field != null) {
                for (PlayerHandler player : players) {
                    player.sendUDP(field.getField());
                }
            }

        }
    }

    @Override
    public void run() {

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                broadcastField();
            }
        }, 0, 17);

    }

    public Observable getGameCore(){
        return gameCore;
    }
}
