package com.team8.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.team8.backend.ninemanmorris.GameController;

@RestController
public class WebController {

    @Autowired
    private GameController gameController;

    // @GetMapping(value = "/boardstate/{gameId}")
    // public String getCurrentBoardState(@RequestParam("gameId") int gameId) {
    // return "{}";
    // }

    // example = {"game-board" : {[1,1,1,1]}}

    @GetMapping(value = "/boardstate/", consumes = { MediaType.APPLICATION_JSON_VALUE })
    public int getCurrentBoardState(@RequestBody String json) {

        return this.gameController.getId();
    }

}