package br.com.miguelmf.robots.core;

/**
 * An identified Robot that has a identifier, position, facing direction, and a speed.
 * The robot speed determines how much 'steps' it does with it moves. See {@link Robot#move()}
 *
 * @author Miguel Fontes
 */
public class Robot {
    private final Integer id;
    private final Direction direction;
    private final Position position;
    private final Integer speed;

    public Robot(Integer id, Direction direction, Position position, Integer speed) {
        this.id = id;
        this.direction = direction;
        this.position = position;
        this.speed = speed;
    }

    public Position getPosition() {
        return position;
    }

    public Robot turnLeft() {
        return new Robot(id, direction.left(), position, 1);
    }

    public Robot turnRight() {
        return new Robot(id, direction.right(), position, 1);
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
                newPosition = new Position(position.getX(), position.getY() + speed);
                break;

            case EAST:
                newPosition = new Position(position.getX() + speed, position.getY());
                break;

            case SOUTH:
                newPosition = new Position(position.getX(), position.getY() - speed);
                break;

            case WEST:
                newPosition = new Position(position.getX() - speed, position.getY());
                break;

            default:
                newPosition = position;
        }

        return new Robot(id, direction, newPosition, 1);
    }

    public Direction getDirection() {
        return direction;
    }

    @Override
    public String toString() {
        return "Robot{" +
                "id=" + id +
                ", direction=" + direction +
                ", position=" + position +
                ", speed=" + speed +
                '}';
    }
}
