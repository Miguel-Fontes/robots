package br.com.miguelmf.robots.port.nasa.data;

/**
 *
 * @author Miguel Fontes
 */
public class ComputeRobotCommandRequest {
    private final String command;

    /**
     * Constructor for compatibility with frameworks.
     * <b>DO NOT USE IT DIRECTLY!</b>
     */
    @Deprecated
    public ComputeRobotCommandRequest() {
        command = null;
    }

    /**
     * Build a ComputeRobotCommandRequest
     *
     * @param command the command to be computed
     */
    public ComputeRobotCommandRequest(String command) {
        this.command = command;
    }
    /**
     * Gets command
     *
     * @return value of command
     */
    public String getCommand() {
        return command;
    }

    /**
     * Builds a new ComputeRobotCommandRequest with the give command
     *
     * @param command the Robot Commands to be executed
     * @return a ComputeRobotCommandRequest
     */
    public static ComputeRobotCommandRequest of(String command) {
        return new ComputeRobotCommandRequest(command);
    }

    @Override
    public String toString() {
        return "ComputeRobotCommandRequest{" +
                "command='" + command + '\'' +
                '}';
    }
}
