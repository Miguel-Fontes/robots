package br.com.miguelmf.robots.core.utils;

import br.com.miguelmf.robots.core.Direction;
import br.com.miguelmf.robots.core.Position;
import br.com.miguelmf.robots.core.Robot;

public class TestUtils {

    public static Robot newRobotAtZeroPositionFacingNorth() {
        return new Robot(0, Direction.NORTH, new Position(0, 0));
    }

    public static Robot newRobotAtZeroPositionFacing(Direction direction) {
        return new Robot(0, direction, new Position(0, 0));
    }

    public static Robot moveNTimes(int times, Robot robot) {
        for (int i = 0; i < times; i++) {
            robot = robot.move();
        }

        return robot;
    }



}
