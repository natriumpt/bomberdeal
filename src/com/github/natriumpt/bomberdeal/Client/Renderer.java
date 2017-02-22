package com.github.natriumpt.bomberdeal.Client;

import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.ScreenWriter;
import com.googlecode.lanterna.terminal.Terminal;

import static com.github.natriumpt.bomberdeal.Client.ClientCore.CURSOR_WIDTH;

public class Renderer implements Runnable {

    private Screen screen;
    private ScreenWriter screenWriter;

    public Renderer(Screen screen, ScreenWriter screenWriter) {

        this.screen = screen;
        this.screenWriter = screenWriter;

    }

    @Override
    public void run() {

        while (true) {

            clearScreen();
            screen.refresh();

        }

    }

    private void drawSquare(int posX, int posY, Terminal.Color color) {
        screenWriter.setBackgroundColor(color);
        screenWriter.drawString(posX * CURSOR_WIDTH, posY, "  ");
    }

    private void clearScreen() {
        screenWriter.setBackgroundColor(Terminal.Color.DEFAULT);
        screenWriter.fillScreen(' ');
    }

}
