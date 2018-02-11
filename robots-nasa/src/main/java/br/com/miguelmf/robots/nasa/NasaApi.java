package br.com.miguelmf.robots.nasa;

import br.com.miguelmf.robots.port.nasa.Nasa;
import br.com.miguelmf.robots.port.nasa.data.ComputeRobotCommandRequest;
import br.com.miguelmf.robots.port.nasa.data.ComputeRobotCommandResponse;

public class NasaApi implements Nasa {

    @Override
    public ComputeRobotCommandResponse compute(ComputeRobotCommandRequest request) {
        return null;
    }

    @Override
    public void initilize() {

    }
}
