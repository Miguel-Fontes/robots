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
     * Computes a command and returns the Robot final position
     *
     * @param request a request containing the robot data and command to be computed
     * @return the Robot final position and associated data
     */
    ComputeRobotCommandResponse compute(ComputeRobotCommandRequest request) throws IllegalPositionException, IllegalCommandException;

}
