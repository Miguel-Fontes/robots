package br.com.miguelmf.robots.core;

/**
 * Direction contains the possible directions for a ROBOT
 *
 * @author Miguel Fontes
 */
public enum Direction {
    NORTH("N"), SOUTH("S"), EAST("E"), WEST("W");

    private final String direction;

    Direction(String direction) {
        this.direction = direction;
    }

    public String getDirection() {
        return direction;
    }
}
