package org.academiadecodigo.bootcamp.bomberdeal.server.gameobject;

import org.academiadecodigo.bootcamp.bomberdeal.server.gamefield.Field;
import org.academiadecodigo.bootcamp.bomberdeal.server.gameobject.interfaces.Collidable;
import org.academiadecodigo.bootcamp.bomberdeal.server.gameobject.interfaces.DestroyableByFire;
import org.academiadecodigo.bootcamp.bomberdeal.server.gameobject.interfaces.Interactable;
import org.academiadecodigo.bootcamp.bomberdeal.server.gameobject.interfaces.Observable;
import org.academiadecodigo.bootcamp.bomberdeal.server.helper.CollisionChecker;
import org.academiadecodigo.bootcamp.bomberdeal.server.helper.TileType;

import java.util.Timer;
import java.util.TimerTask;

public class Bomb implements Interactable, DestroyableByFire, Collidable {


    private Observable observer;
    private int x;
    private int y;
    private String tileType;
    private int range;
    private boolean isOnField;
    private CollisionChecker collisionChecker;
    private final int BOMB_TIMER = 3000; // in ms
    private Field field;

   public Bomb(int x, int y, CollisionChecker collisionChecker) {
      this.x = x;
      this.y = y;
      this.collisionChecker = collisionChecker;
      tileType = TileType.BOMB.getSymbol();
      range = 3;

   }


   public void explode(int x, int y) {

      this.x = x;
      this.y = y;
      observer.update(this);
      isOnField = true;
      System.out.println(x + " : " + y);
      setTimer(BOMB_TIMER);

      observer.update(this); //to remove bomb from interactables list
   }

   public boolean isOnField() {
      return isOnField;
   }

   private void setTimer(int delay) {
      new Timer().schedule(new TimerTask() {
         @Override
         public void run() {
            destroy();
         }
      }, delay);
   }

   @Override
   public void destroy() {
      System.out.println("inicio do fire");

      isOnField = false;
      observer.update(this);

      for (int i = 1; i <= range; i++) {

        System.out.println("inicio do fire");

         if (!collisionChecker.checkWall(x + i, y)) {

            Fire fireRight = new Fire(x + i, y);
            updateObserver(fireRight);
            collisionChecker.processFire(x + i, y);
         }

         if ((x - i) >= 0 && !collisionChecker.checkWall(x - i, y)) {

            Fire fireLeft = new Fire(x - i, y);
            updateObserver(fireLeft);
            collisionChecker.processFire(x - i, y);
         }

         if (!collisionChecker.checkWall(x, y + i)) {

            Fire fireDown = new Fire(x, y + i);
            updateObserver(fireDown);
            collisionChecker.processFire(x, y + i);
         }

         if ((y - i) >= 0 && !collisionChecker.checkWall(x, y - i)) {

            Fire fireUp = new Fire(x, y - 1);
            updateObserver(fireUp);
            collisionChecker.processFire(x, y - i);
         }
      }

   }

   @Override
   public void setField(Field field) {
        field.getFieldPositions()[x][y] = "F";
        isOnField = false;
    }


   @Override
   public int getX() {
      return x;
   }

   @Override
   public int getY() {
      return y;
   }

//    @Override
//    public void setField(Field field) {
//        this.field = field;
//    }

   @Override
   public String getTileType() {
      return tileType;
   }

   private void updateObserver(Fire fire) {

      fire.attach(observer);
      observer.update(fire);

   }

   public void attach(Observable observer) {
      this.observer = observer;
   }

   public void setRange() {
      range++;
   }
}


