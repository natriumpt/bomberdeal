package org.academiadecodigo.bootcamp.gamelogic;


/**
 * Created by codecadet on 2/22/17.
 */
public class PositionServer {
    private int posX;
    private int posY;
    private String tileType;
    private boolean visible;

    public PositionServer(int x, int y, String tileType) {
        posX = x;
        posY = y;

        this.tileType = tileType;
    }

    public String getTileType(){
        return tileType;
    }

    public void setTileType(String tileType){
        this.tileType = tileType;
    }

    public boolean isCollidable(){
        if((TiletypeServer.getTileType(this.tileType)== TiletypeServer.BOMB && isVisible())||
                TiletypeServer.getTileType(this.tileType) == TiletypeServer.WALL ||
                (TiletypeServer.getTileType(this.tileType) == TiletypeServer.BOX && isVisible()) ||
                TiletypeServer.getTileType(this.tileType) == TiletypeServer.PLAYER && isVisible()){
            return true;
        }
        return false;
    }

    public int getPosX(){
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public int setPosX(int nrCol) {
        return posX = posX + nrCol;
    }

    public int setPosY(int nrRow) {
        return posY = posY + nrRow;
    }

    public boolean isVisible(){
        return visible;
    }

    public void setVisible(){
        visible = !visible;
    }
}
