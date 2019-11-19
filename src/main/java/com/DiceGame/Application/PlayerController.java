package com.DiceGame.Application;

import com.DiceGame.Application.DTO.PlayerDTO;
import com.DiceGame.Domain.Game;
import com.DiceGame.Domain.Player;
import com.DiceGame.Persistance.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PlayerController {

    @Autowired
    private PlayerRepository playerRepository;

    public PlayerDTO createPlayer(PlayerDTO playerDTO) throws Exception {
        Player player = new Player(playerDTO);
        playerRepository.savePlayer(player);
        return new PlayerDTO(player);
    }

    public void addGame(Player player, Game game){
        player.getGames().add(game);
        player.setWinRate();
    }

    public List<PlayerDTO> getAllPlayers() throws Exception {
        List<PlayerDTO> playerDTOList = new ArrayList<>();
        for (Player player : playerRepository.getAllPlayers()) {
            PlayerDTO playerDTO = new PlayerDTO(player);
            playerDTOList.add(playerDTO);
        }
        return playerDTOList;
    }

    public PlayerDTO getRankingWinner() throws Exception {
        Player player = playerRepository.getPlayerById(1);
        for (Player player1 : playerRepository.getAllPlayers()) {
            if (player.getWinRate() < player1.getWinRate())
                player = player1;
        }
        return new PlayerDTO(player);
    }

    public PlayerDTO getRankingLoser() throws Exception {
        Player player = playerRepository.getPlayerById(1);
        for (Player player1 : playerRepository.getAllPlayers()) {
            if (player.getWinRate() > player1.getWinRate())
                player = player1;
        }
        return new PlayerDTO(player);
    }

    public double getRanking(){
        double totalWinRate = 0;
        for (Player player : playerRepository.getAllPlayers()) {
            totalWinRate = player.getWinRate()+totalWinRate;
        }
        return totalWinRate/playerRepository.getAllPlayers().size();
    }

    public PlayerDTO editPlayer(int playerId, PlayerDTO playerToEdit) throws Exception {
        Player player = playerRepository.getPlayerById(playerId);
        if (!playerRepository.playerExistByName(playerToEdit.getName())) {
            player.setName(playerToEdit.getName());
        }else{
            throw new Exception();
        }
        return new PlayerDTO(player);
    }

    public void deletePlayer(int playerId) throws Exception {
        playerRepository.deletePlayerById(playerId);
    }
}
