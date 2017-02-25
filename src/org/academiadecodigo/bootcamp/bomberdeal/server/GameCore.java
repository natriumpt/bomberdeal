package org.academiadecodigo.bootcamp.bomberdeal.server;

import org.academiadecodigo.bootcamp.bomberdeal.server.gamefield.Field;
import org.academiadecodigo.bootcamp.bomberdeal.server.gameobject.Fire;
import org.academiadecodigo.bootcamp.bomberdeal.server.gameobject.interfaces.Collidable;
import org.academiadecodigo.bootcamp.bomberdeal.server.gameobject.interfaces.Destroyable;
import org.academiadecodigo.bootcamp.bomberdeal.server.gameobject.interfaces.Interactable;

import java.util.List;

import static com.sun.tools.classfile.AccessFlags.Kind.Field;

public class GameCore implements Runnable {

    protected List<Interactable> interactables;
    private Field field;

    public GameCore() {
        this.field = new Field();
    }

   public void addInteractabletoGame(Interactable interactable){
       interactables.add(interactable);
   }

    public void processFire() {

        for (Interactable fire : interactables) {
            if (fire instanceof Fire) {
                for (Interactable destroyable : interactables) {
                    if (destroyable instanceof Destroyable) {
                        destroyable.destroy();
                    }
                }
            }
        }

    }

    @Override
    public void run() {

        while (true) {

        }

    }


}
