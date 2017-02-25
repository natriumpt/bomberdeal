package org.academiadecodigo.bootcamp.bomberdeal.server.helper;

import org.academiadecodigo.bootcamp.bomberdeal.client.grid.Grid;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileHandler {

    public static void loadMap(String filename, Grid grid) {

        BufferedReader bufferedReader = null;

        try {

            bufferedReader = new BufferedReader(new FileReader(filename));

            String currentLine = bufferedReader.readLine();

            Pattern sizePattern = Pattern.compile("SIZE=(\\d+):(\\d+)");
            Matcher sizeMatcher = sizePattern.matcher(currentLine);

            while (sizeMatcher.find()) {
                int gridW = Integer.parseInt(sizeMatcher.group(1));
                int gridH = Integer.parseInt(sizeMatcher.group(2));
                System.out.println(gridW);
                System.out.println(gridH);
                grid.loadNewGrid(gridW, gridH);
                ((Rebuildable) grid.getRepresentation()).rebuild(gridW, gridH);
            }

            currentLine = bufferedReader.readLine();

            Pattern valuePattern = Pattern.compile("(\\d+);(\\d+);([A-Z]+)");

            while (currentLine != null) {

                Matcher valueMatcher = valuePattern.matcher(currentLine);

                while (valueMatcher.find()) {
                    int cellX = Integer.parseInt(valueMatcher.group(1));
                    int cellY = Integer.parseInt(valueMatcher.group(2));
                    CellType cellType = (CellType.valueOf(valueMatcher.group(3)));
                    grid.getCell()[cellX][cellY].setCellType(cellType);
                }

                currentLine = bufferedReader.readLine();

            }


        } catch (IOException e) {

            System.err.println(e.getMessage());

        } finally {

            try {

                if (bufferedReader != null) bufferedReader.close();

            } catch (IOException e) {

                System.err.println(e.getMessage());

            }

        }

    }


}
