package com.DiceGame.API;

import com.DiceGame.Application.DTO.PlayerDTO;
import com.DiceGame.Application.PlayerController;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PlayerRestController {

    @Autowired
    PlayerController controller;

    @PostMapping(value = "/players", produces = "application/json;charset=UTF-8")
    public String registerPlayer(@RequestBody String jPlayer) throws Exception {

        PlayerDTO newPlayer = new Gson().fromJson(jPlayer, PlayerDTO.class);

        PlayerDTO player = controller.createPlayer(newPlayer);

        return toJson(player);
    }


    @GetMapping(value = "/players", produces = "application/json;charset=UTF-8")
    public String getPlayerList() throws Exception {

        List<PlayerDTO> players = controller.getAllPlayers();

        return toJson(players);
    }


    @GetMapping(value = "/players/ranking", produces = "application/json;charset=UTF-8")
    public String getPlayerRanking() throws Exception {

        double ranking = controller.getRanking();

        return toJson(ranking);

    }


    @GetMapping(value = "/players/ranking/winner", produces = "application/json;charset=UTF-8")
    public String getRankingWinner() throws Exception {

        PlayerDTO winner = controller.getRankingWinner();

        return toJson(winner);

    }


    @GetMapping(value = "/players/ranking/loser", produces = "application/json;charset=UTF-8")
    public String getRankingLoser() throws Exception {

        PlayerDTO loser = controller.getRankingLoser();

        return toJson(loser);

    }

    @PutMapping(value = "/players/{playerId}", produces = "application/json;charset=UTF-8")
    public String editPlayerName(@RequestBody String jPlayerToEdit, @PathVariable int playerId) throws Exception {

        PlayerDTO editedPlayer = new Gson().fromJson(jPlayerToEdit, PlayerDTO.class);

        PlayerDTO playerToEdit = controller.editPlayer(playerId, editedPlayer);

        return toJson(playerToEdit);

    }

    @DeleteMapping(value = "/players/{playerId}", produces = "application/json;charset=UTF-8")
    public void deletePLayer(@PathVariable int playerId) throws Exception {
        controller.deletePlayer(playerId);
    }

    private String toJson(Object object){

        Gson gson=new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        return gson.toJson(object);
    }
}
