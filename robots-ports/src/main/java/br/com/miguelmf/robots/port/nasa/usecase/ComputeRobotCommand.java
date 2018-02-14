package br.com.miguelmf.robots.port.nasa.usecase;

import br.com.miguelmf.robots.port.nasa.data.ComputeRobotCommandRequest;
import br.com.miguelmf.robots.port.nasa.data.ComputeRobotCommandResponse;
import br.com.miguelmf.robots.port.nasa.exception.IllegalCommandException;
import br.com.miguelmf.robots.port.nasa.exception.IllegalPositionException;

/**
 * ComputeRobotCommand defines the contract of the functionality that
 * receives commands for computing Robot actions
 *
 * @author Miguel Fontes
 */
public interface ComputeRobotCommand {

    /**
     * Computes a command on a Robot at Position (0, 0) on a new Zone of Dimension (5, 5), and returns the Robot final position.
     *
     * @param request a request containing the robot data and command to be computed
     * @return the Robot's final position
     * @throws IllegalPositionException when the computation of a command results on a Robot on a Illegal Position
     * @throws IllegalCommandException when a invalid command is found on the Commands sequence
     */
    ComputeRobotCommandResponse compute(ComputeRobotCommandRequest request) throws IllegalPositionException, IllegalCommandException;

}
