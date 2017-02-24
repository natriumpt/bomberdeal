package org.academiadecodigo.bootcamp.Server.gridserver;

import org.academiadecodigo.bootcamp.Server.Observer;
import org.academiadecodigo.bootcamp.Server.gamelogic.Bomb;
import org.academiadecodigo.bootcamp.Server.gamelogic.CollisionChecker;
import org.academiadecodigo.bootcamp.Server.gamelogic.Player;

/**
 * Created by codecadet on 2/23/17.
 */
public class GridServer extends Observer {
    private PositionServer[][] positionServers;
    private CollisionChecker collisionChecker;
    private Player[] players;
    private Bomb[] bombs;

   // falta aqui o metodo que adiciona o grid server como observador às mudancas de estado de todas as bombas. No construtor.

    @Override
    public void update(Bomb bomb) {
        explodeArea(bomb.getRange(), bomb);
    }
    public void explodeArea(int range, Bomb bomb){
        for(int i = 0; i > - range; i--){ //VERTICAL BLAST
            PositionServer positionV1 = positionServers[bomb.getPosition().getPosX()][bomb.getPosition().getPosY() + i];
            blastDamage(positionV1);
            PositionServer positionV2 = positionServers[bomb.getPosition().getPosX()][bomb.getPosition().getPosY() - i];
            blastDamage(positionV2);
        }

        for(int i = 0; i> -range; i--){ //HORIZONTAL BLAST
            PositionServer positionH1 = positionServers[bomb.getPosition().getPosX() + i][bomb.getPosition().getPosY()];
            blastDamage(positionH1);
            PositionServer positionH2 = positionServers[bomb.getPosition().getPosX() - i][bomb.getPosition().getPosY()];
            blastDamage(positionH2);
        }

    }

    private void blastDamage(PositionServer position) {
        String type = collisionChecker.checkTileType(position);
        switch (type){
            case "P":
                position.setTileType("F");
                //timertask
                getPlayerFromPos(position).die(); //player morre
                position.setTileType("0");
                break;
            case "C":
                position.setTileType("F");
                //metodo da box para criar powerups
                //um timetask
                position.setTileType("0"); // passa a FLOOR
                break;
            case "0":
                position.setTileType("F");
                //um timetask
                position.setTileType("0");
                break;
            case "B":
                position.setTileType("F");
                getBombFromPos(position).explode(); //bomba explode
                //um timertask
                position.setTileType("0");
        }
    }

    private Player getPlayerFromPos(PositionServer position){
        Player deathPlayer = null;
       for(Player player: players){
           if(player.getPosition().getPosX() == position.getPosX() || player.getPosition().getPosY() == position.getPosY()){
               deathPlayer = player;
           }
       }
       return deathPlayer;
    }

    private Bomb getBombFromPos(PositionServer position){
        Bomb bombToExplode = null;
        for(Bomb bomb: bombs){
            if(bomb.getPosition().getPosX() == position.getPosX() || bomb.getPosition().getPosY() == position.getPosY()){
                bombToExplode = bomb;
            }
        }
        return bombToExplode;
    }
}
