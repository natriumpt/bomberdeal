package org.academiadecodigo.bootcamp.bomberdeal.client.grid;

import java.io.BufferedReader;
import java.io.InputStream;

/**
 * * * Created by codecadet on 2/24/17.
 * */

public interface Grid {

    void init(BufferedReader reader);

    void updateScreen();

    void updatePositions(int x, int y, String type);

    void drawGrid();

}

