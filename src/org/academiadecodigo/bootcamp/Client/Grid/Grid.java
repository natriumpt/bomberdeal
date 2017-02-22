package org.academiadecodigo.bootcamp.Client.Grid;

import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.ScreenWriter;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.*;

/**
 * Created by andre on 2/20/2017.
 */
public class Grid {

    private int cols;
    private int rows;
    private Screen screen;
    private ScreenWriter screenWriter;
    private Position[][] positions;
    private String gridMap;

    public Grid(InputStream stream) {

            gridMap = "";

        try {

            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
            StringBuilder gridMapBuilder = new StringBuilder(gridMap);

            gridMap = reader.readLine();

            while(gridMap != null) {
                gridMapBuilder.append(gridMap + "\n");
                gridMap = reader.readLine();
            }

            gridMap = gridMapBuilder.toString();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void init() {

        String[] gridArray = gridMap.split("\n");

        screen = TerminalFacade.createScreen();
        screen.getTerminal().getTerminalSize().setColumns(gridArray[0].length());
        screen.getTerminal().getTerminalSize().setRows(gridArray.length);

        positions = new Position[gridArray.length][gridArray[0].length()];

        screenWriter = new ScreenWriter(screen);
        screenWriter.setBackgroundColor(Terminal.Color.BLACK);

        screen.startScreen();

        this.cols = gridArray[0].length();
        this.rows = gridArray.length;

        for (int i = 0; i < gridArray.length; i++) {

            for (int j = 0; j < gridArray[i].length(); j++) {

                positions[i][j] = new Position(j, i, String.valueOf(gridArray[i].charAt(j)));
                positions[i][j].draw(screen);

            }

        }

    }

    public void refreshScreen() {

        for (int i = 0; i < positions.length; i++) {

            for (int j = 0; j < positions[i].length; j++) {
                positions[i][j].draw(screen);
            }

        }

        screen.refresh();
    }

    public void updatePosition(int x, int y, String type) {

        positions[x][y].tile = type;

    }

    public Screen getScreen() {
        return this.screen;
    }

}
