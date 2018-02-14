package br.com.miguelmf.robots.core;

import java.util.Optional;
import java.util.function.Function;

/**
 * Zone represents a bidimensional plane where Robots may exist. The class acts
 * like a context for safe command execution, providing the {@link Zone#compute(Robot, Function)}
 * which receives a {@link Robot} and a {@link Function} that
 * executes commands on it.
 *
 * @author Miguel Fontes
 */
public class Zone {
    private final Dimension dimension;

    public Zone(Integer length, Integer height) {
        this.dimension = new Dimension(length, height);
    }

    public Zone(Dimension dimension) {
        this.dimension = dimension;
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
     * Compute receives a Robot and a Function that executes commands on it. After the
     * function execution, the Robot final position will be validated and a Optional is
     * returned, indicating the success of the operation;
     *
     *
     * @param robot the robot for which the commands will be executed
     * @param fn the function that executes the commands on the Robot
     * @return a Robot, if the final position is valid; otherwise Empty.
     */
    public Optional<Robot> compute(Robot robot, Function<Robot, Robot> fn) {
        return Optional.of(robot)
                .map(fn)
                .filter(this::isRobotInValidPosition);
    }

    /**
     * isRobotInValidPosition is a predicate that validates if a Robot is in a valid position,
     * based on the Zone Dimensions, composing the isPositionAboveZoneLowerBound and isPositionBelowZoneUpperBound
     * predicates (listed below).
     *
     * @see Zone#isPositionAboveZoneLowerBound(Position)
     * @see Zone#isPositionBelowZoneUpperBound(Position)
     * @param robot a robot to be validated
     */
    private Boolean isRobotInValidPosition(Robot robot) {
        Position position = robot.getPosition();
        return isPositionAboveZoneLowerBound(position) && isPositionBelowZoneUpperBound(position);
    }

    /**
     * isPositionAboveZoneLowerBound is a predicate that validates if
     * a given Position is below this Zone lower bound. A Zone Dimension uses a
     * zero based index; hence, its lower bound is <b>always</b> zero, thus any
     * valid position must obey the following predicate:
     * <p>
     * <pre>
     *     For all valid Position (x, y); x >= 0 AND y >= 0;
     * </pre>
     *
     * @param position a position to be validated
     * @return a boolean indicating if the position is invalid (true for invalid)
     */
    private boolean isPositionAboveZoneLowerBound(Position position) {
        return position.getX() >= 0 && position.getY() >= 0;
    }

    /**
     * isPositionBelowZoneUpperBound is a predicate that validates if a given Position is
     * below this Zone upper bound. A Zone upper bound is defined a {@link Dimension}, defined
     * on construction time. A Dimension defines a Bounded Two-Dimensional Space
     * with bounds X by Y (e.g. 5x5) being X the length and Y the height. Hence
     * the predicate that defines a valid position is:
     *
     * <pre>
     *     For all valid Position (x, y) on a Zone with Dimension (length, height); x < dimension.length AND y < dimension.height
     * </pre>
     * <p>
     * A Zone dimension use a zero based index, thus, for a Position (x, y), x AND y must
     * be below the Dimension length and height.
     *
     * @param position a position to be validated
     * @return a boolean indicating if the position is invalid (true for invalid)
     */
    private boolean isPositionBelowZoneUpperBound(Position position) {
        return position.getX() < dimension.getLength() && position.getY() < dimension.getHeight();
    }

}
