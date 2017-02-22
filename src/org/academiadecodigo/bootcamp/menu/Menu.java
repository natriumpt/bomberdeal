package org.academiadecodigo.bootcamp.menu;

import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.gui.*;
import com.googlecode.lanterna.gui.component.*;
import com.googlecode.lanterna.gui.dialog.MessageBox;
import com.googlecode.lanterna.gui.listener.ComponentListener;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.Terminal;

/**
 * Created by codecadet on 2/22/17.
 */
public class Menu {
    private Screen screen;

    public void init() {
        screen = TerminalFacade.createScreen();
        GUIScreen guiScreen = new GUIScreen(screen);
        guiScreen.setTheme(new MyTheme());
        guiScreen.getScreen().startScreen();

        MainMenu myWindow = new MainMenu(screen);
        guiScreen.showWindow(myWindow, GUIScreen.Position.CENTER);

    }

    public class MainMenu extends Window {

        public MainMenu(Screen screen) {
            super("BomberDeal - The marvelous #filadofundo game");

            TextBox userBox = createTextBox();
            TextBox serverBox = createTextBox();

            Panel mainPanel = new Panel(new Border.Invisible(), Panel.Orientation.VERTICAL);
            addComponent(mainPanel);
            mainPanel.addComponent(new Label("Insert your username"));
            mainPanel.addComponent(userBox);
            mainPanel.addComponent(new Label("Insert Server IP address"));
            mainPanel.addComponent(serverBox);
            mainPanel.addComponent(new Label("\nPlease select an option below"));
            mainPanel.addComponent(new Button("Connect", new Action() {
                @Override
                public void doAction() {
                    System.out.println("Open socket");
                    System.out.println("UserBox: " + userBox.getText());
                    System.out.println("ServerBox: " + serverBox.getText());
//                  udpSocket.getOutputStream().write();
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

            if (random == 0 || random == 7) {
                random = 1;
            }
            super.setDefinition(Category.SCREEN_BACKGROUND, new Definition(Terminal.Color.BLACK, Terminal.Color.values()[random]));
            super.setDefinition(Category.BUTTON_ACTIVE, new Definition(Terminal.Color.BLACK, Terminal.Color.BLACK));
            super.setDefinition(Category.BUTTON_LABEL_ACTIVE, new Definition(Terminal.Color.DEFAULT, Terminal.Color.BLACK));
            super.setDefinition(Category.TEXTBOX, new Definition(Terminal.Color.WHITE, Terminal.Color.BLACK));
            setDefinition(Category.TEXTBOX_FOCUSED, new Definition(Terminal.Color.WHITE, Terminal.Color.BLACK));

        }


    }
}
