package org.academiadecodigo.bootcamp.bomberdeal.client.userinput;

import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.screen.Screen;
import org.academiadecodigo.bootcamp.bomberdeal.client.actions.PlayerMessages;
import org.academiadecodigo.bootcamp.bomberdeal.client.grid.GridLanterna;
import org.academiadecodigo.bootcamp.bomberdeal.client.network.ClientNetworkUDP;

public class UserInputLanterna implements UserInput {

    private ClientNetworkUDP udpConnection;
    private GridLanterna grid;

    public UserInputLanterna(GridLanterna grid) {

        this.grid = grid;

    }

    @Override
    public void run() {

        while(true) {

            if(!grid.isGridCreated()) {

                System.out.println(grid.getScreen());
                continue;

            }

            Key key = grid.getScreen().readInput();

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
                            udpConnection.sendPacket(PlayerMessages.PLAYER_DEPLOY);
                        }

                        break;
                    case Escape:
                        System.exit(0);
                        break;
                    case ArrowLeft:
                        udpConnection.sendPacket(PlayerMessages.PLAYER_MOVELEFT);
                        break;
                    case ArrowRight:
                        udpConnection.sendPacket(PlayerMessages.PLAYER_MOVERIGHT);
                        break;
                    case ArrowUp:
                        udpConnection.sendPacket(PlayerMessages.PLAYER_MOVEUP);
                        break;
                    case ArrowDown:
                        udpConnection.sendPacket(PlayerMessages.PLAYER_MOVEDOWN);
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


