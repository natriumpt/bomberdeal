package org.academiadecodigo.bootcamp.bomberdeal.server.Network;

/**
 * Created by andre on 2/26/2017.
 */
public final class ServerNetworkMessages {

    public static final String COORDS_SPACE = ";";
    public static final String SERVER_SPACE = ":";

    public static final String SERVER_MAP_SENDING_LAYOUT = "MAP" + SERVER_SPACE + "SENDING_LAYOUT";
    public static final String SERVER_MAP_LAYOUT_COMPLETE = "MAP" + SERVER_SPACE + "LAYOUTSENT";

    public static final String SERVER_PLAYER_DEATH = "YOU" + SERVER_SPACE + "DEAD";

    public static final String PLAYER_ID_NAME = "PLAYERID" + SERVER_SPACE;

}
