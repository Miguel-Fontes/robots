package br.com.miguelmf.robots.nasa;

import br.com.miguelmf.robots.core.Dimension;
import br.com.miguelmf.robots.core.Direction;
import br.com.miguelmf.robots.core.Position;
import br.com.miguelmf.robots.core.Robot;
import br.com.miguelmf.robots.core.Zone;
import br.com.miguelmf.robots.port.nasa.Nasa;
import br.com.miguelmf.robots.port.nasa.data.ComputeRobotCommandRequest;
import br.com.miguelmf.robots.port.nasa.data.ComputeRobotCommandResponse;
import br.com.miguelmf.robots.port.nasa.exception.IllegalCommandException;
import br.com.miguelmf.robots.port.nasa.exception.IllegalPositionException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * NasaApi implements the functions defined at the Nasa application contract.
 *
 * @author Miguel Fontes
 */
@Service
public class NasaApi implements Nasa {

    private Map<Character, RobotCommand> supportedCommands;

    public NasaApi() {
        this.supportedCommands =  new HashMap<>();
        supportedCommands.put('M', Robot::move);
        supportedCommands.put('R', Robot::turnRight);
        supportedCommands.put('L', Robot::turnLeft);
    }

    /**
     * Compute a Robot command and return it's final position. Before computing a command,
     * the inner Zone will be reset
     *
     * @param request a request containing the robot data and command to be computed
     * @return the Robots final position
     */
    @Override
    public ComputeRobotCommandResponse compute(ComputeRobotCommandRequest request) throws IllegalPositionException, IllegalCommandException {
        return ComputeRobotCommandResponse.from(
                zoneWithDefaultDimensions()
                        .compute(robotAtZeroPositionFacingNorth(),
                                robot -> executeCommands(request.getCommand(), robot))

                        .orElseThrow(() -> new IllegalPositionException(
                                String.format("The command [%s] results on an illegal Robot position!", request.getCommand()))));
    }

    /**
     * Initialize a new blank zone with default parameters
     * <ul>
     * <li>Dimension (5, 5)</li>
     * <li>Default Robot with id 0, Position (0, 0)</li>
     * </ul>
     * <p>
     * This method is executed automatically when an NasaApi is instantiated.
     */
    private Zone zoneWithDefaultDimensions() {
        return new Zone(new Dimension(5, 5));
    }

    /**
     * Initialize a new Robot with attributes:
     * <p>
     * <ul>
     * <li>Position (0, 0)</li>
     * <li>Direction.NORTH</li>
     * </ul>
     *
     * @return a Robot at Position (0, 0) facing North
     */
    private Robot robotAtZeroPositionFacingNorth() {
        return new Robot(Direction.NORTH, new Position(0, 0));
    }

    /**
     * Transforms the Commands String into a list of Command Keys and
     * executes them one by one on a given Robot.
     *
     * @param commands the Commands String to be computed
     * @param robot    the Robot for which the commands are to be executed
     * @return a Robot on the final state, after the commands execution
     */
    private Robot executeCommands(String commands, Robot robot) {
        for (char c : commands.toCharArray()) {
            robot = executeCommand(c, robot);
        }

        return robot;
    }

    /**
     * executeCommand executes a command indicated by a character named Command Key. The command key
     * is searched at the supported commands HashMap initialized at this class constructor.
     *
     * @param commandKey a character indicating a command to execute
     * @param robot      a robot for which the commands will be executed
     * @return the Robot on the new state after executing the command
     * @throws IllegalCommandException when an invalid command is received
     */
    private Robot executeCommand(char commandKey, Robot robot) {
        return Optional.ofNullable(supportedCommands.get(commandKey))
                .orElseThrow(() -> new IllegalCommandException(String.format("The [%s] command is invalid! Supported commands are %s", commandKey, supportedCommands.keySet())))
                .apply(robot);
    }

    /**
     * RobotCommand is a functional interface that represents a Command to be executed
     * on a Robot. It is basically a {@link java.util.function.Function}, but with a
     * semantic name (and no Generics).
     *
     * @author Miguel Fontes
     */
    @FunctionalInterface
    private interface RobotCommand {
        Robot apply(Robot robot);
    }
}
