package org.academiadecodigo.bootcamp.bomberdeal.client.menu;

import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.terminal.Terminal;

public class GameOver implements Screen {

    private com.googlecode.lanterna.screen.Screen screen;


    public void init(){

        screen = TerminalFacade.createScreen();
        screen.startScreen();

        String gameOver =
                "  /$$$$$$                                           /$$$$$$                                \n" +
                " /$$__  $$                                         /$$__  $$                               \n" +
                "| $$  \\__/  /$$$$$$  /$$$$$$/$$$$   /$$$$$$       | $$  \\ $$ /$$    /$$  /$$$$$$   /$$$$$$ \n" +
                "| $$ /$$$$ |____  $$| $$_  $$_  $$ /$$__  $$      | $$  | $$|  $$  /$$/ /$$__  $$ /$$__  $$\n" +
                "| $$|_  $$  /$$$$$$$| $$ \\ $$ \\ $$| $$$$$$$$      | $$  | $$ \\  $$/$$/ | $$$$$$$$| $$  \\__/\n" +
                "| $$  \\ $$ /$$__  $$| $$ | $$ | $$| $$_____/      | $$  | $$  \\  $$$/  | $$_____/| $$      \n" +
                "|  $$$$$$/|  $$$$$$$| $$ | $$ | $$|  $$$$$$$      |  $$$$$$/   \\  $/   |  $$$$$$$| $$      \n" +
                " \\______/  \\_______/|__/ |__/ |__/ \\_______/       \\______/     \\_/     \\_______/|__/      ";

        String cloud =
                "     _.-^^---....,,--       \n" +
                " _--                  --_  \n" +
                "<                        >)\n" +
                "|                         | \n" +
                " \\._                   _./  \n" +
                "    ```--. . , ; .--'''       \n" +
                "          | |   |             \n" +
                "       .-=||  | |=-.   \n" +
                "       `-=#$%&%$#=-'   \n" +
                "          | ;  :|     \n" +
                " _____.,-#%&$@%#&#~,._____";



        String[] gameOverTitle = gameOver.split("\n");
        int count = screen.getTerminalSize().getRows() / 6;
        for (String line : gameOverTitle) {
            System.out.println(line);
            screen.putString(3, count, line, Terminal.Color.YELLOW, Terminal.Color.BLACK);
            count++;
        }

        String[] cloudTitle = cloud.split("\n");
        int count1 = screen.getTerminalSize().getRows()/2;
        for (String line : cloudTitle) {
            System.out.println(line);
            screen.putString(screen.getTerminalSize().getColumns()/3, count1, line, Terminal.Color.WHITE, Terminal.Color.BLACK);
            count1++;
        }

        screen.refresh();


    }


}
