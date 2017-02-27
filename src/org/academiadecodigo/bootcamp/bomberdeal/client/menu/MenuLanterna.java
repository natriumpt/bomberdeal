package org.academiadecodigo.bootcamp.bomberdeal.client.menu;

import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.gui.*;
import com.googlecode.lanterna.gui.component.*;
import com.googlecode.lanterna.gui.dialog.MessageBox;
import com.googlecode.lanterna.gui.listener.ComponentListener;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.Terminal;

import java.util.Timer;
import java.util.TimerTask;

public class MenuLanterna implements SplashScreen, Runnable {

    private Screen screen;
    private String username;
    private String hostname;
    private boolean phaseOver;

    public String getUsername() {
        return username;
    }

    public String getHostname() {
        return hostname;
    }

    public void init() {

    }

    @Override
    public void run() {
        screen = TerminalFacade.createScreen();
        screen.startScreen();
        String title =
                        "/$$$$$$$                           /$$                           /$$      /$$\n" +
                        "| $$__  $$                        | $$                          | $$$    /$$$\n" +
                        "| $$  \\ $$  /$$$$$$  /$$$$$$/$$$$ | $$$$$$$   /$$$$$$   /$$$$$$ | $$$$  /$$$$  /$$$$$$  /$$$$$$$\n" +
                        "| $$$$$$$  /$$__  $$| $$_  $$_  $$| $$__  $$ /$$__  $$ /$$__  $$| $$ $$/$$ $$ |____  $$| $$__  $$\n" +
                        "| $$__  $$| $$  \\ $$| $$ \\ $$ \\ $$| $$  \\ $$| $$$$$$$$| $$  \\__/| $$  $$$| $$  /$$$$$$$| $$  \\ $$\n" +
                        "| $$  \\ $$| $$  | $$| $$ | $$ | $$| $$  | $$| $$_____/| $$      | $$\\  $ | $$ /$$__  $$| $$  | $$\n" +
                        "| $$$$$$$/|  $$$$$$/| $$ | $$ | $$| $$$$$$$/|  $$$$$$$| $$      | $$ \\/  | $$|  $$$$$$$| $$  | $$\n" +
                        "|_______/  \\______/ |__/ |__/ |__/|_______/  \\_______/|__/      |__/     |__/ \\_______/|__/  |__/";

        String bomb =
                        "     ,--.!,\n" +
                        "  __/   -*-\n" +
                        ",MMMM.  '|`\n" +
                        "MMMMMM     \n" +
                        "`MMMM' ";

        for(int i = 0; i<screen.getTerminalSize().getRows();i++){
            for (int j = 0; j<screen.getTerminalSize().getColumns();j++){
                screen.putString(j,0," ", Terminal.Color.YELLOW, Terminal.Color.YELLOW);
                screen.putString(0,i," ", Terminal.Color.YELLOW, Terminal.Color.YELLOW);
                screen.putString(j,screen.getTerminalSize().getRows()-1," ", Terminal.Color.YELLOW, Terminal.Color.YELLOW);
                screen.putString(screen.getTerminalSize().getColumns()-1,i," ", Terminal.Color.YELLOW, Terminal.Color.YELLOW);
            }
        }


        String[] titleASCII = title.split("\n");
        int count = screen.getTerminalSize().getRows() / 3;
        for (String line : titleASCII) {
            System.out.println(line);
            screen.putString(1, count, line, Terminal.Color.YELLOW, Terminal.Color.BLACK);
            count++;
        }

        int count1 = screen.getTerminalSize().getRows() / 7;
        String[] bombASCII = bomb.split("\n");
        for (String line : bombASCII) {
            System.out.println(line);
            screen.putString(screen.getTerminalSize().getColumns()/5, count1, line, Terminal.Color.WHITE, Terminal.Color.BLACK);
            count1++;
        }

        int count2 = screen.getTerminalSize().getRows() - 8;
        for (String line : bombASCII) {
            System.out.println(line);
            screen.putString(screen.getTerminalSize().getColumns()/2, count2, line, Terminal.Color.GREEN, Terminal.Color.BLACK);
            count2++;
        }

        int count3 = screen.getTerminalSize().getRows() / 9;
        for (String line : bombASCII) {
            System.out.println(line);
            screen.putString(3*screen.getTerminalSize().getColumns()/4, count3, line, Terminal.Color.CYAN, Terminal.Color.BLACK);
            count3++;
        }

        screen.refresh();

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                GUIScreen guiScreen = new GUIScreen(screen);
                guiScreen.setTheme(new MyTheme());
                guiScreen.getScreen().startScreen();

                MainMenu myWindow = new MainMenu();
                guiScreen.showWindow(myWindow, GUIScreen.Position.CENTER);
            }
        }, 5000);
    }

    private class MainMenu extends Window {

        public MainMenu() {
            super("BomberDeal - The marvelous #filadofundo's game");

            TextBox userBox = createTextBox();
            TextBox serverBox = createTextBox();

            Panel mainPanel = new Panel(new Border.Invisible(), Panel.Orientation.VERTICAL);
            addComponent(mainPanel);
            mainPanel.addComponent(new Label("Insert your username"));
            mainPanel.addComponent(userBox);
            mainPanel.addComponent(new Label("Insert the serverold's IP address"));
            mainPanel.addComponent(serverBox);
            mainPanel.addComponent(new Label("\nPlease select an option below"));
            mainPanel.addComponent(new Button("Connect", new Action() {
                @Override
                public void doAction() {
                    if (userBox.getText().matches("^(?=.{4,20}$)(?![_.])(?!.*[_.]{2})[a-zA-Z0-9._]+(?<![_.])$") && serverBox.getText().matches("^(([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\\.){3}([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])$")) {
                        System.out.println("Open socket");
                        System.out.println("UserBox: " + userBox.getText());
                        System.out.println("ServerBox: " + serverBox.getText());

                        username = userBox.getText();
                        hostname = serverBox.getText();

                        stopActualScreen();

                    } else {
                        MessageBox.showMessageBox(getOwner(), "", "Please insert a username with 4 or more characters and a valid IP address.");
                    }

//                 udpSocket.getOutputStream().write();
                }
            }));
            mainPanel.addComponent(new Button("How to Play", new Action() {
                @Override
                public void doAction() {
                    MessageBox.showMessageBox(getOwner(), "", "Use Arrow Keys to move.\nUse Space to drop bomb.\n\nBe the last one ALIVE!");
                }
            }));
            mainPanel.addComponent(new Button("Credits", new Action() {
                @Override
                public void doAction() {
                    MessageBox.showMessageBox(getOwner(), "", "Alexandre Ramos\nAndré Leal\nFilipe Branco\nJoão Amaral\nRute Carrapato");
                }
            }));
            mainPanel.addComponent(new Button("Quit Game", new Action() {
                @Override
                public void doAction() {
                    System.out.println("Closed game");
                    System.exit(0);
                    //socket.close();
                }
            }));

        }

        private synchronized void stopActualScreen() {

            phaseOver = true;
            screen.stopScreen();

        }

        private TextBox createTextBox() {
            TextBox textBox = new TextBox(null, 60);
            textBox.addComponentListener(new ComponentListener() {
                @Override
                public void onComponentInvalidated(Component component) {
                }

                @Override
                public void onComponentReceivedFocus(InteractableComponent interactableComponent) {
                }

                @Override
                public void onComponentLostFocus(InteractableComponent interactableComponent) {
                }
            });
            return textBox;
        }

    }

    public static class MyTheme extends com.googlecode.lanterna.gui.Theme {

        public MyTheme() {
            int random = (int) ((Math.random() * (Terminal.Color.values().length)));

            if (random == 0 || random == 8 || random == 7) {
                random = 1;
            }
            super.setDefinition(Category.SCREEN_BACKGROUND, new Definition(Terminal.Color.BLACK, Terminal.Color.values()[random]));
            super.setDefinition(Category.BUTTON_ACTIVE, new Definition(Terminal.Color.BLACK, Terminal.Color.BLACK));
            super.setDefinition(Category.BUTTON_LABEL_ACTIVE, new Definition(Terminal.Color.DEFAULT, Terminal.Color.BLACK));
            super.setDefinition(Category.TEXTBOX, new Definition(Terminal.Color.WHITE, Terminal.Color.BLACK));
            super.setDefinition(Category.TEXTBOX_FOCUSED, new Definition(Terminal.Color.WHITE, Terminal.Color.BLACK));
        }


    }

    public synchronized boolean isPhaseOver() {
        return phaseOver;
    }
}
