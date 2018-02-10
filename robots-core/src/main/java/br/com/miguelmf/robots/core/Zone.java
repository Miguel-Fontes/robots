package br.com.miguelmf.robots.core;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Zone represents a bidimensional plane where Robots may exist.
 *
 * @author Miguel Fontes
 */
public class Zone {
    private final Dimension dimension;
    private final Map<Position, Robot> zone;
    private final Map<Integer, Robot> robots;

    public Zone(Integer length, Integer height) {
        this.dimension = new Dimension(length, height);
        this.robots = new HashMap<>();
        this.zone = new HashMap<>();
    }

    public Zone(Dimension dimension) {
        this.dimension = dimension;
        this.robots = new HashMap<>();
        this.zone = new HashMap<>();
    }

    /**
     * returns the inner Dimension object that represents the dimensions of the zone
     *
     * @return the Zone's Dimension
     */
    public Dimension getDimension() {
        return dimension;
    }

    /**
     * Add a robot at it's current position attribute; if there's a robot at that
     * position it will be replaced by the new one;
     *
     * @param robot the robot to be added
     * @return this Zone instance
     */
    public Zone addRobot(Robot robot) throws IllegalPositionException {
        validatePosition(robot);

        if (robots.containsKey(robot.getId())) {
            Robot oldRobot = robots.get(robot.getId());
            zone.remove(oldRobot.getPosition());
        }

        robots.put(robot.getId(), robot);
        zone.put(robot.getPosition(), robot);

        return this;
    }

    /**
     * Validates if the given robot has a valid position.
     *
     * @param robot a robot to be validated
     * @throws IllegalPositionException when Robot's position is not valid
     */
    private void validatePosition(Robot robot) throws IllegalPositionException {
        Position position = robot.getPosition();
        if (dimension.getLength() <= position.getX()
                || dimension.getHeight() <= position.getY()) {
            throw new IllegalPositionException(String.format("Robot's position [%s] is out of the Zone Bounds [%s]", robot.getPosition().toString(), dimension.toString()));
        }


    }

    /**
     * fetches the Robot at a given position; if it doesn't exists, returns
     * a {@code Optional.Empty}
     *
     * @param position the position of a robot
     * @return a {@code Optional<Robot>} if it exists; otherwise an Optional.Empty
     */
    public Optional<Robot> getRobotAtPosition(Position position) {
        return Optional.ofNullable(zone.get(position));
    }
}
