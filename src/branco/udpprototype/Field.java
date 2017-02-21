package branco.udpprototype;

import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.ScreenWriter;
import com.googlecode.lanterna.terminal.Terminal;

/**
 * Created by codecadet on 2/19/17.
 */
public class Field {
    public static int width;
    public static int height;
    //Uses to write do screen
    private static Screen screen;
    //Screen wrapper that preserves default options
    private static ScreenWriter screenWriter;

    public static void init(int width, int height) {
        //create da GUI
        screen = TerminalFacade.createScreen();
        //set field size
        Field.width = width;
        Field.height = height;
        screen.getTerminal().getTerminalSize().setColumns(width);
        screen.getTerminal().getTerminalSize().setRows(height);
        //default screen writing options
        screenWriter = new ScreenWriter(screen);
        screenWriter.setBackgroundColor(Terminal.Color.CYAN);
        screenWriter.setForegroundColor(Terminal.Color.WHITE);
        screen.startScreen();
    }

    public static void draw(int row, int col, String str) {
        screen.clear();
        screenWriter.drawString(row, col, str);
        screen.refresh();
    }

    public static Key moveWithKeys() {
        while (true) {
            Key key = screen.readInput();
            if (key.getKind() == Key.Kind.ArrowDown) {
                screen.setCursorPosition(0, height + 1);
            }
            if (key.getKind() == Key.Kind.ArrowUp) {
                screen.setCursorPosition(0, height - 1);
            }
            if (key.getKind() == Key.Kind.ArrowRight) {
                screen.setCursorPosition(width + 1, 0);
            }
            if (key.getKind() == Key.Kind.ArrowLeft) {
                screen.setCursorPosition(width - 1, 0);
            }
         }
    }
}