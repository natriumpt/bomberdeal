package com.github.natriumpt.bomberdeal.client;

public enum ControlProtocol {

    NORTH("NORTH"),
    SOUTH("SOUTH"),
    WEST("WEST"),
    EAST("EAST"),
    BOMB("BOMB");

    String message;

    ControlProtocol(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void sendAction() {
        System.out.println(message);
    }

}
