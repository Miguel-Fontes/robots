package br.com.miguelmf;

import br.com.miguelmf.robots.core.Zone;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AppTest {

    @Test
    @DisplayName("should create a 5x5 zone with a robot at 0,0 position, facing NORTH")
    void firstTest() {
        Zone zone = new Zone(5, 5);
    }
}