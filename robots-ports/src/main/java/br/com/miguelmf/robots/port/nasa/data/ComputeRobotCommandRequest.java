package br.com.miguelmf.robots.port.nasa.data;

/**
 *
 * @author Miguel Fontes
 */
public class ComputeRobotCommandRequest {
    private final Integer robotId;
    private final String command;

    /**
     * Constructor for compatibility with frameworks.
     * <b>DO NOT USE IT DIRECTLY!</b>
     */
    @Deprecated
    public ComputeRobotCommandRequest() {
        robotId = null;
        command = null;
    }

    /**
     * Build a ComputeRobotCommandRequest setting the Robot Id as the default <b>0</b>.
     *
     * @param command the command to be computed
     */
    public ComputeRobotCommandRequest(String command) {
        this.robotId = 0;
        this.command = command;
    }

    /**
     * Build a ComputeRobotCommandRequest
     *
     * @param robotId robotId of the robot for which the command will be computed
     * @param command the command to be computed
     */
    public ComputeRobotCommandRequest(Integer robotId, String command) {
        this.robotId = robotId;
        this.command = command;
    }

    /**
     * Gets robotId
     *
     * @return value of robotId
     */
    public Integer getRobotId() {
        return robotId;
    }

    /**
     * Gets command
     *
     * @return value of command
     */
    public String getCommand() {
        return command;
    }

    @Override
    public String toString() {
        return "ComputeRobotCommandRequest{" +
                "robotId=" + robotId +
                ", command='" + command + '\'' +
                '}';
    }
}
