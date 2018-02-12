package br.com.miguelmf.robots.nasa;

import br.com.miguelmf.robots.core.IllegalPositionException;
import br.com.miguelmf.robots.port.nasa.data.ComputeRobotCommandRequest;
import br.com.miguelmf.robots.port.nasa.data.ComputeRobotCommandResponse;
import br.com.miguelmf.robots.port.nasa.exception.IllegalCommandException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class NasaApiTest {

    NasaApi api = new NasaApi();

    @Test
    @DisplayName("should compute a robot final position moving into it's current Direction")
    void shouldComputeARobotFinalPosition() throws IllegalPositionException {
        ComputeRobotCommandResponse oneStepNorth = api.compute(new ComputeRobotCommandRequest("M"));
        ComputeRobotCommandResponse twoStepsNorth = api.compute(new ComputeRobotCommandRequest("MM"));
        ComputeRobotCommandResponse threeStepsNorth = api.compute(new ComputeRobotCommandRequest("MMM"));

        assertEquals("(0, 1, N)", oneStepNorth.getRobotFinalPosition());
        assertEquals("(0, 2, N)", twoStepsNorth.getRobotFinalPosition());
        assertEquals("(0, 3, N)", threeStepsNorth.getRobotFinalPosition());
    }

    @Test
    @DisplayName("should turn a robot right and move")
    void shouldTurnARobotRightAndMove() throws IllegalPositionException {
        ComputeRobotCommandResponse oneStepWest = api.compute(new ComputeRobotCommandRequest("RM"));
        ComputeRobotCommandResponse twoStepsWest = api.compute(new ComputeRobotCommandRequest("RMM"));
        ComputeRobotCommandResponse threeStepsWest = api.compute(new ComputeRobotCommandRequest("RMMM"));

        assertEquals("(1, 0, E)", oneStepWest.getRobotFinalPosition());
        assertEquals("(2, 0, E)", twoStepsWest.getRobotFinalPosition());
        assertEquals("(3, 0, E)", threeStepsWest.getRobotFinalPosition());
    }

    @Test
    @DisplayName("should turn a robot left and move out of bounds")
    void shouldTurnARobotLeftAndMoveOutOfBounds() {
        assertThrows(IllegalPositionException.class, () -> api.compute(new ComputeRobotCommandRequest("LM")));
        assertThrows(IllegalPositionException.class, () -> api.compute(new ComputeRobotCommandRequest("LMM")));
        assertThrows(IllegalPositionException.class, () -> api.compute(new ComputeRobotCommandRequest("LMMM")));
    }

    @Test
    @DisplayName("should walk six steps north going outside of bounds")
    void shouldWalkNorthOutsideOfBounds() throws IllegalPositionException {
        ComputeRobotCommandResponse oneStepNorth = api.compute(new ComputeRobotCommandRequest("M"));
        ComputeRobotCommandResponse fourStepsNorth = api.compute(new ComputeRobotCommandRequest("MMMM"));

        assertEquals("(0, 1, N)", oneStepNorth.getRobotFinalPosition());
        assertEquals("(0, 4, N)", fourStepsNorth.getRobotFinalPosition());

        assertThrows(IllegalPositionException.class, () -> api.compute(new ComputeRobotCommandRequest("MMMMM")));
    }

    @Test
    @DisplayName("should throw an Exceptions for an Invalid Command")
    void shouldThrowAnExceptionForAInvalidCommand() {
        assertThrows(IllegalCommandException.class, () -> api.compute(new ComputeRobotCommandRequest("A")));
        assertThrows(IllegalCommandException.class, () -> api.compute(new ComputeRobotCommandRequest("1")));
        assertThrows(IllegalCommandException.class, () -> api.compute(new ComputeRobotCommandRequest("C")));
        assertThrows(IllegalCommandException.class, () -> api.compute(new ComputeRobotCommandRequest("MMMDAA")));
        assertThrows(IllegalCommandException.class, () -> api.compute(new ComputeRobotCommandRequest("CMMMDAA")));
        assertThrows(IllegalCommandException.class, () -> api.compute(new ComputeRobotCommandRequest("MMAMM")));

    }
}