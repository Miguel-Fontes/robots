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

/**
 * NasaApi implements the functions defined at the Nasa application contract
 *
 * @author Miguel Fontes
 */
@Service
public class NasaApi implements Nasa {

    private Zone zone;

    public NasaApi() {
        initilize();
    }

    /**
     * Compute a Robot command and return it's final position. Before computing the command
     * the Zone will be reset.
     *
     * @param request a request containing the robot data and command to be computed
     * @return the Robots final position
     */
    @Override
    public ComputeRobotCommandResponse compute(ComputeRobotCommandRequest request) throws IllegalPositionException, IllegalCommandException {
        initilize();

        return ComputeRobotCommandResponse.from(
                zone.compute(new Robot(request.getRobotId(), Direction.NORTH, new Position(0, 0)),
                        robot -> executeCommands(request.getCommand(), robot))
                        .orElseThrow(IllegalPositionException::new));
    }

    private Robot executeCommands(String commands, Robot robot) {
        for (char c : commands.toCharArray()) {
            robot = executeCommand(c, robot);
        }

        return robot;
    }

    /**
     * executeCommand executes a command indicated by a character named Command Key
     *
     * @param commandKey a character indicating a command to execute
     * @param robot      a robot for which the commands will be executed
     * @return the Robot on the new state after executing the command
     * @throws IllegalCommandException when an invalid command is received
     */
    public Robot executeCommand(char commandKey, Robot robot) {
        switch (commandKey) {
            case 'M':
                robot = robot.move();
                break;
            case 'R':
                robot = robot.turnRight();
                break;
            case 'L':
                robot = robot.turnLeft();
                break;
            default:
                throw new IllegalCommandException(String.format("The [%s] command is invalid!", commandKey));
        }

        return robot;
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
    @Override
    public void initilize() {
        zone = new Zone(new Dimension(5, 5));
    }
}
