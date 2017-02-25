package org.academiadecodigo.bootcamp.bomberdeal.server.helper;

/**
 * Created by codecadet on 2/25/17.
 */
public class Util{

    public static boolean trueWithProb(double probability){
        return (Math.random() < probability);
    }
    public static int rndIdxInRange (int MAX) {
        return (int) Math.round(Math.random()*(MAX));
    }
    public static int rndBtwIdx(int MIN, int MAX){
        return (int) (MIN + (Math.round(Math.random() * (MAX-MIN))));
    }
}
