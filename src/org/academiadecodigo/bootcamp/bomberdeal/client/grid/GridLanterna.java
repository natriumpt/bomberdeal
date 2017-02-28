package org.academiadecodigo.bootcamp.bomberdeal.client.grid;

import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.ScreenWriter;
import com.googlecode.lanterna.terminal.Terminal;
import org.academiadecodigo.bootcamp.bomberdeal.client.lanterna.TileTypeLanterna;
import org.academiadecodigo.bootcamp.bomberdeal.client.network.ClientNetworkMessages;


import java.io.BufferedReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GridLanterna implements Grid {

    private Position[][] positions;
    private String gridMap;

    public GridLanterna() {

        gridMap = "";

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

    public synchronized void updatePositions(int x, int y, TileType type) {
        positions[x][y].setType(type);
    }

    public Screen getScreen() {
        return this.screen;
    }

}
