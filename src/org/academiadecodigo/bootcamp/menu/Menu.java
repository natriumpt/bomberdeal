package org.academiadecodigo.bootcamp.menu;

import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.gui.*;
import com.googlecode.lanterna.gui.component.Button;
import com.googlecode.lanterna.gui.component.Label;
import com.googlecode.lanterna.gui.component.Panel;
import com.googlecode.lanterna.gui.dialog.MessageBox;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.Terminal;

/**
 * Created by codecadet on 2/22/17.
 */
public class Menu {

    public static void main(String[] args) {

        Screen screen = TerminalFacade.createScreen();
        GUIScreen guiScreen = new GUIScreen(screen);
        guiScreen.setTheme(new MyTheme());
        guiScreen.getScreen().startScreen();

        MainMenu myWindow = new MainMenu();
        guiScreen.showWindow(myWindow, GUIScreen.Position.CENTER);
    }


    public static class MainMenu extends Window {
        public MainMenu() {
            super("BomberDeal - The marvelous #filadofundo game");


            Panel mainPanel = new Panel(new Border.Invisible(), Panel.Orientation.VERTICAL);
            addComponent(mainPanel);
            mainPanel.addComponent(new Label("\nPlease select an option below\n "));
            mainPanel.addComponent(new Button("Connect", new Action() {
                @Override
                public void doAction() {
                    System.out.println("Open socket");
//                    udpSocket.getOutputStream().write();
                }
            }));
            mainPanel.addComponent(new Button("How to Play", new Action() {
                @Override
                public void doAction() {
                    MessageBox.showMessageBox(getOwner(), "", "Use Arrow Keys to move\nUse Space to drop bomb\n\nBe the last one ALIVE!");
                }
            }));
            mainPanel.addComponent(new Button("Quit Game", new Action() {
                @Override
                public void doAction() {
                    System.out.println("Close socket and close game");
                    System.exit(0);
                    //socket.close();
                }
            }));

        }

    }

    public static class MyTheme extends com.googlecode.lanterna.gui.Theme {

        public MyTheme() {
            int random = (int) ((Math.random() * (Terminal.Color.values().length)));

            if (random == 0 || random == 7) {
                random = 1;
            }
            super.setDefinition(Category.SCREEN_BACKGROUND, new Definition(Terminal.Color.BLACK, Terminal.Color.values()[random]));
            super.setDefinition(Category.BUTTON_ACTIVE, new Definition(Terminal.Color.BLACK, Terminal.Color.BLACK));
            super.setDefinition(Category.BUTTON_LABEL_ACTIVE, new Definition(Terminal.Color.DEFAULT, Terminal.Color.BLACK));

        }


    }
}
