package com.DiceGame.Application;

import com.DiceGame.Application.DTO.GameDTO;
import com.DiceGame.Domain.Game;
import com.DiceGame.Domain.Player;
import com.DiceGame.Persistance.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Controller
public class GameController {

    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    PlayerController playerController;

    public GameDTO createGame(int playerId, int diceNumber) throws Exception {
        Player player = playerRepository.getPlayerById(playerId);
        Game game = new Game(diceNumber);
        playerController.addGame(player, game);
        return new GameDTO(game);
    }

    public List<GameDTO> getAllGamesByPlayerId(int playerId) throws Exception {
        List<GameDTO> gameDTOList = new ArrayList<>();
        Player player = playerRepository.getPlayerById(playerId);
        for (Game game : player.getGames()) {
            GameDTO gameDTO = new GameDTO(game);
            gameDTOList.add(gameDTO);
        }
        return gameDTOList;
    }

    public void deleteAllGamesByPlayerId(int playerId) throws Exception {
        Player player = playerRepository.getPlayerById(playerId);
        player.getGames().clear();
        player.setWinRate();
    }
}
