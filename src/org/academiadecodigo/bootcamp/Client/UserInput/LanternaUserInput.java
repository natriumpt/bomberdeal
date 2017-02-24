package org.academiadecodigo.bootcamp.Client.UserInput;

import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.screen.Screen;
import org.academiadecodigo.bootcamp.Client.Actions.PlayerMessages;
import org.academiadecodigo.bootcamp.Client.Network.ClientNetworkUDP;

/**
 * Created by codecadet on 2/22/17.
 */
public class LanternaUserInput implements UserInput {

    private Screen screen;
    private String playerAction;
    private ClientNetworkUDP udpConnection;

    public LanternaUserInput(Screen screen) {

        this.screen = screen;

    }

    @Override
    public void run() {

        while(true) {

            Key key = screen.readInput();

            if (key == null) {
                try {
                    Thread.sleep(17);
                    continue;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

                switch (key.getKind()) {

                    case NormalKey:

                        if (key.getCharacter() == ' ') {
                            playerAction = playerAction + PlayerMessages.PLAYER_DEPLOY;
                        }

                        break;
                    case Escape:
                        System.exit(0);
                        break;
                    case ArrowLeft:
                        playerAction += PlayerMessages.PLAYER_MOVELEFT;
                        break;
                    case ArrowRight:
                        playerAction += PlayerMessages.PLAYER_MOVERIGHT;
                        break;
                    case ArrowUp:
                        playerAction += PlayerMessages.PLAYER_MOVEUP;
                        break;
                    case ArrowDown:
                        playerAction += PlayerMessages.PLAYER_MOVEDOWN;
                        break;
                    case Enter:
                        System.out.println("Enter");
                        break;
                    case Unknown:
                        break;
                    default:
                        System.out.println("Woah there!");
                        break;

                }

        }
    }


    @Override
    public void setUdpConnection(ClientNetworkUDP client) {

        this.udpConnection = client;
    }
}


