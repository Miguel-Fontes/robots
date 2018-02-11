package br.com.miguelmf.robots.port.nasa;

import br.com.miguelmf.robots.port.nasa.usecase.ComputeRobotCommand;
import br.com.miguelmf.robots.port.nasa.usecase.InitializeZone;

/**
 * Nasa defines the top level API that the Nasa client
 * application uses to interact with the application
 *
 * @author Miguel Fontes
 */
public interface Nasa extends
        ComputeRobotCommand,
        InitializeZone {
}
