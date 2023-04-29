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

    @GetMapping(value = "/boardstate/")
    public String getCurrentBoardState(@RequestBody String json) {
        return "hello world";
    }

    @GetMapping(value = "/greeting")
    public String getGreeting() {
        System.out.println("hey");
        return "Hello there!";
    }

    @PostMapping(value = "/makemove")
    public boolean makeMove(@RequestBody String json) throws JsonMappingException, JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        HashMap<String, Object> map = objectMapper.readValue(json, HashMap.class);
        System.out.println(map.get("board"));
        System.out.println(map.get("board").getClass());
        System.out.println(((ArrayList<ArrayList<Integer>>) map.get("board")).get(1));
        return true;
    }

    @PostMapping(value = "/testmove")
    public boolean testMove(@RequestBody String json) throws JsonMappingException, JsonProcessingException {
        // JSON parser
        ObjectMapper objectMapper = new ObjectMapper();
        HashMap<String, Object> jsonObject = objectMapper.readValue(json, new TypeReference<HashMap<String, Object>>() {
        });

        // Values for attempting the move
        Board temporaryBoard = new Board();
        temporaryBoard.setBoardState((ArrayList<ArrayList<Integer>>) jsonObject.get("board"));
        int gameId = (int) jsonObject.get("game_id");

        System.out.println(jsonObject.get("board").getClass());
        System.out.println(jsonObject.get("game_id"));

        System.out.println(json);
        temporaryBoard.setBoardState((ArrayList<ArrayList<Integer>>) jsonObject.get("board"));

        // System.out.println(gameController.getGame(gameId).makeMove(temporaryBoard));

        return gameController.getGame(gameId).makeMove(temporaryBoard);
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
    public ResponseEntity<Map<String, Object>> makeGame() {
        // Output Json initialising
        Map<String, Object> responseMap = new HashMap<>();

        int gameId = gameController.createGame();

        responseMap.put("board", gameController.getGame(gameId).getBoard());
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