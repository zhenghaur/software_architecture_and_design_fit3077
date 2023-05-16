package com.team8.backend;

import java.io.Console;
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

    /**
     * 
     * @param json
     * @return
     * @throws JsonMappingException
     * @throws JsonProcessingException
     */
    @PostMapping(value = "/makeplace")
    public boolean makePlace(@RequestBody String json) throws JsonMappingException, JsonProcessingException {
        return true;
    }

    /**
     * 
     * @param json
     * @return
     * @throws JsonMappingException
     * @throws JsonProcessingException
     */
    @PostMapping(value = "/makemove")
    public boolean makeMove(@RequestBody String json) throws JsonMappingException, JsonProcessingException {
        // JSON parser
        ObjectMapper objectMapper = new ObjectMapper();
        HashMap<String, Integer> jsonObject = objectMapper.readValue(json,
                new TypeReference<HashMap<String, Integer>>() {
                });

        int original_row = (int) jsonObject.get("original_row");
        int original_col = (int) jsonObject.get("original_col");
        int movement_row = (int) jsonObject.get("movement_row");
        int movement_col = (int) jsonObject.get("movement_col");
        int gameId = (int) jsonObject.get("game_id");
        System.out.println(json);
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
    public ResponseEntity<Map<String, Object>> initGame() throws JsonMappingException, JsonProcessingException {
        // Output Json initialising
        Map<String, Object> responseMap = new HashMap<>();

        int gameId = gameController.createGame();

        responseMap.put("board", gameController.getGame(gameId).getBoard().getBoardStateInt());
        responseMap.put("game_id", gameId);
        return ResponseEntity.ok(responseMap);
    }

    /**
     * Initialising a game from the games id, this is for accessing past games, and
     * loading games.
     * 
     * @param json
     * @return
     * @throws JsonMappingException
     * @throws JsonProcessingException
     */
    @CrossOrigin
    @PostMapping(value = "/initfromid")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> initFromId(@RequestBody String json)
            throws JsonMappingException, JsonProcessingException {
        // Output Json initialising
        Map<String, Object> responseMap = new HashMap<>();
        // JSON parser
        ObjectMapper objectMapper = new ObjectMapper();
        HashMap<String, Object> jsonObject = objectMapper.readValue(json, new TypeReference<HashMap<String, Object>>() {
        });

        int gameId = (int) jsonObject.get("game_id");

        responseMap.put("board", gameController.getGame(gameId).getBoard().getBoardStateInt());
        responseMap.put("player", gameController.getGame(gameId).getCurrPlayer().getPlayerToken().getToken());
        responseMap.put("phase", gameController.getGame(gameId).getCurrPlayer().getMovementPhase().getPhase());
        return ResponseEntity.ok(responseMap);
    }

    @CrossOrigin
    @PostMapping(value = "/initplayerdata")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> initPlayerData(@RequestBody String json)
            throws JsonMappingException, JsonProcessingException {
        // Output Json initialising
        Map<String, Object> responseMap = new HashMap<>();
        // JSON parser
        ObjectMapper objectMapper = new ObjectMapper();
        HashMap<String, Object> jsonObject = objectMapper.readValue(json, new TypeReference<HashMap<String, Object>>() {
        });

        int gameId = (int) jsonObject.get("game_id");

        responseMap.put("player_one_name", gameController.getGame(gameId).getPlayerOne().getPlayerName());
        responseMap.put("player_two_name", gameController.getGame(gameId).getPlayerTwo().getPlayerName());
        responseMap.put("player_one_token_count", gameController.getGame(gameId).getPlayerOne().getNumTokens());
        responseMap.put("player_two_token_count", gameController.getGame(gameId).getPlayerTwo().getNumTokens());
        responseMap.put("player_one_token_storage",
                gameController.getGame(gameId).getPlayerOne().getNumStorageTokens());
        responseMap.put("player_two_token_storage",
                gameController.getGame(gameId).getPlayerTwo().getNumStorageTokens());
        System.out.println(json);
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