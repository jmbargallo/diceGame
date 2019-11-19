package com.DiceGame.API;

import com.DiceGame.Application.DTO.GameDTO;
import com.DiceGame.Application.GameController;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.google.gson.JsonSyntaxException;

import java.util.List;

@RestController
public class GameRestController {

    @Autowired
    GameController controller;

    @PostMapping(value = "/players/{playerId}/games", produces = "application/json;charset=UTF-8")
    public String playerPlays(@PathVariable int playerId, @RequestBody JSONObject json) throws Exception, JsonSyntaxException {

        int diceNumber = (int)json.get("diceNumber");

        GameDTO newGame = controller.createGame(playerId, diceNumber);

        return toJson(newGame);
    }


    @GetMapping(value = "/players/{playerId}/games", produces = "application/json;charset=UTF-8")
    public String getPlayerGameList(@PathVariable int playerId) throws Exception {

        List<GameDTO> games = controller.getAllGamesByPlayerId(playerId);

        return toJson(games);
    }

    private String toJson(Object object){

        Gson gson=new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        return gson.toJson(object);
    }

    @DeleteMapping(value = "players/{playerId}/games", produces = "application/json;charset=UTF-8")
    public void deleteAllPlayerThrows(@PathVariable int playerId) throws Exception {
        controller.deleteAllGamesByPlayerId(playerId);
    }
}
