package br.com.miguelmf.robots.port.nasa.data;

/**
 *
 * @author Miguel Fontes
 */
public class ComputeRobotCommandRequest {
    private final Integer id;
    private final String command;

    /**
     * Constructor for compatibility with frameworks.
     * <b>DO NOT USE IT DIRECTLY!</b>
     */
    @Deprecated
    public ComputeRobotCommandRequest() {
        id = null;
        command = null;
    }

    /**
     * Build a ComputeRobotCommandRequest
     *
     * @param robotId id of the robot for which the command will be computed
     * @param command the command to be computed
     */
    public ComputeRobotCommandRequest(Integer robotId, String command) {
        this.id = robotId;
        this.command = command;
    }

    /**
     * Gets id
     *
     * @return value of id
     */
    public Integer getId() {
        return id;
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
                "id=" + id +
                ", command='" + command + '\'' +
                '}';
    }
}
