package br.com.miguelmf.robots.core;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

/**
 * Zone represents a bidimensional plane where Robots may exist.
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

    public Optional<Robot> compute(Robot robot, Function<Robot, Robot> fn) {
        return Optional.of(robot)
                .map(fn)
                .filter(this::validatePosition);
    }

    /**
     * Validates if the given robot has a valid position.
     *
     * @param robot a robot to be validated
     */
    private Boolean validatePosition(Robot robot) {
        Position position = robot.getPosition();
        return !(isPositionBelowZoneLowerBound(position) || isPositionOverZoneUpperBound(position));
    }


    /**
     * isPositionBelowZoneLowerBound is a predicate that validates if
     * a given Position is below this Zone lower bound. A Zone Dimension uses a
     * zero based logic; hence, its lower bound is <b>always</b> zero, thus any
     * valid position must obey the following predicate:
     * <p>
     * <pre>
     *     For all valid Position (x, y); x >= 0 AND y >= 0;
     * </pre>
     * <p>
     * Hence, isPositionBelowZoneLowerBound is the negation the above rule;
     * <p>
     * <pre>
     *     For all invalid Position below a Zone Lower Bound; !(x >= 0) AND !(y >= 0)
     * </pre>
     * <p>
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
     * <p>
     * <pre>
     *     For all valid Position (x, y) on a Zone with Dimension (length, height); x < dimension.length AND y < dimension.height
     * </pre>
     * <p>
     * A Zone dimension use a zero based logic, thus, for a Position (x, y), x AND y must
     * be below the Dimension length and height.
     * <p>
     * isPositionOverZoneUpperBound is the negation of the above rule.
     * <p>
     * <pre>
     *     For all invalid Position above a Zone Upper Bound; !(x < dimension.length) AND !(y < dimension.height)
     * </pre>
     * <p>
     * This negation is implemented as an inversion.
     *
     * @param position a position to be validated
     * @return a boolean indicating if the position is invalid (true for invalid)
     */
    private boolean isPositionOverZoneUpperBound(Position position) {
        return position.getX() >= dimension.getLength() || position.getY() >= dimension.getHeight();
    }

}
