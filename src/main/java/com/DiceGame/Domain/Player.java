package com.DiceGame.Domain;

import com.DiceGame.Application.DTO.PlayerDTO;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private int id;
    private String name;
    private List<Game> games;
    private double winRate;
    private static final String ANONYMOUS="Anonimo";
    private static int COUNTER=1;

    public Player(){}

    public Player(PlayerDTO player) throws Exception {
        if (player == null || player.getName() == null)
            throw new Exception();
        if (!player.getName().equals("") && !player.getName().equalsIgnoreCase(ANONYMOUS)) {
            this.name = player.getName();
        }else{
            this.name = ANONYMOUS;
        }

        this.id = COUNTER;
        this.games = new ArrayList<>();
        this.winRate = 0;
        ++COUNTER;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Game> getGames() { return games; }

    public void setGames(List<Game> games) {
        this.games = games;
    }

    public double getWinRate() { return winRate; }

    public double setWinRate() {
        int winCounter=0;
        if (!games.isEmpty()) {
            for (Game game : games) {
                if (game.isWin())
                    winCounter = winCounter + 1;
            }
            return winRate = ((double)winCounter*100)/(double)games.size();
        }else {
            return winRate = 0;
        }
    }
}