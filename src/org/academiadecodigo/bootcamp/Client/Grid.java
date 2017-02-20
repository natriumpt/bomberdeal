package org.academiadecodigo.bootcamp.Client;

import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.ScreenWriter;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.*;
import java.lang.reflect.Field;

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

    public Grid(FileReader stream) {

        try {
            BufferedReader reader = new BufferedReader(stream);

            gridMap = reader.readLine();

            StringBuilder gridMapBuilder = new StringBuilder(gridMap);

            String message = reader.readLine();

            while(message != null) {
                System.out.println(message);
                gridMapBuilder.append(message + "\n");
                message = reader.readLine();
            }

            String[] gridArray = gridMapBuilder.toString().split("\n");

            System.out.println(gridArray.length);
            System.out.println(gridArray[0].length());

            screen = TerminalFacade.createScreen();
            screen.getTerminal().getTerminalSize().setColumns(gridArray[0].length());
            screen.getTerminal().getTerminalSize().setRows(gridArray.length);

            screenWriter = new ScreenWriter(screen);
            screenWriter.setBackgroundColor(Terminal.Color.BLACK);

            screen.startScreen();

            this.cols = gridArray[0].length();
            this.rows = gridArray.length;

            for (int i = 0; i < gridArray.length; i++) {
                for (int j = 0; j < gridArray[i].length(); j++) {
                    positions[j][i] = new Position(j, i, Tiletype.getTileType(String.valueOf(gridArray[i].charAt(i))));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
