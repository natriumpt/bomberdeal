package org.academiadecodigo.bootcamp.bomberdeal.client.grid;

import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.ScreenWriter;
import com.googlecode.lanterna.terminal.Terminal;
import org.academiadecodigo.bootcamp.bomberdeal.client.network.ClientNetworkMessages;


import java.io.BufferedReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GridLanterna implements Grid {

    private int cols;
    private int rows;
    private static int maxRows;
    private final int colWidth = 4;
    private final int rowHeight = 2;
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

            StringBuilder gridMapBuilder = new StringBuilder(gridMap);

            String serverMessage = reader.readLine();


            while (!serverMessage.equals(ClientNetworkMessages.SERVER_MAP_LAYOUT_COMPLETE)) {

                gridMapBuilder.append(serverMessage + "\r\n");
                serverMessage = reader.readLine();

            }

            gridMap = gridMapBuilder.toString();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public synchronized void drawGrid() {

        String[] gridArray = gridMap.split("\n");

        this.rows = Integer.valueOf(gridArray[gridArray.length - 2].split(";")[1]);
        this.cols = Integer.valueOf(gridArray[gridArray.length - 2].split(";")[0]);

        screen = TerminalFacade.createScreen();
        screen.getTerminal().getTerminalSize().setColumns((rows * colWidth) + colWidth);
        screen.getTerminal().getTerminalSize().setRows((cols * rowHeight) + rowHeight);

        positions = new Position[cols + 1][rows + 1];

        screenWriter = new ScreenWriter(screen);
        screenWriter.setBackgroundColor(Terminal.Color.BLACK);

        screen.startScreen();

        int posX;
        int posY;

        Pattern pattern = Pattern.compile("^(\\d+);(\\d+);(\\w+)$");

        for (int i = 0; i < gridArray.length; i++) {

            Matcher matcher = pattern.matcher(gridArray[i]);

            while (matcher.find()) {

                posY = Integer.valueOf(matcher.group(1));
                posX = Integer.valueOf(matcher.group(2));

                positions[posY][posX] = new Position(posX, posY, matcher.group(3));

                screen.putString(positions[posY][posX].posX * colWidth, positions[posY][posX].posY * rowHeight,
                        " " + positions[posY][posX].tile + "  ",
                        TiletypeLanterna.getTileType(positions[posY][posX].tile).getTextColor(),
                        TiletypeLanterna.getTileType(positions[posY][posX].tile).getColor());

                screen.putString(positions[posY][posX].posX * colWidth, (positions[posY][posX].posY * rowHeight) + 1,
                        " " + positions[posY][posX].tile + "  ",
                        TiletypeLanterna.getTileType(positions[posY][posX].tile).getTextColor(),
                        TiletypeLanterna.getTileType(positions[posY][posX].tile).getColor());

            }

        }

        screen.refresh();

    }

    public synchronized void updateScreen() {

        for (int i = 0; i < positions.length; i++) {

            for (int j = 0; j < positions[i].length; j++) {

                screen.putString(positions[i][j].posX * colWidth, positions[i][j].posY * rowHeight,
                        " " + positions[i][j].tile + "  ",
                        TiletypeLanterna.getTileType(positions[i][j].tile).getTextColor(),
                        TiletypeLanterna.getTileType(positions[i][j].tile).getColor());

                screen.putString(positions[i][j].posX * colWidth, (positions[i][j].posY * rowHeight) + 1,
                        " " + positions[i][j].tile + "  ",
                        TiletypeLanterna.getTileType(positions[i][j].tile).getTextColor(),
                        TiletypeLanterna.getTileType(positions[i][j].tile).getColor());

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
