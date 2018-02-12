package br.com.miguelmf.robots.api.rest;

import br.com.miguelmf.robots.core.IllegalPositionException;
import br.com.miguelmf.robots.port.nasa.Nasa;
import br.com.miguelmf.robots.port.nasa.data.ComputeRobotCommandRequest;
import br.com.miguelmf.robots.port.nasa.data.ComputeRobotCommandResponse;
import br.com.miguelmf.robots.port.nasa.exception.IllegalCommandException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@RestController
@RequestMapping(value = "/rest/mars", consumes = APPLICATION_JSON_UTF8_VALUE, produces = APPLICATION_JSON_UTF8_VALUE)
public class RobotController {

    private final Nasa api;

    @Autowired
    public RobotController(Nasa api) {
        this.api = api;
    }

    @RequestMapping(value = "/{command}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> computeRobotCommands(@PathVariable("command") String command) {
        try {
            ComputeRobotCommandResponse response = api.compute(new ComputeRobotCommandRequest(command));

            return ResponseEntity
                    .status(200)
                    .body(response);

        } catch (IllegalPositionException | IllegalCommandException e) {
            return ResponseEntity
                    .status(400)
                    .body(e.getMessage());
        }
    }
}
