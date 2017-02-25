package org.academiadecodigo.bootcamp.bomberdeal.client.grid;

import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.gui.Interactable;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.ScreenWriter;
import com.googlecode.lanterna.terminal.Terminal;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;
import org.academiadecodigo.bootcamp.bomberdeal.client.network.ServerMessages;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GridLanterna implements Grid {

    private int cols;
    private int rows;
    private static int maxRows;
    private Screen screen;
    private ScreenWriter screenWriter;
    private Position[][] positions;
    private String gridMap;

    public GridLanterna() {

        gridMap = "";
        maxRows = 30;

    }

    public void init(BufferedReader reader) {

        try {

            //BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
            StringBuilder gridMapBuilder = new StringBuilder(gridMap);

            String serverMessage = reader.readLine();
            System.out.println("reading map");

            while (!serverMessage.equals(ServerMessages.SERVER_MAP_LAYOUT_COMPLETE)) {

                gridMapBuilder.append(serverMessage + "\r\n");
                serverMessage = reader.readLine();
                System.out.println(serverMessage);

            }

            System.out.println("finished map");

            gridMap = gridMapBuilder.toString();
            System.out.println(gridMap);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void drawGrid() {

        String[] gridArray = gridMap.split("\n");

        for(String msg: gridArray) {
            System.out.println(msg);
            System.out.println("printing");
        }

        this.rows = Integer.valueOf(gridArray[gridArray.length - 2].split(",")[1]);
        this.cols = Integer.valueOf(gridArray[gridArray.length - 2].split(",")[0]);


        screen = TerminalFacade.createScreen();
        screen.getTerminal().getTerminalSize().setColumns(cols);
        screen.getTerminal().getTerminalSize().setRows(rows);

        positions = new Position[cols][rows];

        screenWriter = new ScreenWriter(screen);
        screenWriter.setBackgroundColor(Terminal.Color.BLACK);

        screen.startScreen();

        int posX;
        int posY;

        Pattern pattern = Pattern.compile("(\\d+),(\\d+),(\\w+),");

        for (int i = 0; i < gridArray.length - 2; i++) {

            Matcher matcher = pattern.matcher(gridArray[i]);

            while (matcher.find()) {
                //for (int j = 0; j < gridArray[i].length(); j++) {

                posX = Integer.valueOf(matcher.group(0));
                posY = Integer.valueOf(matcher.group(1));

                positions[posX][posY] = new Position(posY, posX, matcher.group(2));
                screen.putString(posX * 2, posY, matcher.group(2), TiletypeLanterna.getTileType(positions[posX][posY].tile).getTextColor(), TiletypeLanterna.getTileType(positions[posX][posY].tile).getColor());
                //}
            }

        }

        screen.refresh();

    }

    public void updateScreen() {

        for (int i = 0; i < positions.length; i++) {

            for (int j = 0; j < positions[i].length; j++) {
                screen.putString(positions[i][j].posX, positions[i][j].posY, positions[i][j].tile, TiletypeLanterna.getTileType(positions[i][j].tile).getTextColor(), TiletypeLanterna.getTileType(positions[i][j].tile).getColor());
            }

        }

        screen.refresh();
    }

    public void updatePositions(int x, int y, String type) {

        positions[x][y].tile = type;

    }

    public Screen getScreen() {
        return this.screen;
    }

    /*
    private void drawSquare(int posX, int posY, Terminal.Color color) {
        screenWriter.setBackgroundColor(color);
        screenWriter.drawString(posX * CURSOR_WIDTH, posY, "  ");
    }

    private void clearScreen() {
        screenWriter.setBackgroundColor(Terminal.Color.DEFAULT);
        screenWriter.fillScreen(' ');
    }*/

}
