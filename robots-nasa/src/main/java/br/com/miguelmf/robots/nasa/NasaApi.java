package br.com.miguelmf.robots.nasa;

import br.com.miguelmf.robots.core.Dimension;
import br.com.miguelmf.robots.core.IllegalPositionException;
import br.com.miguelmf.robots.core.Position;
import br.com.miguelmf.robots.core.Robot;
import br.com.miguelmf.robots.core.Zone;
import br.com.miguelmf.robots.port.nasa.Nasa;
import br.com.miguelmf.robots.port.nasa.data.ComputeRobotCommandRequest;
import br.com.miguelmf.robots.port.nasa.data.ComputeRobotCommandResponse;
import br.com.miguelmf.robots.port.nasa.exception.RobotNotFoundException;

import java.util.Arrays;

import static br.com.miguelmf.robots.core.Direction.NORTH;

/**
 * NasaApi implements the functions defined at the Nasa application contract
 *
 * @author Miguel Fontes
 */
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
    public ComputeRobotCommandResponse compute(ComputeRobotCommandRequest request) {
        initilize();

        Robot robot = zone.getRobotById(request.getRobotId())
                .orElseThrow(RobotNotFoundException::new);

        String commands = request.getCommand();

        for (char c : commands.toCharArray()) {
            robot = executeCommand(c, robot);
        }

        return ComputeRobotCommandResponse.from(robot);
    }

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
        }

        return robot;
    }

    @Override
    public void initilize() {
        zone = new Zone(new Dimension(5, 5));
        try {
            zone.addRobot(new Robot(0, NORTH, new Position(0, 0), 1));
        } catch (IllegalPositionException e) {
            e.printStackTrace();
        }
    }
}
