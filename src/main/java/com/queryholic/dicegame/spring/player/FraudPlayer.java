package com.queryholic.dicegame.spring.player;

import com.queryholic.dicegame.spring.dice.FraudDice;

public class FraudPlayer extends Player {
    private Cheating cheating;

    public FraudPlayer(String name, Cheating cheating) {
        super(name);
        this.dice = new FraudDice();
        this.cheating = cheating;
    }

    public void play() {
        score += cheating.roll(this);
    }

    @Override
    public FraudDice getDice() {
        return (FraudDice) super.getDice();
    }
}
