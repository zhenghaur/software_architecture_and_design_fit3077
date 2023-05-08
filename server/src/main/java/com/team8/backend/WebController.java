package com.team8.backend;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.team8.backend.ninemanmorris.Board;
import com.team8.backend.ninemanmorris.GameController;

@SuppressWarnings("unchecked")
@CrossOrigin(origins = { "*" })
@RestController
public class WebController {

    // This is the Factory Method for GameController
    @Autowired
    private GameController gameController;

    // API testing
    @GetMapping(value = "/greeting")
    public String getGreeting() {
        System.out.println("hey");
        return "Hello there!";
    }

    @PostMapping(value = "/makemove")
    public boolean testMove(@RequestBody String json) throws JsonMappingException, JsonProcessingException {
        // JSON parser
        ObjectMapper objectMapper = new ObjectMapper();
        HashMap<String, Object> jsonObject = objectMapper.readValue(json, new TypeReference<HashMap<String, Object>>() {
        });

        int original_row = (int) jsonObject.get("original_row");
        int original_col = (int) jsonObject.get("original_col");
        int movement_row = (int) jsonObject.get("movement_row");
        int movement_col = (int) jsonObject.get("movement_col");
        int gameId = (int) jsonObject.get("game_id");

        return gameController.getGame(gameId).makeMove(original_row, original_col, movement_row, movement_col);
    }

    /***
     * For initialising and creating a game instance,
     * returns a json output of
     * {
     * board: boardstate of the newly created board
     * gameId: id of the newly created game
     * }
     * 
     * @return
     */
    @CrossOrigin
    @GetMapping(value = "/initgame")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> initGame() {
        // Output Json initialising
        Map<String, Object> responseMap = new HashMap<>();

        int gameId = gameController.createGame();

        responseMap.put("board", gameController.getGame(gameId).getBoard().getBoardStateInt());
        responseMap.put("game_id", gameId);
        return ResponseEntity.ok(responseMap);
    }
}

// EXAMPLES DO NO TOUCH
// @GetMapping(value = "/boardstate/{gameId}")
// public String getCurrentBoardState(@RequestParam("gameId") int gameId) {
// return "{}";
// }

// example = {"game-board" : {[1,1,1,1]}}

// @GetMapping(value = "/boardstate/", consumes = {
// MediaType.APPLICATION_JSON_VALUE })
// public int getCurrentBoardState(@RequestBody String json) {

// return this.gameController.getId();
// }

// @CrossOrigin
// @GetMapping(value = "/boardstate/")
// public String getCurrentBoardState(@RequestBody String json) {
// return "hello";
// }