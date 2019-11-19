package com.DiceGame.Domain;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private int id;
    private List<Dice> diceList = new ArrayList<>();
    private List<Integer> diceResults= new ArrayList<>();
    private boolean win;
    private static int COUNTER=1;

    public Game(){}

    public Game(int diceNumber) throws Exception {
        for(int i=0; i < diceNumber; ++i){
            diceList.add(new Dice());
        }
        for (Dice dice : diceList) {
            diceResults.add(dice.getResult());
        }
        this.id = COUNTER;
        this.win = gameResult();
        ++COUNTER;
    }

    private boolean gameResult(){
        for (int i = 0; i < diceResults.size()-1; ++i){
            if (diceResults.get(i) != 5 && diceResults.get(i) != 6 || !diceResults.get(i).equals(diceResults.get(i+1))){
                return false;
            }
        }
        return true;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isWin() {
        return win;
    }

    public void setWin(boolean win) {
        this.win = win;
    }

    public List<Dice> getDiceList() {
        return diceList;
    }

    public void setDiceList(List<Dice> diceList) {
        this.diceList = diceList;
    }

    public List<Integer> getDiceResults() {
        return diceResults;
    }

    public void setDiceResults(List<Integer> diceResults) {
        this.diceResults = diceResults;
    }
}
