package org.academiadecodigo.bootcamp.bomberdeal.server;

import org.academiadecodigo.bootcamp.bomberdeal.server.gamefield.Field;
import org.academiadecodigo.bootcamp.bomberdeal.server.gameobject.interfaces.Interactable;
import org.academiadecodigo.bootcamp.bomberdeal.server.gameobject.interfaces.Observable;
import org.academiadecodigo.bootcamp.bomberdeal.server.helper.CollisionChecker;
import org.academiadecodigo.bootcamp.bomberdeal.server.gameobject.Player;

import java.util.*;

public class GameCore implements Runnable, Observable {

    private Field field;
    private static List<Interactable> interactables;
    private static CollisionChecker collisionChecker;
    private Player player;

    public GameCore() {
        this.field = new Field();
        this.interactables = Collections.synchronizedList(new ArrayList<>());
        this.collisionChecker = new CollisionChecker(field, interactables);
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

    public static void main(String[] args) {
        interactables = Collections.synchronizedList(new ArrayList<>());
        GameCore gameCore = new GameCore();

        Player player = new Player(2, 3, collisionChecker, gameCore);
        Player rute = new Player(1, 1, collisionChecker, gameCore);
        gameCore.setPlayerToGame(player);
        gameCore.setPlayerToGame(rute);
        System.out.println("X: " + player.getX());
        player.move(Player.Direction.WEST);
        System.out.println(("X: " + player.getX()));
        System.out.println("Nr of Bombs: " + player.getNrOfBombs());
        player.move(Player.Direction.WEST);
        System.out.println("X: " + player.getX() + " Y: " + player.getY());

        gameCore.processFire();
        player.deploy();

    }
}


