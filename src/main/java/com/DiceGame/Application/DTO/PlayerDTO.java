package com.DiceGame.Application.DTO;

import com.DiceGame.Domain.Game;
import com.DiceGame.Domain.Player;
import com.google.gson.annotations.Expose;

import java.util.List;

public class PlayerDTO {

    @Expose
    private int id;
    @Expose
    private String name;
    private List<Game> games;
    @Expose
    private double winRate;

    public PlayerDTO(){}

    public PlayerDTO(Player player) throws Exception {
        if (player == null)
            throw new Exception();

        this.id = player.getId();
        this.name = player.getName();
        this.games = player.getGames();
        this.winRate = player.getWinRate();
    }

    public int getId() {
        return id;
    }

    public String getName() throws Exception {
        if (name == null)
            throw new Exception();
        return name;
    }

    public List<Game> getGames() {
        return games;
    }

    public double getWinRate() {
        return winRate;
    }
}
