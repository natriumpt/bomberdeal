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

            System.out.println("reading keys");
            Key key = grid.getScreen().readInput();

            if (key == null) {

                try {

                    Thread.sleep(17);
                    continue;

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

            System.out.println("here");

                switch (key.getKind()) {

                    case NormalKey:

                        if (key.getCharacter() == ' ') {
                            System.out.println("BOMB DEPLOYED");
                            udpConnection.sendPacket(PlayerMessages.PLAYER_DEPLOY);
                        }

                        break;
                    case Escape:
                        System.exit(0);
                        break;
                    case ArrowLeft:
                        System.out.println("MOVEMENT LEFT");
                        udpConnection.sendPacket(PlayerMessages.PLAYER_MOVELEFT);
                        break;
                    case ArrowRight:
                        System.out.println("MOVEMENT RIGHT");
                        udpConnection.sendPacket(PlayerMessages.PLAYER_MOVERIGHT);
                        break;
                    case ArrowUp:
                        System.out.println("MOVEMENT UP");
                        udpConnection.sendPacket(PlayerMessages.PLAYER_MOVEUP);
                        break;
                    case ArrowDown:
                        System.out.println("MOVEMENT DOWN");
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


