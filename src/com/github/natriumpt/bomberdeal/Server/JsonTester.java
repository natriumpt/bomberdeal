package com.github.natriumpt.bomberdeal.Server;

import com.google.gson.Gson;

public class JsonTester {

    public static void main(String[] args) {
        Gson gson = new Gson();
        Player player = new Player();

        player.setName("Alex");
        player.setX(10);
        player.setY(player.getX() * 10);

        System.out.println(gson.toJson(player));

        Player testFromJson = gson.fromJson("{\"name\":\"Other Player\",\"x\":20,\"y\":100}",Player.class);

        System.out.println(testFromJson.getName() + " " +  testFromJson.getX() + " " + testFromJson.getY());

    }

}
