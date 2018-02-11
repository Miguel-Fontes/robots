package br.com.miguelmf.robots.port.nasa.data;

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
}
