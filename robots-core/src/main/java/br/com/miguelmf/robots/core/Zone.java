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
        if (isPositionBelowZoneLowerBound(position) || isPositionOverZoneUpperBound(position)) {
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

    /**
     * isPositionBelowZoneLowerBound is a predicate that validates if
     * a given Position is below this Zone lower bound. A Zone Dimension uses a
     * zero based logic; hence, its lower bound is <b>always</b> zero, thus any
     * valid position must obey the following predicate:
     *
     * <pre>
     *     For all valid Position (x, y); x >= 0 AND y >= 0;
     * </pre>
     *
     * Hence, isPositionBelowZoneLowerBound is the negation the above rule;
     *
     * <pre>
     *     For all invalid Position below a Zone Lower Bound; !(x >= 0) AND !(y >= 0)
     * </pre>
     *
     * This negation is implemented as an inversion.
     *
     * @param position a position to be validated
     * @return a boolean indicating if the position is invalid (true for invalid)
     */
    private boolean isPositionBelowZoneLowerBound(Position position) {
        return position.getX() < 0 || position.getY() < 0;
    }

    /**
     * isPositionOverZoneUpperBound is a predicate that validates if a given Position is
     * over this Zone upper bound. A Zone upper bound is defined by inner dimension
     * object, received on construction time. The logic is based on the classic
     * XxY notation (e.g. 5x5) being x the length and y the height of a Zone, thus
     * the predicate that defines a valid position is:
     *
     * <pre>
     *     For all valid Position (x, y) on a Zone with Dimension (length, height); x < dimension.length AND y < dimension.height
     * </pre>
     *
     * A Zone dimension use a zero based logic, thus, for a Position (x, y), x AND y must
     * be below the Dimension length and height.
     *
     * isPositionOverZoneUpperBound is the negation of the above rule.
     *
     * <pre>
     *     For all invalid Position above a Zone Upper Bound; !(x < dimension.length) AND !(y < dimension.height)
     * </pre>
     *
     * This negation is implemented as an inversion.
     *
     * @param position a position to be validated
     * @return a boolean indicating if the position is invalid (true for invalid)
     */
    private boolean isPositionOverZoneUpperBound(Position position) {
        return position.getX() >= dimension.getLength() || position.getY() >= dimension.getHeight();
    }

    /**
     * Obtains a Robot by it's id.
     *
     * @param robotId the Robot id to be searched
     * @return return the found Robot or a Optional.Empty otherwise
     */
    public Optional<Robot> getRobotById(Integer robotId) {
        return Optional.ofNullable(robots.get(robotId));
    }
}
