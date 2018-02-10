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
    private final Map<Position, Robot> robots;

    public Zone(Integer length, Integer height) {
        this.dimension = new Dimension(length, height);
        this.robots = new HashMap<>();
    }

    public Zone(Dimension dimension) {
        this.dimension = dimension;
        this.robots = new HashMap<>();
    }

    /**
     * returns the inner Dimension object that represents the size of the zone
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
    public Zone addRobot(Robot robot) {
        robots.put(robot.getPosition(), robot);

        return this;
    }

    /**
     * fetches the Robot at a given position; if it doesn't exists, returns
     * a {@code Optional.Empty}
     *
     * @param position the position of a robot
     * @return a {@code Optional<Robot>} if it exists; otherwise an Optional.Empty
     */
    public Optional<Robot> getRobotAtPosition(Position position) {
        return Optional.ofNullable(robots.get(position));
    }
}
