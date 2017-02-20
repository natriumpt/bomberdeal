package org.academiadecodigo.bootcamp;

import java.io.File;

/**
 * Created by andre on 2/20/2017.
 */
public class Main {

    public static void main(String[] args) {

        File file = new File("\\testing\\cenas.txt");

        System.out.println(file.exists());

    }

}
