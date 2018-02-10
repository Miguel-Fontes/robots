package br.com.miguelmf.robots.core;

/**
 * Direction contains the possible directions for a ROBOT
 *
 * @author Miguel Fontes
 */
public enum Direction {
    NORTH("N"), EAST("E"), SOUTH("S"), WEST("W");

    private final String direction;

    Direction(String direction) {
        this.direction = direction;
    }

    public String getDirection() {
        return direction;
    }

    private Direction fromString(String abbrv) {
        for (Direction direction : Direction.values())
            if (direction.getDirection().equals(abbrv))
                return direction;

        throw new IllegalArgumentException("No enum constant with abbrv " + abbrv + " found");
    }

    public Direction left() {
        int currentDirectionIndex = fromString(this.direction).ordinal();
        int lastDirectionElementIndex = Direction.values().length - 1;

        return currentDirectionIndex - 1 >= 0
                ? Direction.values()[currentDirectionIndex - 1]
                : Direction.values()[lastDirectionElementIndex];
    }

    public Direction right() {
        int currentDirectionIndex = fromString(this.direction).ordinal();
        int lastDirectionElementIndex = Direction.values().length - 1;
        int firstDirectionElementIndex = 0;

        return currentDirectionIndex + 1 <= lastDirectionElementIndex
                ? Direction.values()[currentDirectionIndex + 1]
                : Direction.values()[firstDirectionElementIndex];
    }
}
