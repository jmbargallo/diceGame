package com.DiceGame.Application.DTO;

import com.DiceGame.Domain.Dice;
import com.DiceGame.Domain.Game;
import com.google.gson.annotations.Expose;

import java.util.List;

public class GameDTO {

    @Expose
    private int id;
    private List<Dice> diceList;
    @Expose
    private boolean win;
    private List<Integer> diceResults;
    @Expose
    private String diceResultsExpose;

    public GameDTO(){}

    public GameDTO(Game game) throws Exception {
        if (game == null)
            throw new Exception();

        this.id = game.getId();
        this.diceList = game.getDiceList();
        this.win = game.isWin();
        this.diceResults = game.getDiceResults();
        this.diceResultsExpose = game.getDiceResults().toString();
    }

    public List<Dice> getDiceList() {
        return diceList;
    }

    public boolean isWin() {
        return win;
    }

    public List<Integer> getDiceResults() {
        return diceResults;
    }

    public String getDiceResultsExpose() {
        return diceResultsExpose;
    }
}
