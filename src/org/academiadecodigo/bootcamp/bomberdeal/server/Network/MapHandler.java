package org.academiadecodigo.bootcamp.bomberdeal.server.Network;

import java.io.*;

/**
 * Created by codecadet on 2/22/17.
 */
public final class MapHandler {

    public static String getMap() {

        String totalLines = "";

        try {

            BufferedReader inputStream = new BufferedReader(new FileReader("res/bombermap.txt"));
            totalLines = "";
            String thisLine = null;

            thisLine = inputStream.readLine();

            while (thisLine != null) {

                totalLines += thisLine + "\r\n";

                thisLine = inputStream.readLine();

            }

            inputStream.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        totalLines.trim();

        return totalLines;
    }

}
