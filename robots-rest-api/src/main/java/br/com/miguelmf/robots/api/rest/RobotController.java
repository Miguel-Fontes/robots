package br.com.miguelmf.robots.api.rest;

import br.com.miguelmf.robots.port.nasa.Nasa;
import br.com.miguelmf.robots.port.nasa.data.ComputeRobotCommandRequest;
import br.com.miguelmf.robots.port.nasa.data.ComputeRobotCommandResponse;
import br.com.miguelmf.robots.port.nasa.exception.IllegalCommandException;
import br.com.miguelmf.robots.port.nasa.exception.IllegalPositionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

/**
 * RobotController is REST API for working with Robots
 *
 * @author Miguel Fontes
 */
@RestController
@RequestMapping(value = "/rest/mars", consumes = APPLICATION_JSON_UTF8_VALUE, produces = APPLICATION_JSON_UTF8_VALUE)
public class RobotController {

    private final Nasa api;

    @Autowired
    public RobotController(Nasa api) {
        this.api = api;
    }

    /**
     * Receives a command and executes it on a new Robot at Position (0, 0) and a Zone of Dimension (5, 5), returning
     * it's final position if it is valid. The documented possible exceptions (IllegalPositionException, IllegalCommandException)
     * are handled by {@link RobotExceptionHandler}
     *
     * @param command the command to be executed
     * @return the Robot's final position formatted as (x, y, direction); E.g. (2, 3, W).
     * @throws IllegalPositionException when the computation results on a Robot on a invalid position
     * @throws IllegalCommandException when a invalid command is received at the command String
     */
    @RequestMapping(value = "/{command}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> computeRobotCommands(@PathVariable("command") String command) throws IllegalPositionException, IllegalCommandException {
        ComputeRobotCommandResponse response = api.compute(ComputeRobotCommandRequest.of(command));

        return ResponseEntity
                .status(200)
                .body(response);
    }
}
