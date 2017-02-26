package org.academiadecodigo.bootcamp.bomberdeal.server.gamefield;

import org.academiadecodigo.bootcamp.bomberdeal.server.Network.MapHandler;
import org.academiadecodigo.bootcamp.bomberdeal.server.Network.ServerNetworkMessages;
import org.academiadecodigo.bootcamp.bomberdeal.server.gameobject.Crate;
import org.academiadecodigo.bootcamp.bomberdeal.server.gameobject.interfaces.Observable;
import org.academiadecodigo.bootcamp.bomberdeal.server.helper.TileType;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Field {

    private int fieldWidth;
    private int fieldHeight;
    private Observable observer;

    private String[][] field;

    public Field(Observable gameCore) {

        this.observer= gameCore;
        createEmptyField();
        populateField();

    }

    public String getField() {

        String populatedField = "";
        StringBuilder builder = new StringBuilder(populatedField);

        String x;
        String y;

        for (int i = 0; i < field.length; i++) {

            for (int j = 0; j < field[i].length; j++) {

                x = String.valueOf(i);
                y = String.valueOf(j);

                builder.append(x + ServerNetworkMessages.COORDS_SPACE + y + ServerNetworkMessages.COORDS_SPACE +
                        field[i][j]);

                builder.append("\n");
            }

        }

        populatedField = builder.toString();

        return populatedField;
    }

    /**
     * Creates the field array
     */
    private void createEmptyField() {

        String[] mapField = MapHandler.getMap().split("\\n");

        this.fieldWidth = Integer.valueOf(mapField[mapField.length - 1].split(";")[1]);
        this.fieldHeight = Integer.valueOf(mapField[mapField.length - 2].split(";")[0]);

        field = new String[fieldWidth + 1][fieldHeight + 1];

        int posX;
        int posY;

        Pattern pattern = Pattern.compile("^(\\w+);(\\w+);(\\w+)");

        for (int i = 0; i < mapField.length; i++) {

            Matcher matcher = pattern.matcher(mapField[i]);

            while (matcher.find()) {

                posY = Integer.valueOf(matcher.group(1));
                posX = Integer.valueOf(matcher.group(2));

                field[posY][posX] = matcher.group(3);

            }

        }
    }

    /**
     * Populates the field with the pre-set map tiles
     */
    private void populateField() {

        String[] mapField = MapHandler.getMapFill().split("\\n");

        this.fieldWidth = Integer.valueOf(mapField[mapField.length - 1].split(";")[1]);
        this.fieldHeight = Integer.valueOf(mapField[mapField.length - 2].split(";")[0]);

        int posX;
        int posY;

        Pattern pattern = Pattern.compile("^(\\d+);(\\d+);(\\w+)$");

        for (int i = 0; i < mapField.length; i++) {

            Matcher matcher = pattern.matcher(mapField[i]);

            while (matcher.find()) {

                if (matcher.group(3).equals("EMPTY")) {
                    break;
                }

                posY = Integer.valueOf(matcher.group(1));
                posX = Integer.valueOf(matcher.group(2));

                if(matcher.group(2).equals("C")){
                   Crate crate = new Crate(posX, posY);
                    observer.update(crate);
                }
                field[posY][posX] = matcher.group(3);



            }

        }
    }

    public String getAvailableSpawns() {

        String spawns = "";

        for(int i = 0; i < field.length; i++) {

            for(int j = 0; j < field[i].length; j++) {

                if(field[i][j].equals("S")) {

                    spawns += i + ServerNetworkMessages.COORDS_SPACE + j +
                            ServerNetworkMessages.COORDS_SPACE + field[i][j] + "\n";

                }

            }

        }

        return spawns;

    }

    /**
     * Returns true if the TileType in the coordinates is WALL
     *
     * @param x
     * @param y
     * @return
     */
    public boolean isWall(int x, int y) {
        return field[x][y].equals(TileType.WALL.getSymbol());
    }

}
