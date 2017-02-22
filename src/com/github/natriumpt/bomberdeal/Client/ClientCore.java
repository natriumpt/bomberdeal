package com.github.natriumpt.bomberdeal.Client;

import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.ScreenWriter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ClientCore {

    static final int CURSOR_WIDTH = 2;
    private static final int SCREEN_COLS = 30;
    private static final int SCREEN_ROWS = 30;

    private Screen screen;
    private ScreenWriter screenWriter;

    public ClientCore() {

        screen = TerminalFacade.createScreen();
        screenWriter = new ScreenWriter(screen);

        screen.getTerminal().getTerminalSize().setColumns(SCREEN_COLS * CURSOR_WIDTH);
        screen.getTerminal().getTerminalSize().setRows(SCREEN_ROWS);

    }

    public void start() {

        screen.startScreen();

        ExecutorService pool = Executors.newFixedThreadPool(2);
        pool.submit(new Controller(screen));
        pool.submit(new Renderer(screen, screenWriter));

    }

}
