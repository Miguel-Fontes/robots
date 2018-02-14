package br.com.miguelmf.robots.core;

/**
 * An identified Robot that has a identifier, position, facing direction, and a speed.
 * The robot speed determines how much 'steps' it does with it moves. See {@link Robot#move()}
 *
 * @author Miguel Fontes
 */
public class Robot {
    private final Direction direction;
    private final Position position;

    public Robot(Direction direction, Position position) {
        this.direction = direction;
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

    public Robot turnLeft() {
        return new Robot(direction.left(), position);
    }

    public Robot turnRight() {
        return new Robot(direction.right(), position);
    }

    /**
     * Computes the next position of a Robot, moving it ahead
     * on the direction he is facing. A Robot movement is based on it's speed,
     * so, with a speed of 1 a Position of (x: 0, y: 0) becomes (x: 0, y: 1) when
     * the robot moves North, because [y + speed = 1].
     *
     * @return a new Instance of Robot with the updated position attribute
     */
    public Robot move() {
        Position newPosition;

        switch (direction) {
            case NORTH:
                newPosition = new Position(position.getX(), position.getY() + 1);
                break;

            case EAST:
                newPosition = new Position(position.getX() + 1, position.getY());
                break;

            case SOUTH:
                newPosition = new Position(position.getX(), position.getY() - 1);
                break;

            case WEST:
                newPosition = new Position(position.getX() - 1, position.getY());
                break;

            default:
                newPosition = position;
        }

        return new Robot(direction, newPosition);
    }

    public Direction getDirection() {
        return direction;
    }

    @Override
    public String toString() {
        return "Robot{" +
                "direction=" + direction +
                ", position=" + position +
                '}';
    }
}
