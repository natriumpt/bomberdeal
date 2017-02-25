package org.academiadecodigo.bootcamp.bomberdeal.server.Network;

import java.io.*;

/**
 * Created by codecadet on 2/22/17.
 */
public class FileEditor {

    public String Loader() throws IOException {
        BufferedReader inputStream = new BufferedReader(new FileReader("res/bomberman.txt"));
        String  totalLines = "";
        String thisLine = null;

        thisLine = inputStream.readLine();

        while (thisLine != null){

            totalLines += thisLine + "\r\n";

            thisLine = inputStream.readLine();
            
        }

        System.out.println(totalLines);

        //inputStream.close();
        return totalLines;
    }

    public void Writer(String string)throws IOException{
        BufferedWriter outputStream = new BufferedWriter(new FileWriter("test-res/cenas"));
        outputStream.write(string,0,string.length());
        outputStream.close();
    }

}
