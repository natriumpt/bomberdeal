package com.github.natriumpt.bomberdeal.Server;

import java.io.*;

/**
 * Created by codecadet on 2/22/17.
 */
public class FileEditor {

    public String Loader() throws IOException {
        BufferedReader inputStream = new BufferedReader(new FileReader("test-res/cenas"));
        String  totalLines = "";
        String thisLine = null;
        while ((thisLine = inputStream.readLine())!=null){
            totalLines = totalLines.concat(thisLine);
        }
        inputStream.close();
        return totalLines;
    }

    public void Writer(String string)throws IOException{
        BufferedWriter outputStream = new BufferedWriter(new FileWriter("test-res/cenas"));
        outputStream.write(string,0,string.length());
        outputStream.close();
    }

}
