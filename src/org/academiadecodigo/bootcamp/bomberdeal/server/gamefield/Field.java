package org.academiadecodigo.bootcamp.bomberdeal.server.gamefield;

import org.academiadecodigo.bootcamp.bomberdeal.server.Network.MapHandler;
import org.academiadecodigo.bootcamp.bomberdeal.server.helper.TileType;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Field {

    private int fieldWidth;
    private int fieldHeight;

    private String[][] field;

    public Field() {

        createEmptyField();
        populateField();

    }

    public String[][] getField() {
        //TODO: Provide client with populated map information
        return field;
    }

    /**
     * Creates the field array
     */
    private void createEmptyField() {

        String[] mapField = MapHandler.getMap().split("\\n");

        this.fieldWidth = Integer.valueOf(mapField[mapField.length - 1].split(";")[1]);
        this.fieldHeight = Integer.valueOf(mapField[mapField.length - 2].split(";")[0]);

        System.out.println(fieldWidth + 1);
        System.out.println(fieldHeight + 1);

        field = new String[fieldWidth + 1][fieldHeight + 1];

        int posX;
        int posY;

        String gridMap = "";
        StringBuilder builder = new StringBuilder(gridMap);

        Pattern pattern = Pattern.compile("^(\\d+);(\\d+);(\\w+)$");

        for (int i = 0; i < mapField.length; i++) {

                Matcher matcher = pattern.matcher(mapField[i]);

                while (matcher.find()) {

                    posY = Integer.valueOf(matcher.group(1));
                    posX = Integer.valueOf(matcher.group(2));

                    field[posY][posX] = matcher.group(3);
                    builder.append(matcher.group(3));

                    if(posX == 14) {
                        builder.append("\n");
                    }
                }

        }

        System.out.println(builder.toString());
    }

    /**
     * Populates the field with the pre-set map tiles
     */
    private void populateField() {

        String[] mapField = MapHandler.getMapFill().split("\\n");

        this.fieldWidth = Integer.valueOf(mapField[mapField.length - 1].split(";")[1]);
        this.fieldHeight = Integer.valueOf(mapField[mapField.length - 2].split(";")[0]);

        field = new String[fieldWidth + 1][fieldHeight + 1];

        int posX;
        int posY;

        Pattern pattern = Pattern.compile("^(\\d+);(\\d+);(\\w+)$");

        for (int i = 0; i < mapField.length; i++) {

                Matcher matcher = pattern.matcher(mapField[i]);

                while (matcher.find()) {

                    if(matcher.group(3).equals("EMPTY")) {
                        break;
                    }

                    posY = Integer.valueOf(matcher.group(1));
                    posX = Integer.valueOf(matcher.group(2));

                    field[posY][posX] = matcher.group(3);

                }

        }
    }

    /**
     * Returns true if the TileType in the coordinates is WALL
     * @param x
     * @param y
     * @return
     */
    public boolean isWall(int x, int y) {
        return field[x][y].equals(TileType.WALL.getSymbol());
    }

}
