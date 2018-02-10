package br.com.miguelmf.robots.core;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static br.com.miguelmf.robots.core.utils.TestUtils.newRobotAtZeroPositionFacingNorth;
import static org.junit.jupiter.api.Assertions.*;

class ZoneTest {

    @Test
    @DisplayName("should create a 5x5 zone with a robot at 0,0 position, facing NORTH")
    void skeletonTest() {
        Zone zone = newZoneWithDimension(5, 5);
        Robot robot = newRobotAtZeroPositionFacingNorth();

        zone.addRobot(robot);
        Optional<Robot> maybeRobot = zone.getRobotAtPosition(getZeroPosition());

        assertEquals(new Dimension(5,5), zone.getDimension());
        assertTrue(maybeRobot.isPresent(), String.format("Robot not found on position [%s]!", getZeroPosition()));
        assertEquals(robot, maybeRobot.get());
    }


    private Zone newZoneWithDimension(Integer length, Integer height) {
        return new Zone(length, height);
    }

    public Position getZeroPosition() {
        return new Position(0, 0);
    }

}