package com.github.natriumpt.bomberdeal.Examples;

import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.gui.Border;
import com.googlecode.lanterna.gui.GUIScreen;
import com.googlecode.lanterna.gui.Window;
import com.googlecode.lanterna.gui.component.Button;
import com.googlecode.lanterna.gui.component.Label;
import com.googlecode.lanterna.gui.component.Panel;
import com.googlecode.lanterna.screen.Screen;


public class LanternaGUIExample {

    public static void main(String[] args) {

        Screen screen = TerminalFacade.createScreen();
        GUIScreen guiScreen = new GUIScreen(screen);
        guiScreen.getScreen().startScreen();

        MyWindow myWindow = new MyWindow();
        guiScreen.showWindow(myWindow, GUIScreen.Position.CENTER);

    }

    public static class MyWindow extends Window {
        public MyWindow() {
            super("BomberDeal - The marvelous #filadofundo game");


            Panel mainPanel = new Panel(new Border.Invisible(), Panel.Orientation.VERTICAL);
            addComponent(mainPanel);
            mainPanel.addComponent(new Label("Please select an option below"));
            mainPanel.addComponent(new Button("Connect"));
            mainPanel.addComponent(new Button("How to Play"));
            mainPanel.addComponent(new Button("Quit Game"));

/*
            Panel horisontalPanel = new Panel(new Border.Invisible(), Panel.Orientation.HORISONTAL);
            Panel leftPanel = new Panel(new Border.Bevel(true), Panel.Orientation.VERTICAL);
            Panel middlePanel = new Panel(new Border.Bevel(true), Panel.Orientation.VERTICAL);
            Panel rightPanel = new Panel(new Border.Bevel(true), Panel.Orientation.VERTICAL);

            horisontalPanel.addComponent(leftPanel);
            horisontalPanel.addComponent(middlePanel);
            horisontalPanel.addComponent(rightPanel);

            addComponent(horisontalPanel);

            leftPanel.addComponent(new Label("This is the first label"));
            addComponent(new Label("This is the second label, red", Terminal.Color.RED));
            addComponent(new Label("This is the third label, fixed 50 columns", 50));
            addComponent(new Label("This is the last label\nSpanning\nMultiple\nRows"));

            addComponent(new Button("Button with no action"));

            addComponent(new Button("Button with action", new Action() {
                @Override
                public void doAction() {
                    MessageBox.showMessageBox(getOwner(), "Hello", "You selected the button with an action attached to it!");
                }
            }));
*/

        }
    }

}
