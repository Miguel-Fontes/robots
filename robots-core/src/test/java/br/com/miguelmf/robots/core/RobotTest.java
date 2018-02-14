package br.com.miguelmf.robots.core;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static br.com.miguelmf.robots.core.utils.TestUtils.newRobotAtZeroPositionFacing;
import static br.com.miguelmf.robots.core.utils.TestUtils.newRobotAtZeroPositionFacingNorth;
import static br.com.miguelmf.robots.core.Direction.EAST;
import static br.com.miguelmf.robots.core.Direction.NORTH;
import static br.com.miguelmf.robots.core.Direction.SOUTH;
import static br.com.miguelmf.robots.core.Direction.WEST;
import static br.com.miguelmf.robots.core.RobotTest.Side.LEFT;
import static br.com.miguelmf.robots.core.RobotTest.Side.RIGHT;
import static org.junit.jupiter.api.Assertions.assertEquals;

class RobotTest {

    @Test
    @DisplayName("should turn a robot orientation to the Left")
    void turnLeft() {
        Robot robot = newRobotAtZeroPositionFacingNorth();

        assertEquals(WEST, turnNTimes(LEFT, 1, robot).getDirection());
        assertEquals(Direction.SOUTH, turnNTimes(LEFT, 2, robot).getDirection());
        assertEquals(Direction.EAST, turnNTimes(LEFT, 3, robot).getDirection());
        assertEquals(Direction.NORTH, turnNTimes(LEFT, 4, robot).getDirection());
    }

    @Test
    @DisplayName("should turn a robot orientation to the right")
    void turnRight() {
        Robot robot = newRobotAtZeroPositionFacingNorth();

        assertEquals(Direction.EAST, turnNTimes(RIGHT, 1, robot).getDirection());
        assertEquals(Direction.SOUTH, turnNTimes(RIGHT, 2, robot).getDirection());
        assertEquals(WEST, turnNTimes(RIGHT, 3, robot).getDirection());
        assertEquals(Direction.NORTH, turnNTimes(RIGHT, 4, robot).getDirection());
    }

    @Test
    @DisplayName("should compute directions when mixing left and right turns")
    void mixedTurns() {
        Robot robot = newRobotAtZeroPositionFacingNorth();
        Robot  eastRobot = robot // NORTH
                .turnLeft()      // WEST
                .turnLeft()      // SOUTH
                .turnLeft()      // EAST
                .turnRight()     // SOUTH
                .turnLeft()      // EAST
                .turnRight()     // SOUTH
                .turnRight()     // WEST
                .turnRight()     // NORTH
                .turnLeft()      // WEST
                .turnLeft()      // SOUTH
                .turnLeft();     // EAST;

        assertEquals(Direction.EAST, eastRobot.getDirection());
    }

    @Test
    @DisplayName("should compute a new direction based on the robot facing direction")
    void shouldComputeANewPosition() {
        Robot robotNorth = newRobotAtZeroPositionFacing(NORTH);
        Robot robotWest = newRobotAtZeroPositionFacing(WEST);
        Robot robotSouth = newRobotAtZeroPositionFacing(SOUTH);
        Robot robotEast = newRobotAtZeroPositionFacing(EAST);

        assertEquals(
                new Position(0, 1),
                robotNorth.move().getPosition());

        assertEquals(
                new Position(-1, 0),
                robotWest.move().getPosition());

        assertEquals(
                new Position(0, -1),
                robotSouth.move().getPosition());

        assertEquals(
                new Position(1, 0),
                robotEast.move().getPosition());
    }

    private Robot turnNTimes(Side side, int times, Robot robot) {
        for (int i = 0; i < times; i++)
            robot = side.equals(LEFT)
                    ? robot.turnLeft()
                    : robot.turnRight();

        return robot;
    }

    enum Side {
        LEFT, RIGHT
    }

}