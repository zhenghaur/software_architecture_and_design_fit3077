package com.team8.backend;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Console;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

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
import com.team8.backend.ninemanmorris.GameController;
import com.team8.backend.ninemanmorris.game.Board;

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
        return "Hello there! Latest";
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
        this.gameController.getBoard(gameId).printBoard();
        return gameController.getGame(gameId).makeMove(original_row, original_col, movement_row, movement_col);
    }

    @PostMapping(value = "/getmovestack")
    public ResponseEntity<Map<String, Object>> getMoveStack(@RequestBody String json)
            throws JsonMappingException, JsonProcessingException {
        // Output Json initialising
        Map<String, Object> responseMap = new HashMap<>();

        // JSON parser
        ObjectMapper objectMapper = new ObjectMapper();
        HashMap<String, String> jsonObject = objectMapper.readValue(json,
                new TypeReference<HashMap<String, String>>() {
                });

        int gameId = Integer.parseInt(jsonObject.get("game_id"));

        // Obtaining the move stack
        Stack moveStack = gameController.getGame(gameId).getMoveStack();

        // Serializing the move stack
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        try (ObjectOutputStream objectOut = new ObjectOutputStream(byteStream)) {
            objectOut.writeObject(moveStack);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Get the serialized bytes
        byte[] serializedBytes = byteStream.toByteArray();

        // Put the serialized stack into the response map
        responseMap.put("serialized_stack", serializedBytes);

        return ResponseEntity.ok(responseMap);
    }

    @PostMapping(value = "/uploadstate")
    public boolean uploadstate(@RequestBody String json) throws JsonMappingException, JsonProcessingException {
        // JSON parser
        ObjectMapper objectMapper = new ObjectMapper();
        HashMap<String, String> jsonObject = objectMapper.readValue(json,
                new TypeReference<HashMap<String, String>>() {
                });

        int gameId = Integer.parseInt(jsonObject.get("game_id"));
        int playerOneTokensLeft = Integer.parseInt(jsonObject.get("player_one_token_left"));
        int playerTwoTokensLeft = Integer.parseInt(jsonObject.get("player_two_token_left"));
        int playerOneTokensStorage = Integer.parseInt(jsonObject.get("player_one_token_storage"));
        int playerTwoTokensStorage = Integer.parseInt(jsonObject.get("player_two_token_storage"));
        boolean gameOver = Boolean.parseBoolean(jsonObject.get("game_over"));
        int playerTurn = Integer.parseInt(jsonObject.get("player_turn"));
        int playerPhase = Integer.parseInt(jsonObject.get("player_phase"));
        String boardStateString = jsonObject.get("board_state");
        String serealizedStackString = jsonObject.get("serialized_stack");

        // Retrieve the serialized stack bytes from the response map
        byte[] serializedBytes = Base64.getDecoder().decode(serealizedStackString);

        // Deserialize the stack
        Stack moveStack = null;
        try (ObjectInputStream objectIn = new ObjectInputStream(new ByteArrayInputStream(serializedBytes))) {
            moveStack = (Stack) objectIn.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        // Converting boardState
        String[] rows = boardStateString.split("\\],\\[");

        int rowCount = rows.length;
        int columnCount = rows[0].split(",").length;

        int[][] boardState = new int[rowCount][columnCount];

        for (int i = 0; i < rowCount; i++) {
            String[] elements = rows[i].replaceAll("\\[|\\]", "").split(",");
            for (int j = 0; j < columnCount; j++) {
                boardState[i][j] = Integer.parseInt(elements[j]);
            }
        }

        System.out.println("Board State Array-----");
        for (int[] row : boardState) {
            for (int i : row) {
                System.out.print(String.valueOf(i) + " ");
            }
            System.out.println(" ");
        }
        System.out.println("Board State Array-----");

        System.out.println("Game ID: " + gameId);
        System.out.println("Player One Tokens Left: " + playerOneTokensLeft);
        System.out.println("Player Two Tokens Left: " + playerTwoTokensLeft);
        System.out.println("Player One Tokens Storage: " + playerOneTokensStorage);
        System.out.println("Player Two Tokens Storage: " + playerTwoTokensStorage);
        System.out.println("Game Over: " + gameOver);
        System.out.println("Player Turn: " + playerTurn);
        System.out.println("Player Phase: " + playerPhase);
        System.out.println("serealizedStack: " + serealizedStackString);

        return gameController.getGame(gameId).setState(playerOneTokensLeft, playerTwoTokensLeft, playerOneTokensStorage,
                playerTwoTokensStorage, gameOver, playerTurn, playerPhase, boardState, moveStack);
    }

    /***
     * For undoing moves
     * 
     * @param json containing id of the game
     * @return boolean value on if the undo was successful
     * @throws JsonMappingException
     * @throws JsonProcessingException
     */
    @CrossOrigin
    @PostMapping(value = "/undomove")
    @ResponseBody
    public boolean undoMove(@RequestBody String json)
            throws JsonMappingException, JsonProcessingException {
        // JSON parser
        ObjectMapper objectMapper = new ObjectMapper();
        HashMap<String, Integer> jsonObject = objectMapper.readValue(json,
                new TypeReference<HashMap<String, Integer>>() {
                });

        int gameId = (int) jsonObject.get("game_id");

        // Returns if the undoMove is successful
        return gameController.getGame(gameId).undoMove();
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
        responseMap.put("game_status", gameController.getGame(gameId).getGameStatus());
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