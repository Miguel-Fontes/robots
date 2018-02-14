package br.com.miguelmf.robots.core;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.function.Function;

import static br.com.miguelmf.robots.core.Direction.WEST;
import static br.com.miguelmf.robots.core.utils.TestUtils.moveNTimes;
import static br.com.miguelmf.robots.core.utils.TestUtils.newRobotAtZeroPositionFacing;
import static br.com.miguelmf.robots.core.utils.TestUtils.newRobotAtZeroPositionFacingNorth;
import static java.util.function.Function.identity;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ZoneTest {

    @Test
    @DisplayName("should create a 5x5 zone")
    void skeletonTest() {
        Zone zone = new Zone(5, 5);
        Robot robot = newRobotAtZeroPositionFacingNorth();

        assertAll(
                () -> assertEquals(new Dimension(5, 5), zone.getDimension())
        );
    }

    @Test
    @DisplayName("should throw an IllegalPositionException when a given Robot has an position out of the Dimension bounds")
    void errorOnInvalidPostion() {
        Dimension dimension = new Dimension(5, 5);
        Zone zone = new Zone(dimension);

        Robot validRobot = moveNTimes(4, newRobotAtZeroPositionFacingNorth());
        Robot limitCaseInvalidRobot = moveNTimes(5, newRobotAtZeroPositionFacingNorth());
        Robot invalidRobot = moveNTimes(6, newRobotAtZeroPositionFacingNorth());
        Robot invalidRobot2 = moveNTimes(1, newRobotAtZeroPositionFacing(WEST));

        assertAll(
                () -> assertTrue(zone.compute(validRobot, identity()).isPresent()),
                () -> assertFalse(zone.compute(limitCaseInvalidRobot, identity()).isPresent()),
                () -> assertFalse(zone.compute(invalidRobot, identity()).isPresent()),
                () -> assertFalse(zone.compute(invalidRobot2, identity()).isPresent())
        );

    }

}