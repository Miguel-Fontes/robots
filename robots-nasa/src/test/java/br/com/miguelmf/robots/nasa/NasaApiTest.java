package br.com.miguelmf.robots.nasa;

import br.com.miguelmf.robots.port.nasa.data.ComputeRobotCommandRequest;
import br.com.miguelmf.robots.port.nasa.data.ComputeRobotCommandResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NasaApiTest {

    NasaApi api = new NasaApi();

    @Test
    @DisplayName("should compute a robot final position moving into it's current Direction")
    void shouldComputeARobotFinalPosition() {
        ComputeRobotCommandResponse oneStepNorth = api.compute(new ComputeRobotCommandRequest("M"));
        ComputeRobotCommandResponse twoStepsNorth = api.compute(new ComputeRobotCommandRequest("MM"));
        ComputeRobotCommandResponse threeStepsNorth= api.compute(new ComputeRobotCommandRequest("MMM"));

        assertEquals("(0, 1, N)", oneStepNorth.getRobotFinalPosition());
        assertEquals("(0, 2, N)", twoStepsNorth.getRobotFinalPosition());
        assertEquals("(0, 3, N)", threeStepsNorth.getRobotFinalPosition());
    }

    @Test
    @DisplayName("should turn a robot right and move")
    void shouldTurnARobotRightAndMove() {
        ComputeRobotCommandResponse oneStepWest = api.compute(new ComputeRobotCommandRequest("RM"));
        ComputeRobotCommandResponse twoStepsWest = api.compute(new ComputeRobotCommandRequest("RMM"));
        ComputeRobotCommandResponse threeStepsWest= api.compute(new ComputeRobotCommandRequest("RMMM"));

        assertEquals("(1, 0, E)", oneStepWest.getRobotFinalPosition());
        assertEquals("(2, 0, E)", twoStepsWest.getRobotFinalPosition());
        assertEquals("(3, 0, E)", threeStepsWest.getRobotFinalPosition());
    }

    @Test
    @DisplayName("should turn a robot left and move")
    void shouldTurnARobotLeftAndMove() {
        ComputeRobotCommandResponse oneStepEast = api.compute(new ComputeRobotCommandRequest("LM"));
        ComputeRobotCommandResponse twoStepsEast = api.compute(new ComputeRobotCommandRequest("LMM"));
        ComputeRobotCommandResponse threeStepsEast= api.compute(new ComputeRobotCommandRequest("LMMM"));

        assertEquals("(-1, 0, W)", oneStepEast.getRobotFinalPosition());
        assertEquals("(-2, 0, W)", twoStepsEast.getRobotFinalPosition());
        assertEquals("(-3, 0, W)", threeStepsEast.getRobotFinalPosition());
    }
}