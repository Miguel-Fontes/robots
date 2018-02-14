package br.com.miguelmf.robots.port.nasa.data;

import br.com.miguelmf.robots.core.Direction;
import br.com.miguelmf.robots.core.Position;
import br.com.miguelmf.robots.core.Robot;

/**
 * ComputeRobotCommandResponse encapsulates data returned by
 * {@link br.com.miguelmf.robots.port.nasa.usecase.ComputeRobotCommand}
 *
 * @author Miguel Fontes
 */
public class ComputeRobotCommandResponse {

    private final String robotFinalPosition;

    /**
     * Constructor for compatibility with frameworks.
     * <b>DO NOT USE IT DIRECTLY!</b>
     */
    @Deprecated
    public ComputeRobotCommandResponse() {
        robotFinalPosition = null;
    }

    /**
     * Builds a ComputeRobotCommandResponse
     *
     * @param robotFinalPosition the Robot final position as a String formatted as (x, y, direction); Ex: (2, 1, N)
     */
    public ComputeRobotCommandResponse(String robotFinalPosition) {
        this.robotFinalPosition = robotFinalPosition;
    }

    /**
     * Builds a ComputeRobotCommandResponse from a Robot
     *
     * @param robot the robot from which the position should be extract
     */
    public ComputeRobotCommandResponse(Robot robot) {
        this.robotFinalPosition = formatRobotFinalPositionAsString(robot);
    }

    /**
     * Gets robotFinalPosition
     *
     * @return value of robotFinalPosition
     */
    public String getRobotFinalPosition() {
        return robotFinalPosition;
    }

    @Override
    public String toString() {
        return "ComputeRobotCommandResponse{" +
                "robotFinalPosition='" + robotFinalPosition + '\'' +
                '}';
    }

    /**
     * Creates a new ComputeRobotCommandResponse from a Robot instance, extracting
     * the required data for instance creation
     *
     * @param robot the robot from which the data will be fetched
     * @return a ComputeRobotCommandResponse with the data copied from the give Robot instance
     */
    public static ComputeRobotCommandResponse from(Robot robot) {
        return new ComputeRobotCommandResponse(
                formatRobotFinalPositionAsString(robot));
    }

    /**
     * formatRobotFinalPositionAsString formats the robot current position
     * as (x, y, direction). E.g. (1, 2, N).
     *
     * @param robot the Robot from which the data should be extracted
     * @return the robots final position formatted as a String
     */
    private static String formatRobotFinalPositionAsString(Robot robot) {
        Position position = robot.getPosition();
        Direction direction = robot.getDirection();

        return String.format("(%s, %s, %s)", position.getX(), position.getY(), direction.getDirection());
    }
}
