package br.com.miguelmf.robots.core;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static br.com.miguelmf.robots.core.utils.TestUtils.moveNTimes;
import static br.com.miguelmf.robots.core.utils.TestUtils.newRobotAtZeroPositionFacingNorth;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ZoneTest {

    @Test
    @DisplayName("should create a 5x5 zone with a robot at 0,0 position, facing NORTH")
    void skeletonTest() throws IllegalPositionException {
        Zone zone = new Zone(5, 5);
        Robot robot = newRobotAtZeroPositionFacingNorth();

        zone.addRobot(robot);
        Optional<Robot> maybeRobot = zone.getRobotAtPosition(getZeroPosition());

        assertTrue(maybeRobot.isPresent(), String.format("Robot not found on position [%s]!", getZeroPosition()));
        assertAll(
                () -> assertEquals(new Dimension(5, 5), zone.getDimension()),
                () -> assertEquals(robot, maybeRobot.get())
        );
    }

    @Test
    @DisplayName("should create a 5x5 zone with a robot at 0,0 position, facing NORTH")
    void shouldUpdateRobotPosition() throws IllegalPositionException {
        Position updatedPosition = new Position(0, 2);
        Zone zone = new Zone(5, 5);
        Robot robot = newRobotAtZeroPositionFacingNorth();

        zone.addRobot(robot);
        Robot updatedRobot = robot.move().move();
        zone.addRobot(updatedRobot);
        Optional<Robot> noRobotAtPositionZero = zone.getRobotAtPosition(getZeroPosition());
        Optional<Robot> updatedRobotTwoPointsNorth = zone.getRobotAtPosition(updatedPosition);

        assertAll(
                () -> assertTrue(!noRobotAtPositionZero.isPresent(),
                        String.format("Updated Robot wasn't removed from position [%s]!", getZeroPosition())),

                () -> assertTrue(updatedRobotTwoPointsNorth.isPresent(),
                        String.format("Robot not found on position [%s]!", updatedPosition))
        );

    }

    @Test
    @DisplayName("should throw an IllegalPositionException when a given Robot has an position out of the Dimension bounds")
    void errorOnInvalidPostion() throws IllegalPositionException {
        Position invalidPosition1= new Position(0,5);
        Position invalidPosition2= new Position(0,6);
        Dimension dimension = new Dimension(5,5);
        Zone zone = new Zone(dimension);

        Robot validRobot = moveNTimes(4, newRobotAtZeroPositionFacingNorth());
        Robot limitCaseInvalidRobot = moveNTimes(5, newRobotAtZeroPositionFacingNorth());
        Robot invalidRobot = moveNTimes(6, newRobotAtZeroPositionFacingNorth());

        zone.addRobot(validRobot);

        Throwable exception = assertThrows(IllegalPositionException.class,
                () -> zone.addRobot(limitCaseInvalidRobot));

        Throwable exception2 = assertThrows(IllegalPositionException.class,
                () -> zone.addRobot(invalidRobot));


        assertAll(
                () -> assertTrue(exception.getMessage().startsWith(String.format("Robot's position [%s] is out of the Zone Bounds [%s]",
                        invalidPosition1.toString(), dimension.toString()))),

                () -> assertTrue(exception2.getMessage().startsWith(String.format("Robot's position [%s] is out of the Zone Bounds [%s]",
                        invalidPosition2.toString(), dimension.toString())))
        );

    }
    
    private Position getZeroPosition() {
        return new Position(0, 0);
    }

}