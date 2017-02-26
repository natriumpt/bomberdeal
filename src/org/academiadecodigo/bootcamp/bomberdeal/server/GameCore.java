package org.academiadecodigo.bootcamp.bomberdeal.server;

import org.academiadecodigo.bootcamp.bomberdeal.client.network.ClientNetworkMessages;
import org.academiadecodigo.bootcamp.bomberdeal.server.Network.MapHandler;
import org.academiadecodigo.bootcamp.bomberdeal.server.Network.PlayerHandler;
import org.academiadecodigo.bootcamp.bomberdeal.server.Network.ServerNetworkMessages;
import org.academiadecodigo.bootcamp.bomberdeal.server.gamefield.Field;
import org.academiadecodigo.bootcamp.bomberdeal.server.gameobject.Player;
import org.academiadecodigo.bootcamp.bomberdeal.server.gameobject.interfaces.Interactable;
import org.academiadecodigo.bootcamp.bomberdeal.server.gameobject.interfaces.Observable;
import org.academiadecodigo.bootcamp.bomberdeal.server.helper.CollisionChecker;

import java.util.*;

public class GameCore implements Observable {

    private Field field;
    private ArrayList<PlayerHandler> playerHandlers;
    private List<Interactable> interactables;
    private CollisionChecker collisionChecker;
    private String[] spawnPoints;

    public GameCore() {

        this.field = new Field();
        this.interactables = Collections.synchronizedList(new ArrayList<>());
        this.collisionChecker = new CollisionChecker(field, interactables);

        playerHandlers = new ArrayList<>();
        spawnPoints = new String[4];

        setSpawnPoints(field.getAvailableSpawns());

    }

    public void addPlayer(PlayerHandler playerHandler) {

        interactables.add(playerHandler.getPlayer());

        synchronized (playerHandlers) {
            playerHandlers.add(playerHandler);
        }

        initPlayer(playerHandler);

    }

    public void setSpawnPoints(String spawnPoints) {

        String[] spawns = spawnPoints.split("\n");

        for(int i = 0; i < spawns.length; i++) {

            this.spawnPoints[i] = spawns[i];

        }
    }

    public String getSpawn() {

        String spawnCoords = "";

        for(String spawn: spawnPoints) {

            if (spawn != null) {
                spawnCoords = spawn;
                spawn = null;
            }

        }

        return spawnCoords;
    }

    @Override
    public void update(Interactable gameObject) {

        System.out.println("inicio update " + gameObject.getTileType());
        System.out.println(interactables.contains(gameObject));

        if (interactables.contains(gameObject)) {

            System.out.println("contains");
            interactables.remove(gameObject);

        } else {

            System.out.println("not contains");
            interactables.add(gameObject);

        }
    }

    public String convertAllInteractablesToString() {

        String interactablesCoords = "";

        for (int i = 0; i < interactables.size(); i++) {

            interactablesCoords += interactableToString(i);

        }

        return interactablesCoords;
    }

    public String interactableToString(int i) {

        return interactables.get(i).getX() + ServerNetworkMessages.COORDS_SPACE + interactables.get(i).getY() +
                ServerNetworkMessages.COORDS_SPACE + interactables.get(i).getTileType() + "\n";

    }

    //TODO: method that will wait until 2 or more playerHandlers are connected with a timer

    private void waitForPlayers() {

        /*while(playerHandlers.size()<2) {

            try {

                Thread.sleep(1000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                startGame();
            }
        }, 0, 30 * 1000);*/

    }

    //TODO: start game, start Field

    //TODO: send playerHandlers map layout on connection and waiting timers

    private void initPlayer(PlayerHandler player) {

        player.sendTCP(ClientNetworkMessages.SERVER_MAP_SENDING_LAYOUT);
        player.sendTCP(MapHandler.getMap());
        player.sendTCP(ClientNetworkMessages.SERVER_MAP_LAYOUT_COMPLETE);

    }

    public void broadcastInteractables() {

        synchronized (playerHandlers) {

            if (field != null) {

                for (PlayerHandler player : playerHandlers) {

                    player.sendUDP(field.getField());
                    player.sendUDP(convertAllInteractablesToString());

                    if(player.getPlayer().isAlive()) {
                        player.sendUDP(player.getPlayer().getPosition());
                    }

                }

            }
        }
    }

    public void startGame() {

        waitForPlayers();

        new Timer().scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {
                broadcastInteractables();
            }
        }, 0, 17);

    }

    public CollisionChecker getCollisionChecker() {
        return collisionChecker;
    }

}
//    public static void main(String[] args) {
//        interactables = Collections.synchronizedList(new ArrayList<>());
//        GameCore gameCore = new GameCore();
//
//        Player player = new Player(2, 3, collisionChecker, gameCore);
//        Player rute = new Player(1, 1, collisionChecker, gameCore);
//        gameCore.setPlayerToGame(player);
//        gameCore.setPlayerToGame(rute);
//        System.out.println("X: " + player.getX());
//        player.move(Player.Direction.WEST);
//        System.out.println(("X: " + player.getX()));
//        System.out.println("Nr of Bombs: " + player.getNrOfBombs());
//        player.move(Player.Direction.WEST);
//        System.out.println("X: " + player.getX() + " Y: " + player.getY());
//
//        gameCore.processFire();
//        player.deploy();
//
//    }
//}


