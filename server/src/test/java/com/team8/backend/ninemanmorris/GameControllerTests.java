package com.team8.backend.ninemanmorris;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class GameControllerTests {

    private static GameController controller;

    // Setup state before any tests run
    @BeforeAll
    public static void init() {
        controller = new GameController();
    }

    // Cleanup state after all tests run
    @AfterAll
    public static void cleanup() {
        controller = null;
    }

    @Test
    public void testGameController() {
        // Assertions.assertThat(controller.getId()).isEqualTo(10);
    }

}
