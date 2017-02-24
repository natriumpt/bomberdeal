package org.academiadecodigo.bootcamp.menu;

/**
 * Created by codecadet on 2/24/17.
 */
public interface Menu extends Runnable {

    boolean isPhaseOver();

    String getUsername();

    String getHostname();

}
