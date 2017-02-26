package org.academiadecodigo.bootcamp.bomberdeal.server;

import org.academiadecodigo.bootcamp.bomberdeal.server.Network.PlayerHandler;
import org.academiadecodigo.bootcamp.bomberdeal.server.gamefield.Field;
import org.academiadecodigo.bootcamp.bomberdeal.server.gameobject.Player;
import org.academiadecodigo.bootcamp.bomberdeal.server.gameobject.interfaces.Interactable;
import org.academiadecodigo.bootcamp.bomberdeal.server.gameobject.interfaces.Observable;
import org.academiadecodigo.bootcamp.bomberdeal.server.helper.CollisionChecker;
import org.academiadecodigo.bootcamp.bomberdeal.server.gameobject.Player;


import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameCore implements Runnable, Observable {

    private Field field;
    private static List<Interactable> interactables;
    private static CollisionChecker collisionChecker;
    private Player player;

    public GameCore(int nrOfPlayers, ArrayList<PlayerHandler> playerHandlers) {
        this.field = new Field();
        this.interactables = Collections.synchronizedList(new ArrayList<>());
        this.collisionChecker = new CollisionChecker(field, interactables);
        for (int i = 0; i < nrOfPlayers; i++) {
            interactables.add(new Player(0, 0, collisionChecker, this, playerHandlers.get(i).getInetAddress()));
        }
    }

    public void processFire() {
        collisionChecker.processFire();
    }

    @Override
    public void run() {

        while (true) {


        }

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

    public CollisionChecker getCollisionChecker() {
        return collisionChecker;
    }

    public void setPlayerToGame(Player player) {
        this.player = player;
        interactables.add(player);
    }

    @Override
    public void convertAction(String action, InetAddress inetAddress) {
        for (Interactable interactable : interactables) {
            if (interactable instanceof Player && ((Player) interactable).getInetAddress().equals(inetAddress)) {
                if (action.equals("BOMB_DEPLOY")) {
                    ((Player) interactable).deploy();
                } else {

                    ((Player) interactable).move(action);
                }
            }
        }

    }

    public String convertAllInteractablesToString(Interactable gameObject) {
        String sendToServer = "";
        for (int i = 0; i < interactables.size(); i++) {
            sendToServer += interactableToString(i);
        }
        return sendToServer;
    }


    public String interactableToString(int i) {
        return "" + interactables.get(i).getX() + ":" + interactables.get(i).getY() + ":" + interactables.get(i).getTileType() + ";\n";
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


