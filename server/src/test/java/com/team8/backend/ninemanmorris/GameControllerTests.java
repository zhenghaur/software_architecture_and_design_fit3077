package com.team8.backend.ninemanmorris;

import static org.mockito.ArgumentMatchers.contains;

import org.assertj.core.api.Assert;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class GameControllerTests {

    @Test
    public void testGameController() {
        GameController controller = new GameController();
        Assertions.assertThat(controller.getId()).isEqualTo(10);
    }

}
