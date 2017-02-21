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
    private String temp = "11111111111111111111\n" +
            "10002000000000000001\n" +
            "10112101011010101101\n" +
            "10000000000000000001\n" +
            "10110101011010101101\n" +
            "10220000000000000001\n" +
            "10112101011010101101\n" +
            "12202000000000000001\n" +
            "10112101011010101101\n" +
            "10020220000000000001\n" +
            "11111111111111111111\n";

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

            String[] gridArray = gridMapBuilder.toString().split("\n");

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
                    screen.putString(positions[i][j].posX, positions[i][j].posY, positions[i][j].tile,
                            Tiletype.getTileType(positions[i][j].tile).getColor(),
                            Tiletype.getTileType(positions[i][j].tile).getColor());
                    screen.refresh();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
