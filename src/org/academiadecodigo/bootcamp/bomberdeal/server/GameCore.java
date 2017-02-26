package org.academiadecodigo.bootcamp.bomberdeal.server;

import org.academiadecodigo.bootcamp.bomberdeal.server.Network.PlayerHandler;
import org.academiadecodigo.bootcamp.bomberdeal.server.gamefield.Field;
import org.academiadecodigo.bootcamp.bomberdeal.server.gameobject.Player;
import org.academiadecodigo.bootcamp.bomberdeal.server.gameobject.interfaces.Interactable;
import org.academiadecodigo.bootcamp.bomberdeal.server.gameobject.interfaces.Observable;
import org.academiadecodigo.bootcamp.bomberdeal.server.helper.CollisionChecker;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameCore implements Runnable, Observable {

    private Field field;
    private List<Interactable> interactables;
    private CollisionChecker collisionChecker;

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

        if (interactables.contains(gameObject)) {
            interactables.remove(gameObject);
        } else {
            interactables.add(gameObject);
        }
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
