package org.academiadecodigo.bootcamp.Server;


import apple.laf.JRSUIConstants;
import org.academiadecodigo.bootcamp.bomberdeal.server.GameCore;
import org.academiadecodigo.bootcamp.bomberdeal.server.gamefield.Field;
import org.academiadecodigo.bootcamp.bomberdeal.server.gameobject.Bomb;
import org.academiadecodigo.bootcamp.bomberdeal.server.gameobject.Player;
import org.academiadecodigo.bootcamp.bomberdeal.server.gameobject.interfaces.Interactable;
import org.academiadecodigo.bootcamp.bomberdeal.server.helper.CollisionChecker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainTest {
    public static void main(String[] args) {

        List<Interactable> interactables;
        interactables=Collections.synchronizedList(new ArrayList<>());
        GameCore gameCore = new GameCore();

        Player player = new Player(2,3,gameCore.getCollisionChecker(), gameCore);
        interactables.add(player);
        System.out.println(interactables.size());
        System.out.println(interactables.get(0).getTileType());
        System.out.println("X: " + player.getX());
        player.move(Player.Direction.WEST);
        System.out.println(("X: " + player.getX()));
        System.out.println("Nr of Bombs: " + player.getNrOfBombs());
//        player.deploy();
        player.move(Player.Direction.WEST);
        System.out.println("X: " + player.getX() + " Y: " + player.getY());

//        player.getBomb(0).destroy();

        player.deploy();

    }
}
