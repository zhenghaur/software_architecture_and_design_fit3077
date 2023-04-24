package com.team8.backend;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.team8.backend.ninemanmorris.GameController;

@Configuration
public class ApplicationConfiguration {
    @Bean
    public GameController gameControl() {
        return new GameController();
    }
}
