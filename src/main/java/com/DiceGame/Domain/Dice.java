package com.DiceGame.Domain;

import java.util.Random;

public class Dice {

    private int sides;
    private Random randomGenerator = new Random();
    private int result;

    public Dice(){
        result = randomGenerator.nextInt(6) + 1;
    }

    public Dice(int sides) throws Exception {
        if (sides <= 0)
            throw new Exception();

        this.sides = sides;
        result = randomGenerator.nextInt(sides) + 1;
    }

    public int getSides() {
        return sides;
    }

    public void setSides(int sides) {
        this.sides = sides;
    }

    public int getResult() {
        return result;
    }
}